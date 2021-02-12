package com.manin.invoiceservice.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TaxRateCode {
    GST_TAX_1(5),
    GST_TAX_2(12),
    GST_TAX_3(18),
    GST_TAX_4(28);

    private int taxRate;

    TaxRateCode(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getTaxRate() {
        return this.taxRate;
    }

    private static final Map<String, Integer> taxRateMap = Arrays.stream(TaxRateCode.values())
            .collect(Collectors.toMap(TaxRateCode::name, TaxRateCode::getTaxRate));

    public static int valueOfTaxRateCode(String taxRateCode) {
        return taxRateMap.get(taxRateCode);
    }
}
