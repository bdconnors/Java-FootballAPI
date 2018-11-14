package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test") // BASE URL http://localhost:8080/api/test/
public class TestController {

    // default
    // http://localhost:8080/api/test
    @RequestMapping
    public String getDefault() {
        return "default";
    }

    // http://localhost:8080/api/test/hello?name=someone
    // http://localhost:8080/api/test/hello
    @RequestMapping("hello")
    public String getHello(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello " + name;
    }
}
