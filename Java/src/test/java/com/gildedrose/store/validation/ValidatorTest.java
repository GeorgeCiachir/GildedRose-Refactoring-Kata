package com.gildedrose.store.validation;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.gildedrose.store.validation.ValidationError.LOWER_THAN_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void verifyItemHasErrors() {
        //given
        int minQuality = 0;
        Item someItem = new Item("Some name", 1, minQuality - 1);
        Validator validator = new Validator(item -> item.quality < 0, LOWER_THAN_ZERO);

        //when
        Optional<ValidationError> optionalError = validator.validate(someItem);

        //then
        assertThat(optionalError).contains(LOWER_THAN_ZERO);
    }

    @Test
    void verifyItemHasNoErrors() {
        //given
        int minQuality = 0;
        Item someItem = new Item("Some name", 1, minQuality);
        Validator validator = new Validator(item -> item.quality < 0, LOWER_THAN_ZERO);

        //when
        Optional<ValidationError> optionalError = validator.validate(someItem);

        //then
        assertThat(optionalError).isEmpty();
    }
}