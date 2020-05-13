package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.TokenRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.Token;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Exeptions.NotFoundUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private BillService billService;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UserService(BillService billService, UserRepository userRepository, TokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder, MailService mailService) {
        this.billService = billService;
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
    public Bill getUserBill(Long userId, Long billId){
        return userRepository
                .findById(userId)
                .map(user-> user.getBill(billService.findById(billId)))
                //exception of no bill handled in User getBill method
                .orElseThrow(()->new NotFoundUserException(userId));
    }
    //returning all bills of specific user
    public Iterable<Bill> getAllUserBills(Long userId){
        return userRepository.findById(userId)
                .map(User::getBills)
                .orElseThrow(()->new NotFoundUserException(userId));
    }

    //finding user and setting new bill then saving bill in repository
    public Bill addUserBill(Long userId, Bill bill){
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException(userId))
                .addBill(bill);
        return billService.save(bill);
    }

    public List<User> findAllUsers(){
        return userRepository
                .findAll()
                .parallelStream()
                .filter(user -> user.getRole().equals(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

    //save user
    public void save(User user) {
        userRepository.save(user);
    }
}
