package com.manin.invoiceservice.model.response;

import com.manin.invoiceservice.model.Product;

import java.math.BigDecimal;

public class OrderResponse {
    private Product product;
    private BigDecimal discount;
    private BigDecimal individualIGstValue;
    private BigDecimal individualCGstValue;
    private BigDecimal individualUGstValue;
    private BigDecimal totalIGstValue;
    private BigDecimal totalUGstValue;
    private BigDecimal totalCGstValue;
    private BigDecimal individualAmountBeforeTax;
    private BigDecimal individualAmountAfterTax;
    private BigDecimal totalAmountBeforeTax;
    private BigDecimal totalAmountAfterTax;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getIndividualIGstValue() {
        return individualIGstValue;
    }

    public void setIndividualIGstValue(BigDecimal individualIGstValue) {
        this.individualIGstValue = individualIGstValue;
    }

    public BigDecimal getIndividualCGstValue() {
        return individualCGstValue;
    }

    public void setIndividualCGstValue(BigDecimal individualCGstValue) {
        this.individualCGstValue = individualCGstValue;
    }

    public BigDecimal getIndividualUGstValue() {
        return individualUGstValue;
    }

    public void setIndividualUGstValue(BigDecimal individualUGstValue) {
        this.individualUGstValue = individualUGstValue;
    }

    public BigDecimal getIndividualAmountBeforeTax() {
        return individualAmountBeforeTax;
    }

    public void setIndividualAmountBeforeTax(BigDecimal individualAmountBeforeTax) {
        this.individualAmountBeforeTax = individualAmountBeforeTax;
    }

    public BigDecimal getTotalIGstValue() {
        return totalIGstValue;
    }

    public void setTotalIGstValue(BigDecimal totalIGstValue) {
        this.totalIGstValue = totalIGstValue;
    }

    public BigDecimal getTotalUGstValue() {
        return totalUGstValue;
    }

    public void setTotalUGstValue(BigDecimal totalUGstValue) {
        this.totalUGstValue = totalUGstValue;
    }

    public BigDecimal getTotalCGstValue() {
        return totalCGstValue;
    }

    public void setTotalCGstValue(BigDecimal totalCGstValue) {
        this.totalCGstValue = totalCGstValue;
    }

    public BigDecimal getTotalAmountBeforeTax() {
        return totalAmountBeforeTax;
    }

    public void setTotalAmountBeforeTax(BigDecimal totalAmountBeforeTax) {
        this.totalAmountBeforeTax = totalAmountBeforeTax;
    }

    public BigDecimal getIndividualAmountAfterTax() {
        return individualAmountAfterTax;
    }

    public void setIndividualAmountAfterTax(BigDecimal individualAmountAfterTax) {
        this.individualAmountAfterTax = individualAmountAfterTax;
    }

    public BigDecimal getTotalAmountAfterTax() {
        return totalAmountAfterTax;
    }

    public void setTotalAmountAfterTax(BigDecimal totalAmountAfterTax) {
        this.totalAmountAfterTax = totalAmountAfterTax;
    }
}
