package com.matthewksc.billlogic.Dao.entity;

import com.matthewksc.billlogic.Exeptions.NotFoundBillException;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; //EnumType for sql database

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "user_id")
    private List<Bill> bills = new ArrayList<>();

    @Email(message = "Email is required")
    private String email;
    private String country;
    private String address;
    private boolean isEnable; //for MyUserDetails

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", bills=" + bills +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    //need to get exactly one bill form list
    public Bill getBill(Bill bill) {
        return this.bills.parallelStream()
                .filter(bill1 -> bill1.equals(bill))
                .findFirst()
                .orElseThrow(() -> new NotFoundBillException(bill.getBill_id()));
    }
}
