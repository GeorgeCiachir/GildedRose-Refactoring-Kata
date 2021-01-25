package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @Test
    void testToString() {
        //given
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Some new normal item", 5, 7),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

        //when, then
        Stream.of(items)
                .collect(toMap(item -> item, ProductFactory::newProduct))
                .forEach(((item, product) -> assertThat(item.toString()).isEqualTo(product.toString())));

    }
}
