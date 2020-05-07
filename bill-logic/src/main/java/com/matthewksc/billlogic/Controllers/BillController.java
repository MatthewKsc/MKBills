package com.matthewksc.billlogic.Controllers;

import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Services.BillService;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/bills")
public class BillController {

    private BillService billService;
    private UserService userService;

    @Autowired
    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/{billId}")
    public Optional<Optional<Bill>> getBill(@PathVariable Long userId, @PathVariable Long billId){
        return userService.getUserBill(userId, billId);
    }

    @GetMapping("/{userId}")
    public Optional<List<Bill>> userBills(@PathVariable Long userId){
        return userService.getAllUserBills(userId);
    }

    @PostMapping("/design")
    public Bill addBill(@RequestBody Bill bill){
        return billService.save(bill);
    }

    @PutMapping("/update")
    public Bill updateBill(@RequestBody Bill bill){
        return billService.save(bill);
    }

    @DeleteMapping
    public void deleteBill(Long id){
        billService.delete(id);
    }
}
