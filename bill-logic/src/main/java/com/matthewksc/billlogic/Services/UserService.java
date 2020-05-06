package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.Token;
import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        sendToken(user);
    }

    public void sendToken(User user){
        String tokeValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setUser(user);
        token.setValue(tokeValue);
        tokenRepository.save(token);
        String url = "http://localhost:8080/token?value=" + tokeValue;
        try {
            mailService.sendMail(user.getEmail(), "Submit registration",url, false);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
