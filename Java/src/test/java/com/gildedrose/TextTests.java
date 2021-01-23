package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.gildedrose.TestUtils.createResultFile;
import static com.gildedrose.TestUtils.getExpectedFile;
import static com.gildedrose.TestUtils.writeEvolution;
import static org.assertj.core.api.Assertions.assertThat;

public class TextTests {

    private static final String EVOLUTION_TXT = "evolution.txt";
    private static final String BENCHMARK_TXT = "benchmark.txt";
    private static final int DAYS = 45;

    @Test
    public void testEvolution() {
        //given
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};


        //when
        GildedRose app = new GildedRose(items);
        File resultFile = createResultFile(EVOLUTION_TXT);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < DAYS; i++) {
            result.append("-------- day " + i + " --------").append("\n");
            result.append("name, sellIn, quality").append("\n");
            for (Item item : items) {
                result.append(item).append("\n");
            }
            result.append("\n");
            app.updateQuality();
        }
        writeEvolution(resultFile, result.toString());

        //then
        File expected = getExpectedFile(BENCHMARK_TXT);
        assertThat(resultFile).hasSameTextualContentAs(expected);
    }
}
