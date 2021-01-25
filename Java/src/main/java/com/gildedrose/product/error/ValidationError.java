package com.gildedrose.product.error;

public enum ValidationError {

    LOWER_THAN_ZERO("The quality should never be lower than zero"),
    HIGHER_THAN_FIFTY("The quality should never be higher than 50"),
    SULFURAS_CUSTOM("The quality of Sulfuras should always be 80");

    private final String message;

    ValidationError(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
