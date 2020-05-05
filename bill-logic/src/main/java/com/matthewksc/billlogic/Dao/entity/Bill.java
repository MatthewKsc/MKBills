package com.matthewksc.billlogic.Dao.entity;

import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bill_id;
    private String Tittle;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Double Price;
    private String Description;

    public Long getBill_id() {
        return bill_id;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
