package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class ProductFactory {

    private static final Map<String, Function<Item, ? extends Product>> BUILDERS = getProductConstructors();

    private static Map<String, Function<Item, ? extends Product>> getProductConstructors() {
        return Arrays.stream(ProductType.values())
                .collect(toMap(ProductType::getItemName, ProductType::getProductConstructor));
    }

    public static Product productFor(Item item) {
        return BUILDERS
                .getOrDefault(item.name, defaultProductConstructor())
                .apply(item);
    }

    private static Function<Item, ? extends Product> defaultProductConstructor() {
        return BUILDERS.get(ProductType.NORMAL.getItemName());
    }

}
