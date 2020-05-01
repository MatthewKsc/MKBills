package com.matthewksc.billlogic;

import com.matthewksc.billlogic.dao.BillRepository;
import com.matthewksc.billlogic.dao.UserRepository;
import com.matthewksc.billlogic.dao.entity.Bill;
import com.matthewksc.billlogic.dao.entity.Category;
import com.matthewksc.billlogic.dao.entity.Role;
import com.matthewksc.billlogic.dao.entity.User;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class start {

    //todo for now class to test data is passing
    public start(BillRepository billRepository, UserRepository userRepository) {
        User mati = new User();
        mati.setUsername("matthew");
        mati.setPassword("matthew123");
        mati.setCountry("PL");
        mati.setAddress("Somewhere");
        mati.setEmail("mati@wp.pl");
        mati.setRole(Role.ROLE_USER);


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

        billRepository.save(bill1);
        billRepository.save(bill2);
    }
}
