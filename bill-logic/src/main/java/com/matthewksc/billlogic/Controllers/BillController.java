package com.matthewksc.billlogic.Controllers;

import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Services.BillService;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/bills")
public class BillController {

    private BillService billService;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public BillController(BillService billService, UserService userService, UserRepository userRepository) {
        this.billService = billService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}/{billId}")
    public Optional<Optional<Bill>> getBill(@PathVariable Long userId, @PathVariable Long billId){
        return userService.getUserBill(userId, billId);
    }

    @GetMapping("/{userId}")
    public Optional<List<Bill>> userBills(@PathVariable Long userId){
        return userService.getAllUserBills(userId);
    }

    @PostMapping("/{userId}/design")
    public Bill addBill(@PathVariable Long userId,@RequestBody Bill bill){
        return userService.addUserBill(userId, bill);
    }

    @PutMapping("/{userId}/update")
    public Bill updateBill(@PathVariable Long userId, @RequestBody Bill bill){
        return userService.addUserBill(userId, bill);
    }

    @DeleteMapping
    public void deleteBill(Long id){
        billService.delete(id);
    }
}
