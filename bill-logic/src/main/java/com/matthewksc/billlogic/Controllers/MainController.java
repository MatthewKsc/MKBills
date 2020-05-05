package com.matthewksc.billlogic.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    //todo main said for now for security
    @GetMapping
    public String main(){
        return "Hello";
    }

    //todo control panel for admin
    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
