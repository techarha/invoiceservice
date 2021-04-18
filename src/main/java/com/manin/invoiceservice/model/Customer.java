package com.manin.invoiceservice.model;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String customerGst;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private boolean isBusinessCustomer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerGst() {
        return customerGst;
    }

    public void setCustomerGst(String customerGst) {
        this.customerGst = customerGst;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBusinessCustomer() {
        return isBusinessCustomer;
    }

    public void setBusinessCustomer(boolean businessCustomer) {
        isBusinessCustomer = businessCustomer;
    }
}
