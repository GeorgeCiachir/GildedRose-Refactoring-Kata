package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @Test
    void testToString() {
        //given
        Item item = new Item("+5 Dexterity Vest", 10, 20);

        //when
        Product actual = Product.builder(NormalProduct::new).forItem(item);

        //then
        assertThat(actual.toString()).isEqualTo(item.toString());

    }
}
