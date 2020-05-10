package com.matthewksc.billlogic.Controllers;


import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Token;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Services.UserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SecurityController {

    private UserService userService;
    private TokenRepository tokenRepository;

    public SecurityController(UserService userService, TokenRepository tokenRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
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
    public String singUp(@RequestParam String value) throws NotFoundException {
        //handling exception if token even exists
        Optional<Token> byValue = tokenRepository.findTokeByValue(value);
        byValue.orElseThrow(()-> new NotFoundException("Not found token: "+value));

        User user = byValue.get().getUser();
        user.setEnable(true);
        userService.save(user);
        return "main/side";
    }
}
