package ES.flysite.controller;

import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ES.flysite.FlysiteApplication;
import ES.flysite.Value;
import ch.qos.logback.classic.Logger;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MyController {

    List<Value> Plane;
    List<String> callsignrepo =new ArrayList<String>();
    int x =0;
	private static final Logger log = (Logger) LoggerFactory.getLogger(FlysiteApplication.class);

    @RequestMapping(value = "/")
    public String home() {

        return "This is Home page";
    }

    @RequestMapping(value = "/about", method = RequestMethod.POST)
    public String about() {

        return "This is About page; POST request";
    }

    @RequestMapping(value = "/fresh", method = {RequestMethod.POST, RequestMethod.GET})
    public String fresh() {

        return "This is Fresh page; GET/POST request";
    }

    @RequestMapping(value = "/todo", consumes = "text/plain")
    public String todo() {

        return "This is Todo page; text/plain content type";
    }

    @RequestMapping(value = "/time", params = { "info=time" })
    public String showTime() {

        var now = LocalTime.now();

        return String.format("%s", now.toString());
    }
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    @Bean
	public List<Value> run(RestTemplate restTemplate) throws Exception {

		ResponseEntity<Value[]> responseEntity = restTemplate.getForEntity(
				"https://opensky-network.org/api/flights/departure?airport=EDDF&begin=1517227200&end=1517230800",
				Value[].class);
		Value[] objects = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();

		//log.info(Arrays.stream(objects).map(object -> mapper.convertValue(object, Value.class))
		//		.collect(Collectors.toList()).toString());    
		Plane = Arrays.stream(objects).map(object -> mapper.convertValue(object, Value.class)).collect(Collectors.toList());
        log.info(Plane.toString());
		return Plane;

	}
     //@Scheduled(fixedRate = 1000)
	@RequestMapping("/allPlane")
	public List<Value> somePage() {
		return Plane;
	}
    @RequestMapping("/filterpage")
	public List<Value> somePage2() {
		return Plane;
	}
    @Scheduled(fixedRate = 5000)
    @RequestMapping("/returnCallsign")
    public List<String> callsignret(){
        callsignrepo.clear();   
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String d = "tempo=" + formatter.format(date);          
        for(int i=0; i< Plane.size(); i++){
            Value temp = Plane.get(i);
            String Calls = temp.getCallsign();
            callsignrepo.add(Calls);
        }
        callsignrepo.add(d);
        return callsignrepo;
    }

    @RequestMapping("/arrivalairport")
    public List<Value> arriret(){
        List<Value> arrivalrepo =new ArrayList<Value>();
        for(int i=0; i< Plane.size(); i++){
            Value temp = Plane.get(i);
            String Arrival = temp.getEstArrivalAirport();
            if( Arrival != null){
                arrivalrepo.add(temp);
            }
            //log.info(Calls);
        }
        return arrivalrepo;
    }


}