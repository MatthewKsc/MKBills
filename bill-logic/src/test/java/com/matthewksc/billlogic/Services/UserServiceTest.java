package com.matthewksc.billlogic.Services;

import com.matthewksc.billlogic.Dao.BillRepository;
import com.matthewksc.billlogic.Dao.UserRepository;
import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.User;
import com.matthewksc.billlogic.Exeptions.NotFoundUserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    BillRepository billRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void addUser() {
    }

    @Test
    public void getUserBill() {
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
        assertEquals(billList.size(), 3);
        assertNotEquals(billList.size(), 2);
        assertThrows(NotFoundUserException.class, ()->userService.getAllUserBills(3L));
    }

    @Test
    public void addUserBill() {
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