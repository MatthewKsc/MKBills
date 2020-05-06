package com.matthewksc.billlogic.Controllers;


import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
