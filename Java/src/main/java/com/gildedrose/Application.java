package com.gildedrose;

public class Application {

    public static void main(String[] args) {

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6),
                new Item("Conjured Mana Cake", 10, 50)
        };

        GildedRose gildedRose = new GildedRose(items);

        int daysToPass = 2 + 1;//need to add one for the for loop
        if (args.length > 0) {

            if (args.length > 1) {
                System.out.println("Only the first argument will be used");
            }

            int intendedDays = Integer.parseInt(args[0]);

            if (intendedDays < 0) {
                throw new IllegalArgumentException("Please provide a non negative number");
            }

            if (intendedDays > 100) {
                String message = "50 days or so should be enough to test everything, " +
                        "but just for the fun of it I decided to allow 100 days as input. " +
                        "Please don't go overboard.";
                throw new IllegalArgumentException(message);
            }

            daysToPass = intendedDays + 1;//need to add one for the for loop

        }

        for (int i = 0; i < daysToPass; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            gildedRose.updateQuality();
        }
    }
}
