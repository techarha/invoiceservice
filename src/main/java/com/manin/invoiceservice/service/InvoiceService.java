package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Client;
import com.manin.invoiceservice.model.Customer;
import com.manin.invoiceservice.model.Product;
import com.manin.invoiceservice.model.request.InvoiceRequest;
import com.manin.invoiceservice.model.request.OrderRequest;
import com.manin.invoiceservice.model.response.InvoiceResponse;
import com.manin.invoiceservice.model.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Service
public class InvoiceService {

    private CustomerService customerService;
    private ClientService clientService;
    private ProductService productService;

    public InvoiceService(CustomerService customerService,
                          ClientService clientService,
                          ProductService productService) {
        this.customerService = customerService;
        this.clientService = clientService;
        this.productService = productService;
    }

    public InvoiceResponse generateInvoice(InvoiceRequest invoiceRequest) {
        /**
         * 1. Need to add logging as well as exception handling through advice pattern.
         * 2. Exception handling to be added.
         * 3. Refactoring
         *  Field Validations to be added.
         */

        Optional<Customer> customer = customerService.getCustomerById(invoiceRequest.getCustomerId());
        Optional<Client> client = clientService.getClientById(invoiceRequest.getClientId());
        List<OrderRequest> orders = invoiceRequest.getOrderList();
        List<OrderResponse> orderResponseList = assembleOrderResponseList(orders, invoiceRequest.isGstInclusive());
        InvoiceResponse invoiceResponse = assembleInvoice(client, customer, orderResponseList);
        return invoiceResponse;
    }

    private List<OrderResponse> assembleOrderResponseList(List<OrderRequest> orders, boolean isGstInclusive) {
        List<Integer> productIdList = orders.stream()
                .map(r -> r.getProductId()).collect(Collectors.toList());

        Map<Integer, Product> productMap = productService.getAllProductByIds(productIdList);

        List<OrderResponse> orderResponseList = orders.stream()
                .map(orderRequest -> assembleOrderResponse(orderRequest, productMap, isGstInclusive))
                .collect(Collectors.toList());

        return orderResponseList;
    }

    private OrderResponse assembleOrderResponse(OrderRequest order,
                                                Map<Integer, Product> productMap,
                                                boolean isGstInclusive) {
        BigDecimal uGst;
        BigDecimal cGst;
        BigDecimal amountBeforeTax;
        BigDecimal amountAfterTax;

        OrderResponse orderResponse = new OrderResponse();
        BigDecimal sellingPrice = order.getSellingPrice();
        Product product = productMap.get(order.getProductId());
        // TODO: update to a better name
        BigDecimal taxIdentifier = valueOf(product.getGstTaxCode()).divide(valueOf(100)).add(BigDecimal.ONE);
        if (isGstInclusive) {
            amountBeforeTax = sellingPrice.divide(taxIdentifier, BigDecimal.ROUND_HALF_DOWN, 2);
            amountAfterTax = sellingPrice;
        } else {
            amountBeforeTax = sellingPrice;
            amountAfterTax = sellingPrice.multiply(taxIdentifier);
        }
        // TODO: IGST to be introduced for inter state transaction
        cGst = amountAfterTax.subtract(amountBeforeTax).divide(valueOf(2), BigDecimal.ROUND_HALF_DOWN, 2);
        uGst = cGst;

        orderResponse.setIndividualAmountBeforeTax(amountBeforeTax);
        orderResponse.setTotalAmountBeforeTax(amountBeforeTax.multiply(valueOf(order.getQuantity())));
        orderResponse.setIndividualCGstValue(cGst);
        orderResponse.setIndividualUGstValue(uGst);
        orderResponse.setTotalCGstValue(cGst.multiply(valueOf(order.getQuantity())));
        orderResponse.setTotalUGstValue(uGst.multiply(valueOf(order.getQuantity())));
        orderResponse.setProduct(product);
        orderResponse.setDiscount(product.getMaxRetailPrice().subtract(sellingPrice)
                .divide(product.getMaxRetailPrice(), BigDecimal.ROUND_HALF_DOWN, 2).multiply(new BigDecimal("100")));
        orderResponse.setIndividualAmountAfterTax(amountAfterTax);
        orderResponse.setTotalAmountAfterTax(amountAfterTax.multiply(valueOf(order.getQuantity())));
        return orderResponse;
    }

    private InvoiceResponse assembleInvoice(Optional<Client> client,
                                            Optional<Customer> customer,
                                            List<OrderResponse> orderResponseList) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setInvoiceNumber(invoiceNumberGenerationStrategy(client.get()));
        invoiceResponse.setClient(client.get());
        invoiceResponse.setCustomer(customer.get());
        invoiceResponse.setOrderResponseList(orderResponseList);
        invoiceResponse.setTotalUGstValue(orderResponseList.stream()
                .map(o -> o.getTotalUGstValue()).reduce(BigDecimal.ZERO, BigDecimal::add));
        invoiceResponse.setTotalCGstValue(orderResponseList.stream()
                .map(o -> o.getTotalCGstValue()).reduce(BigDecimal.ZERO, BigDecimal::add));
        invoiceResponse.setTotalOrderValue(orderResponseList.stream()
                .map(o -> o.getTotalAmountAfterTax()).reduce(BigDecimal.ZERO, BigDecimal::add));
        return invoiceResponse;
    }


    // TODO: move to a new class, so to test it properly.
    private String invoiceNumberGenerationStrategy(Client client) {
        // TODO: should be based on client name and timeStamp.
        String name = client.getBusinessName();
        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        // TODO: introduce DB for Invoice service, to track everyday order numbers.
        return name + "-" + date;
    }

}
