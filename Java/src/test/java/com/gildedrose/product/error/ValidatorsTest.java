package com.gildedrose.product.error;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static com.gildedrose.product.error.ValidationError.HIGHER_THAN_FIFTY;
import static com.gildedrose.product.error.ValidationError.LOWER_THAN_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorsTest {

    @Test
    void validateItemHasLowerThanZeroQualityError() {
        //given
        Item someItem = new Item("Some name", 1, -1);

        //when
        Optional<ValidationError> optionalError = Validators.defaultValidators().stream()
                .map(validator -> validator.validate(someItem))
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .filter(LOWER_THAN_ZERO::equals)
                .findFirst();

        //then
        assertThat(optionalError).contains(LOWER_THAN_ZERO);
    }

    @Test
    void validateItemHasHigherThanFiftyQualityError() {
        //given
        Item someItem = new Item("Some name", 1, 51);

        //when
        Optional<ValidationError> optionalError = Validators.defaultValidators().stream()
                .map(validator -> validator.validate(someItem))
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .filter(HIGHER_THAN_FIFTY::equals)
                .findFirst();

        //then
        assertThat(optionalError).contains(HIGHER_THAN_FIFTY);
    }
}