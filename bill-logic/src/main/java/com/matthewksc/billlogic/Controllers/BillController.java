package com.matthewksc.billlogic.Controllers;

import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Services.BillService;
import com.matthewksc.billlogic.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/bills")
public class BillController {

    private BillService billService;
    private UserService userService;

    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/{billId}")
    public Optional<Bill> getBill(@PathVariable Long userId, @PathVariable Long billId){
        return userService.getUserBill(userId, billId);
    }

    @GetMapping("/{userId}")
    public Optional<Iterable<Bill>> userBills(@PathVariable Long userId){
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

    @DeleteMapping("/{billId}")
    public void deleteBill(@PathVariable Long billId) {
        billService.delete(billId);
    }
}
