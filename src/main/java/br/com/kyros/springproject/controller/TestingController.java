package br.com.kyros.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestingController {

    @RequestMapping("/")
    @ResponseBody
    public String Running(){
        return "Running!";
    }

}
