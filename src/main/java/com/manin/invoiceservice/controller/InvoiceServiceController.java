package com.manin.invoiceservice.controller;

import com.manin.invoiceservice.model.Customer;
import com.manin.invoiceservice.model.request.InvoiceRequest;
import com.manin.invoiceservice.model.response.InvoiceResponse;
import com.manin.invoiceservice.service.InvoiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@ResponseBody
public class InvoiceServiceController {
    public static final String INVOICE_URL = "/invoice";
    public static final String INVOICE_URL_ID = "/invoice/{id}";

    private InvoiceService invoiceService;

    public InvoiceServiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @ApiOperation("Get all Invoices for a User")
    @GetMapping(path = INVOICE_URL)
    public List<String> getAllInvoices() {
        return Collections.emptyList();
    }

    @ApiOperation("Get Invoice for a invoiceId")
    @GetMapping(path = INVOICE_URL_ID)
    public Optional<String> getInvoiceById(@PathVariable String invoiceId) {
        return Optional.empty();
    }

    @ApiOperation("Save Invoice to db")
    @PostMapping(path = INVOICE_URL)
    public InvoiceResponse saveInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.generateInvoice(invoiceRequest);
    }

}
