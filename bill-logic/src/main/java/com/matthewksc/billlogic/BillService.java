package com.matthewksc.billlogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Iterable<Bill> findAll(){
        return billRepository.findAll();
    }

    public Optional<Bill> findById(Long id){
        return billRepository.findById(id);
    }

    public Bill save(Bill bill){
        return billRepository.save(bill);
    }

    public void delete(Long id){
        billRepository.deleteById(id);
    }
}
