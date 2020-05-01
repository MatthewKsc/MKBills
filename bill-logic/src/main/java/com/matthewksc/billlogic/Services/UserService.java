package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.dao.UserRepo;
import com.matthewksc.billlogic.dao.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
