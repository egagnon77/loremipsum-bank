package bank.domain.model;

import java.util.stream.Stream;

public enum ProductLevel {
    NORMAL(0),
    VIP(1);

    private Integer value;

    ProductLevel(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean isValid(Integer productLevel) {
        return Stream.of(ProductLevel.values())
            .anyMatch(e -> e.getValue().equals(productLevel));
    }
}

