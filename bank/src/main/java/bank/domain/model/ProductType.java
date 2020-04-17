package bank.domain.model;

import java.util.stream.Stream;

public enum ProductType {
    AUTOMATIC(0),
    MANUAL(1);

    private Integer value;

    ProductType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean isValid(Integer productType) {
        return Stream.of(ProductType.values())
            .anyMatch(e -> e.getValue().equals(productType));
    }
}
