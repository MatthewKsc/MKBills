package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.Role;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Exeptions.NotFoundBillException;
import com.matthewksc.billlogic.Exeptions.NotFoundUserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    BillService billService;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @Test
    public void save() {
        User user = new User();
        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getUserBill() {
        User user = initData();
        Bill bill = user.getBills()
                .parallelStream()
                .findFirst()
                .get();
        Bill otherBill = new Bill();
        given(billService.findById(2L)).willReturn(bill);
        given(billService.findById(1L)).willThrow(NotFoundBillException.class);
        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(user));

        Bill check = userService.getUserBill(1L, 2L);

        //check if getUserBill retuning good bill
        assertEquals(bill, check);
        assertNotEquals(otherBill,check);
        //checks if exceptions work fine
        assertThrows(NotFoundUserException.class,() -> userService.getUserBill(2L, 2L));
        assertThrows(NotFoundBillException.class,() -> userService.getUserBill(1L, 1L));
    }

    @Test
    public void getAllUserBills() {
        User user = initData();
        User userNoList = new User();
        given(userRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(user));
        given(userRepository.findById(2L)).willReturn(java.util.Optional.ofNullable(userNoList));

        List<Bill> billList = (List<Bill>) userService.getAllUserBills(1L);
        List<Bill> noElementsList = (List<Bill>) userService.getAllUserBills(2L);

        //noElementList check that each user have his one list of bills
        assertEquals(noElementsList.size(), 0);
        assertNotEquals(noElementsList.size(), 2);
        //check size of list
        assertEquals(billList.size(), 3);
        assertNotEquals(billList.size(), 2);
        //checking if notFoundUserException works properly
        assertThrows(NotFoundUserException.class, ()->userService.getAllUserBills(3L));
    }

    @Test
    public void addUserBill() {
        User user = new User();
        Bill bill = new Bill();
        Bill bill2 = new Bill();
        given(userRepository.findById(1L)).willReturn(java.util.Optional.of(user));
        given(billService.save(bill)).willReturn(bill);

        Bill checkBill = userService.addUserBill(1L, bill);

        //check if proper bill was saved
        assertEquals(bill, checkBill);
        assertNotEquals(bill2, checkBill);
        //checking if notFoundUserException works properly
        assertThrows(NotFoundUserException.class, ()-> userService.addUserBill(2L, bill));
    }

    public User initData(){
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill());
        bills.add(new Bill());
        bills.add(new Bill());
        User user = new User();
        user.setBills(bills);
        return user;
    }
}