package com.matthewksc.billlogic.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/mkbills")
    public String domain(){
        return "main/side";
    }

}
