package service;

import model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {
    private static final BigDecimal BASIC_TAX_RATE = new BigDecimal("0.10");
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");
    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");

    public BigDecimal calculateUnitTax(Item item) {
        if (item.isExempt()) return BigDecimal.ZERO;
        
        BigDecimal tax = item.getPrice().multiply(BASIC_TAX_RATE);
        if (item.isImported()) {
            tax = tax.add(item.getPrice().multiply(IMPORT_TAX_RATE));
        }
        return roundTax(tax);
    }

    private BigDecimal roundTax(BigDecimal tax) {
        if (tax.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal divided = tax.divide(ROUNDING_FACTOR, 0, RoundingMode.UP);
        return divided.multiply(ROUNDING_FACTOR).setScale(2, RoundingMode.HALF_UP);
    }
}