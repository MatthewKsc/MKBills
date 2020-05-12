package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Exeptions.NotFoundBillException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    @Mock
    BillRepository billRepository;

    @InjectMocks
    BillService billService;

    @Test
    public void findAll() {
        given(billRepository.findAll()).willReturn(initData());

        List<Bill> billList = (List<Bill>) billService.findAll();

        Assert.assertThat(billList, Matchers.hasSize(3));
        assertEquals(billList.size(), 3);
    }

    @Test
    public void findById() {
        given(billRepository.findById(1L)).willReturn(java.util.Optional.of(new Bill()));

        Bill bill = billService.findById(1L);

        assertEquals(bill, billService.findById(1L));
        assertThrows(NotFoundBillException.class,()->billService.findById(2L));
    }

    @Test
    public void save() {
        Bill bill = new Bill();
        Bill bill2 = new Bill();
        given(billRepository.save(bill)).willReturn(bill);

        assertEquals(bill, billService.save(bill));
        assertNotEquals(bill, billService.save(bill2));
    }

    @Test
    public void delete() {
        Bill bill = new Bill();
        bill.setBill_id(1L);
        billService.delete(1L);

        verify(billRepository, times(1)).deleteById(1L);
    }

    public List<Bill> initData(){
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill());
        bills.add(new Bill());
        bills.add(new Bill());
        return bills;
    }
}