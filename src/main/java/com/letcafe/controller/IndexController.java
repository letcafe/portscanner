package com.letcafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {


    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String getIndex() {
        System.out.println("你已经进来了");
        return "index";
    }
}
