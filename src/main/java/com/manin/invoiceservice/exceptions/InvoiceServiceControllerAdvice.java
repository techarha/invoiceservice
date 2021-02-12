package com.manin.invoiceservice.exceptions;

import com.manin.invoiceservice.model.response.InvoiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class InvoiceServiceControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public InvoiceError handleRuntimeExceptions(RuntimeException exception) {
        return new InvoiceError("er1", exception.getMessage());
    }
}
