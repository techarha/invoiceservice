package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Client;
import com.manin.invoiceservice.model.Customer;
import com.manin.invoiceservice.model.Product;
import com.manin.invoiceservice.model.request.InvoiceRequest;
import com.manin.invoiceservice.model.request.OrderRequest;
import com.manin.invoiceservice.model.response.InvoiceResponse;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.when;

/**
 * 1. test when GST included/excluded
 * 2. test for exception scenarios
 * 3. test for single order invoice
 * 4. test for multiple order invoice
 * 5. test for dummy invoice generation strategy
 * 6. test for multiple different product orders
 * 7. test for field validations as well.
 */

@SpringBootTest
public class InvoiceServiceTest {

    @Mock
    private ClientService clientService;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    public void testInvoiceGenerationWhenGstIsIncluded() {
        // given
        when(clientService.getClientById(1)).thenReturn(getClient());
        when(customerService.getCustomerById(1)).thenReturn(getCustomer());
        when(productService.getAllProductByIds(Arrays.asList(1))).thenReturn(getProducts());
        // when
        InvoiceResponse invoiceResponse = invoiceService.generateInvoice(createInvoiceRequest());


    }


    private Map<Integer, Product> getProducts() {
        Product product = new Product();
        product.setId(1);
        product.setGstTaxCode(18);
        product.setProductName("Product name");
        product.setCategory("category");
        product.setMaxRetailPrice(new BigDecimal("1000"));
        Map<Integer, Product> productMap = new HashMap<>();
        productMap.put(1, product);
        return productMap;
    }

    private Optional<Client> getClient() {
        Client client = new Client();
        client.setId(1);
        client.setOwnerName("Owner");
        return Optional.of(client);
    }

    private Optional<Customer> getCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Customer Name");
        return Optional.of(customer);
    }

    private InvoiceRequest createInvoiceRequest() {
        List<OrderRequest> orderRequests = new ArrayList<>();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductId(1);
        orderRequest.setQuantity(1);
        orderRequest.setSellingPrice(new BigDecimal("500"));
        orderRequests.add(orderRequest);
        InvoiceRequest invoiceRequest = new InvoiceRequest();
        invoiceRequest.setClientId(1);
        invoiceRequest.setCustomerId(1);
        invoiceRequest.setGstInclusive(false);
        invoiceRequest.setOrderList(orderRequests);
        return invoiceRequest;
    }
}