package com.matthewksc.billlogic.Dao;

import com.matthewksc.billlogic.Dao.entity.Bill;
import com.matthewksc.billlogic.Dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String Username);

    @Query(value = "SELECT bills from User where user_id= ?1")
    Iterable<Bill> findBillsByUser_id(Long id);
}
