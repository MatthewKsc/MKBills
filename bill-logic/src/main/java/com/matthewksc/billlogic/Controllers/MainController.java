package com.matthewksc.billlogic.Controllers;


import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sing-up")
    public String singUp(Model model){
        model.addAttribute("user", new User());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(User user){
        System.out.println(user);
        return "sing-up";
    }


//    //todo main said for now for security
//    @GetMapping("/main")
//    public String main(){
//        return "Main";
//    }
//
//    //todo control panel for admin
//    @GetMapping("/admin")
//    public String admin(){
//        return "Admin";
//    }
//
//    //todo token jwt
//    @GetMapping("/authenticated")
//    public String getToken(){
//        return "authenticated";
//    }
}
