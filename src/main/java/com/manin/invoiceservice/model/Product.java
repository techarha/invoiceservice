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
    private String id;
    @Column
    private String category;
    @Column
    private String subCategory;
    @Column
    private String brandName;
    @Column
    private String productName;
    // TODO: this should be a enum with tax codes
    @Column
    private String gstTaxCode;
    @Column
    private BigDecimal maxRetailPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGstTaxCode() {
        return gstTaxCode;
    }

    public void setGstTaxCode(String gstTaxCode) {
        this.gstTaxCode = gstTaxCode;
    }

    public BigDecimal getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(BigDecimal maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }
}
