package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.Token;
import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private BillRepository billRepository;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UserService(BillRepository billRepository, UserRepository userRepository, TokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder, MailService mailService) {
        this.billRepository = billRepository;
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
    // return one bill by id
    public Optional<Optional<Bill>> getUserBill(Long userId, Long billId){
        return userRepository.findById(userId).map(user -> {
            return billRepository.findById(billId);});
    }
    //returning all bills of specific user
    public Optional<List<Bill>> getAllUserBills(Long userId){
        return userRepository.findById(userId).map(user -> user.getBills());
    }
}
