package com.matthewksc.billlogic.Controllers;


import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public Iterable<User> getUsers(){
        return userService.findAllUsers();
    }
}
