package com.matthewksc.billlogic;

import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.Category;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class start {

    //todo for now class to test data is passing
    public start(BillRepository billRepository, UserRepository userRepository,
                 PasswordEncoder passwordEncoder) {
        User mati = new User();
        mati.setUsername("matthew");
        mati.setPassword(passwordEncoder.encode("matthew123"));
        mati.setCountry("PL");
        mati.setAddress("Somewhere");
        mati.setEmail("mati@wp.pl");
        mati.setRole(Role.ROLE_USER);
        mati.setEnable(true);

        User mati2 = new User();
        mati2.setUsername("admin");
        mati2.setPassword(passwordEncoder.encode("1234"));
        mati2.setCountry("PL");
        mati2.setAddress("Somewhere");
        mati2.setEmail("matiadmin@wp.pl");
        mati2.setRole(Role.ROLE_ADMIN);
        mati2.setEnable(true);


        List<Bill> bills = new ArrayList<>();
        Bill bill1 = new Bill();
        Bill bill2 = new Bill();
        bill1.setTittle("refueling");
        bill1.setCategory(Category.Car);
        bill1.setDescription("refueling car to full");
        bill1.setPrice(150.00);

        bill2.setTittle("McDonald's");
        bill2.setCategory(Category.Food);
        bill2.setDescription("Night trip throw city with stop in fast food");
        bill2.setPrice(15.00);
        bills.add(bill1);
        bills.add(bill2);
        mati.setBills(bills);

        userRepository.save(mati);
        userRepository.save(mati2);
        billRepository.save(bill1);
        billRepository.save(bill2);
    }
}
