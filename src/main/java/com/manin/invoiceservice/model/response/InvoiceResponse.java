package com.manin.invoiceservice.model.response;

import com.manin.invoiceservice.model.Client;
import com.manin.invoiceservice.model.Customer;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceResponse {
    private String invoiceNumber;
    private Customer customer;
    private Client client;
    private List<OrderResponse> orderResponseList;
    private BigDecimal totalOrderValue;
    private BigDecimal totalUGstValue;
    private BigDecimal totalIGstValue;
    private BigDecimal totalCGstValue;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderResponse> getOrderResponseList() {
        return orderResponseList;
    }

    public void setOrderResponseList(List<OrderResponse> orderResponseList) {
        this.orderResponseList = orderResponseList;
    }

    public BigDecimal getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(BigDecimal totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public BigDecimal getTotalUGstValue() {
        return totalUGstValue;
    }

    public void setTotalUGstValue(BigDecimal totalUGstValue) {
        this.totalUGstValue = totalUGstValue;
    }

    public BigDecimal getTotalIGstValue() {
        return totalIGstValue;
    }

    public void setTotalIGstValue(BigDecimal totalIGstValue) {
        this.totalIGstValue = totalIGstValue;
    }

    public BigDecimal getTotalCGstValue() {
        return totalCGstValue;
    }

    public void setTotalCGstValue(BigDecimal totalCGstValue) {
        this.totalCGstValue = totalCGstValue;
    }
}
