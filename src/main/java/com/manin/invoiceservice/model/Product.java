package com.manin.invoiceservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
public class Product {
    @Id
    private int id;
    @Column
    private String category;
    @Column
    private String subCategory;
    @Column
    private String brandName;
    @Column
    private String productName;
    // TODO: should be a enum with tax codes
    @Column
    private int gstTaxCode;
    // TODO: should be an enum
    @Column
    private String size;
    @Column
    private BigDecimal maxRetailPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getGstTaxCode() {
        return gstTaxCode;
    }

    public void setGstTaxCode(int gstTaxCode) {
        this.gstTaxCode = gstTaxCode;
    }

    public BigDecimal getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(BigDecimal maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }
}
