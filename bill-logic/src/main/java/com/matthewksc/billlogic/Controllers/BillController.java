package com.matthewksc.billlogic.Controllers;

import com.matthewksc.billlogic.dao.entity.Bill;
import com.matthewksc.billlogic.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bill")
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public Iterable<Bill> getAll(){
        return billService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Bill> getById(@RequestParam Long id){
        return billService.findById(id);
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
