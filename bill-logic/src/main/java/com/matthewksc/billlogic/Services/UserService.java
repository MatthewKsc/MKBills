package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}
