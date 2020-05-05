package com.matthewksc.billlogic.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    //todo main said for now for security
    @GetMapping("/main")
    public String main(){
        return "Main";
    }

    //todo control panel for admin
    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

    //todo token jwt
    @GetMapping("/authenticated")
    public String getToken(){
        return "authenticated";
    }
}
