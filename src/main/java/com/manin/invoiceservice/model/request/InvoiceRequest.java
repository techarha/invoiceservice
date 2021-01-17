package com.manin.invoiceservice.model.request;

import java.util.List;

public class InvoiceRequest {
    private int customerId;
    private int clientId;
    private List<OrderRequest> orderList;
    private boolean isGstInclusive;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<OrderRequest> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderRequest> orderList) {
        this.orderList = orderList;
    }

    public boolean isGstInclusive() {
        return isGstInclusive;
    }

    public void setGstInclusive(boolean gstInclusive) {
        isGstInclusive = gstInclusive;
    }
}
