package util;

import java.math.BigDecimal;

public class TaxConstants {
    public static final BigDecimal BASIC_TAX_RATE = new BigDecimal("0.10");
    public static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");
    public static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");

    private TaxConstants() {
        throw new UnsupportedOperationException("This is a util class and cannot be instantiated");
    }
}
