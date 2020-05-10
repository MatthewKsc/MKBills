package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Exeptions.NotFoundBillException;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Iterable<Bill> findAll(){
        return billRepository.findAll();
    }

    public Bill findById(Long id){
        return billRepository.findById(id).orElseThrow(()->new NotFoundBillException(id));
    }

    public Bill save(Bill bill){
        return billRepository.save(bill);
    }

    public void delete(Long id){
        billRepository.deleteById(id);
    }
}
