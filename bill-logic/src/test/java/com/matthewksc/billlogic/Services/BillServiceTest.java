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
    }

    @Test
    public void delete() {
    }

    public List<Bill> initData(){
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill());
        bills.add(new Bill());
        bills.add(new Bill());
        return bills;
    }
}