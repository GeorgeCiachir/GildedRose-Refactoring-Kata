package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ProductBuilderTest {

    @Test
    void validateQualityNotBelowZero() {
        //given
        Item item = new Item("Some name", 2, -1);
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