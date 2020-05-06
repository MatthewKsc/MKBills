package com.matthewksc.billlogic.Dao.entity;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Token_id;
    private String value;

    @OneToOne
    private User user;

    public Long getToken_id() {
        return Token_id;
    }

    public void setToken_id(Long token_id) {
        Token_id = token_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
