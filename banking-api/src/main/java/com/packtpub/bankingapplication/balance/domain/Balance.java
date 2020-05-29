package com.packtpub.bankingapplication.balance.domain;

import javax.persistence.*;

@Entity
public class Balance {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private int balance;
}
