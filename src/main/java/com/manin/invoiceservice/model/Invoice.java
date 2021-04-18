package com.manin.invoiceservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Invoice {
    @Id
    private String id;

}
