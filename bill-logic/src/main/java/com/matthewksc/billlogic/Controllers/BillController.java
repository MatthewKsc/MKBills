package com.matthewksc.billlogic.Controllers;

import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {

    private BillService billService;
    private UserRepository userRepository;

    @Autowired
    public BillController(BillService billService, UserRepository userRepository) {
        this.billService = billService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<Bill> getAll(){
        return billService.findAll();
    }

//    @GetMapping("/{id}")
//    public Optional<Bill> getById(@PathVariable Long id){
//        return billService.findById(id);
//    }

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

    //for now listing bills for user
    @GetMapping("/{username}")
    public Optional<List<Bill>> userBills(@PathVariable String username){
        return userRepository.findByUsername(username).map(user -> user.getBills());
    }
}
