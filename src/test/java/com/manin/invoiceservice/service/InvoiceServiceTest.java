package com.manin.invoiceservice.service;

import com.google.common.collect.ImmutableMap;
import com.manin.invoiceservice.model.Client;
import com.manin.invoiceservice.model.Customer;
import com.manin.invoiceservice.model.Product;
import com.manin.invoiceservice.model.request.InvoiceRequest;
import com.manin.invoiceservice.model.request.OrderRequest;
import com.manin.invoiceservice.model.response.InvoiceResponse;
import com.manin.invoiceservice.model.response.OrderResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    private static final String CUSTOMER_NAME = "customer";
    private static final String CLIENT_NAME = "client";
    private static final String PRODUCT_NAME_1 = "product_1";
    private static final String PRODUCT_NAME_2 = "product_2";
    private static final int PRODUCT_1_ID = 1;
    private static final int PRODUCT_2_ID = 2;
    private static final BigDecimal PRODUCT_1_MRP = new BigDecimal("1000");
    private static final BigDecimal PRODUCT_2_MRP = new BigDecimal("2000");
    private static final int PRODUCT_1_GST = 18;
    private static final int PRODUCT_2_GST = 25;

    @Mock
    private ClientService clientService;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    public void testInvoiceGenerationWhenGstIsInclusive() {
        List<OrderRequest> orderRequestList = new ArrayList<>();
        OrderRequest orderRequest = createMockedOrder(1, 1, new BigDecimal("980"));
        orderRequestList.add(orderRequest);
        InvoiceRequest invoiceRequest = createInvoiceRequest(1, 1, true, orderRequestList);

        // given
        when(clientService.getClientById(1)).thenReturn(getClient());
        when(customerService.getCustomerById(1)).thenReturn(getCustomer());
        when(productService.getAllProductByIds(Arrays.asList(1))).thenReturn(getSingleMockedProduct());
        // when
        InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
        // then
        assertThat(invoiceResponse.getClient().getOwnerName()).isEqualTo(CLIENT_NAME);
        assertThat(invoiceResponse.getCustomer().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(invoiceResponse.getTotalCGstValue()).isEqualTo(new BigDecimal("74.75"));
        assertThat(invoiceResponse.getTotalOrderValue()).isEqualTo(new BigDecimal("980"));
        assertThat(invoiceResponse.getOrderResponseList().get(0).getDiscount()).isEqualTo(new BigDecimal("2.00"));
        assertThat(invoiceResponse.getOrderResponseList().get(0).getIndividualAmountAfterTax()).isEqualTo(new BigDecimal("2.00"));
        assertThat(invoiceResponse.getOrderResponseList().get(0).getDiscount()).isEqualTo(new BigDecimal("2.00"));
        assertThat(invoiceResponse.getOrderResponseList().get(0).getDiscount()).isEqualTo(new BigDecimal("2.00"));
    }

    @Test
    public void testInvoiceGenerationWhenGstIsExclusive() {
        List<OrderRequest> orderRequestList = new ArrayList<>();
        OrderRequest orderRequest = createMockedOrder(1, 1, new BigDecimal("1000"));
        orderRequestList.add(orderRequest);
        InvoiceRequest invoiceRequest = createInvoiceRequest(1, 1, false, orderRequestList);

        // given
        when(clientService.getClientById(1)).thenReturn(getClient());
        when(customerService.getCustomerById(1)).thenReturn(getCustomer());
        when(productService.getAllProductByIds(Arrays.asList(1))).thenReturn(getSingleMockedProduct());
        // when
        InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
        // then
        assertThat(invoiceResponse.getClient().getOwnerName()).isEqualTo(CLIENT_NAME);
        assertThat(invoiceResponse.getCustomer().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(invoiceResponse.getTotalCGstValue()).isEqualTo(new BigDecimal("90.00"));
        assertThat(invoiceResponse.getTotalOrderValue()).isEqualTo(new BigDecimal("1180.00"));
    }

    @Test
    public void testInvoiceGenerationWhenMultipleOrdersRequestWhenGstIsInclusive() {
        List<OrderRequest> orderRequestList = new ArrayList<>();
        OrderRequest orderRequest1 = createMockedOrder(1, 1, new BigDecimal("900"));
        OrderRequest orderRequest2 = createMockedOrder(2, 1, new BigDecimal("1800"));
        orderRequestList.add(orderRequest1);
        orderRequestList.add(orderRequest2);

        InvoiceRequest invoiceRequest = createInvoiceRequest(1, 1, true, orderRequestList);
        //given
        when(clientService.getClientById(1)).thenReturn(getClient());
        when(customerService.getCustomerById(1)).thenReturn(getCustomer());
        when(productService.getAllProductByIds(Arrays.asList(1, 2))).thenReturn(getMultipleMockedProducts());
        //when
        InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
        // then
        assertThat(invoiceResponse.getClient().getOwnerName()).isEqualTo(CLIENT_NAME);
        assertThat(invoiceResponse.getCustomer().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(invoiceResponse.getTotalCGstValue()).isEqualTo(new BigDecimal("248.64"));
        assertThat(invoiceResponse.getTotalOrderValue()).isEqualTo(new BigDecimal("2700"));
        assertOrderResponseList(invoiceResponse.getOrderResponseList().get(0), new BigDecimal("10.00"), 1,
                new BigDecimal("900"), new BigDecimal("68.64"), 1,
                new BigDecimal("900"), new BigDecimal("762.72"));
        assertOrderResponseList(invoiceResponse.getOrderResponseList().get(1), new BigDecimal("10.00"), 1,
                new BigDecimal("1800"), new BigDecimal("180.00"), 2,
                new BigDecimal("1800"), new BigDecimal("1440.00"));

    }

    @Test
    public void testInvoiceGenerationWhenMultipleOrdersRequestWhenGstIsExclusive() {
        List<OrderRequest> orderRequestList = new ArrayList<>();
        OrderRequest orderRequest1 = createMockedOrder(1, 1, new BigDecimal("500"));
        OrderRequest orderRequest2 = createMockedOrder(2, 1, new BigDecimal("1600"));
        orderRequestList.add(orderRequest1);
        orderRequestList.add(orderRequest2);
        // TODO: define strategy to handle cases amount crosses MRP value.
        InvoiceRequest invoiceRequest = createInvoiceRequest(1, 1, false, orderRequestList);
        //given
        when(clientService.getClientById(1)).thenReturn(getClient());
        when(customerService.getCustomerById(1)).thenReturn(getCustomer());
        when(productService.getAllProductByIds(Arrays.asList(1, 2))).thenReturn(getMultipleMockedProducts());
        //when
        InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
        // then
        assertThat(invoiceResponse.getClient().getOwnerName()).isEqualTo(CLIENT_NAME);
        assertThat(invoiceResponse.getCustomer().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(invoiceResponse.getTotalCGstValue()).isEqualTo(new BigDecimal("245.00"));
        assertThat(invoiceResponse.getTotalOrderValue()).isEqualTo(new BigDecimal("2590.00"));
        assertOrderResponseList(invoiceResponse.getOrderResponseList().get(0), new BigDecimal("41.00"), 1,
                new BigDecimal("590.00"), new BigDecimal("45.00"), 1, new BigDecimal("590.00"), new BigDecimal("500"));
        assertOrderResponseList(invoiceResponse.getOrderResponseList().get(1), new BigDecimal("0.00"), 1,
                new BigDecimal("2000.00"), new BigDecimal("200.00"), 2, new BigDecimal("2000.00"), new BigDecimal("1600"));
    }

    private OrderRequest createMockedOrder(int productId, int quantity, BigDecimal sellingPrice) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductId(productId);
        orderRequest.setQuantity(quantity);
        orderRequest.setSellingPrice(sellingPrice);
        return orderRequest;
    }

    private InvoiceRequest createInvoiceRequest(int clientId,
                                                int customerId,
                                                boolean isGstInclusive,
                                                List<OrderRequest> orderRequestList) {
        InvoiceRequest invoiceRequest = new InvoiceRequest();
        invoiceRequest.setClientId(clientId);
        invoiceRequest.setCustomerId(customerId);
        invoiceRequest.setGstInclusive(isGstInclusive);
        invoiceRequest.setOrderList(orderRequestList);
        return invoiceRequest;
    }

    private Optional<Client> getClient() {
        Client client = new Client();
        client.setId(1);
        client.setOwnerName(CLIENT_NAME);
        return Optional.of(client);
    }

    private Optional<Customer> getCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName(CUSTOMER_NAME);
        return Optional.of(customer);
    }

    private Map<Integer, Product> getSingleMockedProduct() {
        Product product1 = getMockedProduct(PRODUCT_1_ID, PRODUCT_1_GST, PRODUCT_NAME_1, PRODUCT_1_MRP);
        return ImmutableMap.of(PRODUCT_1_ID, product1);
    }

    private Map<Integer, Product> getMultipleMockedProducts() {
        Product product1 = getMockedProduct(PRODUCT_1_ID, PRODUCT_1_GST, PRODUCT_NAME_1, PRODUCT_1_MRP);
        Product product2 = getMockedProduct(PRODUCT_2_ID, PRODUCT_2_GST, PRODUCT_NAME_2, PRODUCT_2_MRP);
        Map<Integer, Product> productMap = new HashMap<>();
        productMap.put(PRODUCT_1_ID, product1);
        productMap.put(PRODUCT_2_ID, product2);
        return productMap;
    }

    private Product getMockedProduct(int id, int gstCode, String productName, BigDecimal mrp) {
        Product product = new Product();
        product.setId(id);
        product.setGstTaxCode(gstCode);
        product.setProductName(productName);
        product.setMaxRetailPrice(mrp);
        return product;
    }

    private void assertOrderResponseList(OrderResponse orderResponse, BigDecimal discount, int quantity,
                                         BigDecimal individualAmountAfterTax, BigDecimal cGstValue,
                                         int productId, BigDecimal totalAmountAfterTax,
                                         BigDecimal totalAmountBeforeTax) {
        assertThat(orderResponse.getDiscount()).isEqualTo(discount);
        assertThat(orderResponse.getQuantity()).isNull();
        assertThat(orderResponse.getIndividualAmountAfterTax()).isEqualTo(individualAmountAfterTax);
        assertThat(orderResponse.getTotalCGstValue()).isEqualTo(cGstValue);
        assertThat(orderResponse.getProduct().getId()).isEqualTo(productId);
        assertThat(orderResponse.getTotalAmountBeforeTax()).isEqualTo(totalAmountBeforeTax);
        assertThat(orderResponse.getTotalAmountAfterTax()).isEqualTo(totalAmountAfterTax);
    }
}