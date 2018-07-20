package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class MyController {

    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/miya")
    public String miya() {
        return configBean.getGreeting()+"-"+configBean.getName()+"-"+configBean.getUuid()+"-"+configBean.getMax();
    }

    @RequestMapping(value = "/hi")
    public String hi() {
        return configBean.getGreeting();
    }
}
