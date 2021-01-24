package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

class ProductBuilderTest {

    @Test
    void validateQualityNotBelowZero() {
        //given
        Item item = new Item("Some name", 2, -1);
        Function<Item, Product> productConstructor = ProductType.NORMAL.getProductConstructor();
        ProductBuilder productBuilder = Product.builder(productConstructor);

        try {
            //when
            productBuilder.forItem(item);
            fail("should validate quality and throw IllegalArgumentException if lower than zero");
        } catch (Exception e) {
            //then
            //successfully tested quality at object creation
        }
    }
}