package ES.flysite.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
public class TestController {

    @RequestMapping(value = "/info")
    public String info() {

        return "This is info page";
    }

    @RequestMapping(path="*.do")
    public String somePage() {

        return "This is some page";
    }
}