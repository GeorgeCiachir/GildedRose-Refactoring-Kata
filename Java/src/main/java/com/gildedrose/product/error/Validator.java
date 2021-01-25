package com.gildedrose.product.error;

import com.gildedrose.Item;

import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Validator {

    private final Predicate<Item> checker;
    private final ValidationError error;

    public Validator(Predicate<Item> checker, ValidationError error) {
        this.checker = checker;
        this.error = error;
    }

    public Optional<ValidationError> validate(Item item) {
        return checker.test(item)
                ? of(error)
                : empty();
    }
}
