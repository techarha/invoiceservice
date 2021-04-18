package com.manin.invoiceservice.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InvoiceRequest {
    // TODO: update data type to String ****
    @NotNull(message = "CustomerId cannot be null")
    private String customerId;
    @NotNull(message = "ClientId cannot be null")
    private String clientId;
    @NotEmpty(message = "Order List cannot be null")
    private List<OrderRequest> orderList;
    @NotNull(message = "GST inclusive Flag must be set")
    private boolean isGstInclusive;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
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
