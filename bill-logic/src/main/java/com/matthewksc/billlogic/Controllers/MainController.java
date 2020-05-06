package com.matthewksc.billlogic.Controllers;


import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Token;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private UserService userService;
    private TokenRepository tokenRepository;
    private UserRepository userRepository;

    public MainController(UserService userService, TokenRepository tokenRepository, UserRepository userRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/sing-up")
    public String singUp(Model model){
        model.addAttribute("user", new User());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.addUser(user);
        return "sing-up";
    }

    @GetMapping("/token")
    public String singUp(@RequestParam String value){
        Token byValue = tokenRepository.findTokeByValue(value);
        User user = byValue.getUser();
        user.setEnable(true);
        userRepository.save(user);
        return "hello";
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
