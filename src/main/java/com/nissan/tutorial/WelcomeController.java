package com.nissan.tutorial;

import com.nissan.tutorial.configuration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service;

    @Autowired
    private BasicConfiguration configuration;

    @RequestMapping("/welcome")
    public String welcome() {
        return service.retrieveWelcomeMessage();
    }

    @RequestMapping("/dynamic-configuration")
    public String mapWelcome() {

        Map map = new HashMap();
        map.put("", configuration.getMessage());
        map.put("", configuration.getNumber());
        map.put("", configuration.isValue());
        return service.retrieveWelcomeMessage();
    }
}
