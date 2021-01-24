package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.function.Function;

public enum ProductType {

    NORMAL("Normal", NormalProduct::new),
    AGED_BRIE("Aged Brie", AgedBrie::new),
    SULFURAS("Sulfuras, Hand of Ragnaros", Sulfuras::new),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", BackstagePasses::new),
    CONJURED("Conjured Mana Cake", ConjuredMana::new);

    private final String itemName;
    private final Function<Item, ? extends Product> productConstructor;

    ProductType(String itemName, Function<Item, ? extends Product> productConstructor) {
        this.itemName = itemName;
        this.productConstructor = productConstructor;
    }

    public String getItemName() {
        return itemName;
    }

    public Function<Item, ? extends Product> getProductConstructor() {
        return productConstructor;
    }
}
