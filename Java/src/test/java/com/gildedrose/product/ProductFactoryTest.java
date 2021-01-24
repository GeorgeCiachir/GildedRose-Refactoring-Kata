package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductFactoryTest {

    @Test
    public void testAppropriateProductCreation() {
        Item dexterityVest = new Item("+5 Dexterity Vest", 10, 20);
        Item elixir = new Item("Elixir of the Mongoose", 5, 7);
        Item someNewNormalItem = new Item("New Normal Item", 5, 7);

        Item agedBrie = new Item("Aged Brie", 2, 0);
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        Item conjuredMana = new Item("Conjured Mana Cake", 3, 6);

        assertThat(ProductFactory.productFor(dexterityVest)).isInstanceOf(NormalProduct.class);
        assertThat(ProductFactory.productFor(elixir)).isInstanceOf(NormalProduct.class);
        assertThat(ProductFactory.productFor(someNewNormalItem)).isInstanceOf(NormalProduct.class);

        assertThat(ProductFactory.productFor(agedBrie)).isInstanceOf(AgedBrie.class);
        assertThat(ProductFactory.productFor(sulfuras)).isInstanceOf(Sulfuras.class);
        assertThat(ProductFactory.productFor(backstagePasses)).isInstanceOf(BackstagePasses.class);
        assertThat(ProductFactory.productFor(conjuredMana)).isInstanceOf(ConjuredMana.class);
    }
}