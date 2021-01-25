package com.gildedrose.product;

import com.gildedrose.Item;
import com.gildedrose.product.error.ValidationError;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ProductBuilder {

    private final ProductType productType;

    public ProductBuilder(ProductType productType) {
        this.productType = productType;
    }

    public Product buildFrom(Item item) {
        validate(item);
        return productType.getProductConstructor().apply(item);
    }

    private void validate(Item item) {
        List<ValidationError> errors = productType.getProductValidators().stream()
                .map(validator -> validator.validate(item))
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(toList());

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Validation errors: " + errors);
        }
    }
}
