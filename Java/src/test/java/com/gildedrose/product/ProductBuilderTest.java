package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ProductBuilderTest {

    @Test
    void verifyValidatorsAreApplied() {
        //given
        int minimumQuality = 0;
        Item item = new Item("Some name", 2, minimumQuality - 1);
        ProductBuilder productBuilder = new ProductBuilder(ProductType.NORMAL);

        try {
            //when
            productBuilder.buildFrom(item);
            fail("should validate quality and throw IllegalArgumentException if lower than zero");
        } catch (Exception e) {
            //then
            //successfully tested quality at object creation
        }
    }
}