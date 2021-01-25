package com.gildedrose.store.validation;

import java.util.Arrays;
import java.util.List;

import static com.gildedrose.store.validation.ValidationError.HIGHER_THAN_FIFTY;
import static com.gildedrose.store.validation.ValidationError.LOWER_THAN_ZERO;

public final class Validators {

    private Validators() {
    }

    public static List<Validator> defaultValidators() {
        return Arrays.asList(
                lowerThanZeroValidator(),
                higherThanFiftyValidator());
    }

    private static Validator lowerThanZeroValidator() {
        return new Validator(item -> item.quality < 0, LOWER_THAN_ZERO);
    }

    private static Validator higherThanFiftyValidator() {
        return new Validator(item -> item.quality > 50, HIGHER_THAN_FIFTY);
    }
}
