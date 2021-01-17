package com.manin.invoiceservice.model.response;

import com.manin.invoiceservice.model.Product;

import java.math.BigDecimal;

public class OrderResponse {
    private Product product;
    private BigDecimal discount;
    private BigDecimal iGst;
    private BigDecimal cGst;
    private BigDecimal uGst;

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

    public BigDecimal getiGst() {
        return iGst;
    }

    public void setiGst(BigDecimal iGst) {
        this.iGst = iGst;
    }

    public BigDecimal getcGst() {
        return cGst;
    }

    public void setcGst(BigDecimal cGst) {
        this.cGst = cGst;
    }

    public BigDecimal getuGst() {
        return uGst;
    }

    public void setuGst(BigDecimal uGst) {
        this.uGst = uGst;
    }
}
