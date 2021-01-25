package com.gildedrose.product.error;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.gildedrose.product.error.ValidationError.LOWER_THAN_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void validateItemHasErrors() {
        //given
        Item someItem = new Item("Some name", 1, -1);
        Validator validator = new Validator(item -> item.quality < 0, LOWER_THAN_ZERO);

        //when
        Optional<ValidationError> optionalError = validator.validate(someItem);

        //then
        assertThat(optionalError).contains(LOWER_THAN_ZERO);
    }

    @Test
    void validateItemHasNoErrors() {
        //given
        Item someItem = new Item("Some name", 1, 1);
        Validator validator = new Validator(item -> item.quality < 0, LOWER_THAN_ZERO);

        //when
        Optional<ValidationError> optionalError = validator.validate(someItem);

        //then
        assertThat(optionalError).isEmpty();
    }
}