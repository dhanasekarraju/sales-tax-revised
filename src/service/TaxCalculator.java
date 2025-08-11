package service;

import model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static util.TaxConstants.*;

public class TaxCalculator {

    public BigDecimal calculateUnitTax(Item item) {
        BigDecimal tax = BigDecimal.ZERO;

        if (!item.isExempt()) {
            tax = tax.add(item.getPrice().multiply(BASIC_TAX_RATE));
        }

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