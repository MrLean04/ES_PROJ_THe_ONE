package ES.flysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class SchedulingTasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlysiteApplication.class);
    }
    
}
