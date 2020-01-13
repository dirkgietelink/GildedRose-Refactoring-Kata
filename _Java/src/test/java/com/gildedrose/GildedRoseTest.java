package com.gildedrose;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    public void updateNormalItem_ThenDegradeSellInAndQualityByOne() {
        Item[] items = new Item[] { new Item("foo", 3, 1) };
        GildedRose app = new GildedRose(items);
        assertEquals("foo", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateNormalItemWithQualityZero_ThenQualityCannotBeNegative() {
        Item[] items = new Item[] { new Item("foo", 3, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateNormalItemWithSellInZero_ThenSellInCanBeNegativeAndQualityMinusTwo() {
        Item[] items = new Item[] { new Item("foo", 0, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void sellDatePassed_ThenQualityDegradeTimesTwo() {
        Item[] items = new Item[] { new Item("foo", -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void sellInIsZero_ThenQualityDegradeTimesTwo() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void agedBrieOlder_ThenQualityIncreases() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 5, 3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Disabled
    @Test
    public void agedBrieLessStrictNamed_ThenQualityIncreases() {
        Item[] items = new Item[] {
            new Item("agEd bRie", 5, 3),
            new Item("AGED BRIE", 5, 3),
            new Item("  Aged Brie  ", 5, 3),
            new Item("This is also Aged Brie", 5, 3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(4, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
        assertEquals(4, app.items[3].quality);
    }

    @Test
    public void agedBrieOlder_ThenQualityCannotExceedFifty() {
        Item[] items = new Item[] {new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void sulfurasSellDateNeverChangesAndAlwaysPositive() {
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, items[0].sellIn);
    }

    @Test
    public void sulfurasQualityIsAlwaysEighty() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 5, 80),
            new Item("Sulfuras, Hand of Ragnaros", 5, 13),
        };
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
    }

    @Disabled
    @Test
    public void sulfurasLessStrictNamedQualityIsAlwaysEighty() {
        Item[] items = new Item[] {
            new Item("Sulfuras", 5, 80),
            new Item("Another Sulfuras", 5, 13),
        };
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
    }

    @Test
    public void backstagePassesMoreThan10Days_ThenQualityIncreasesBy1() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Disabled
    @Test
    public void backstagePassesLessStrictNamedMoreThan10Days_ThenQualityIncreasesBy1() {
        Item[] items = new Item[] {
            new Item("Backstage pass", 11, 3),
            new Item("Backstage passes", 12, 3),
            new Item("backSTage paSSeS in a different case", 13, 3),
            new Item("Backstage passes to Whatever concert", 14, 3),
            new Item("Here are some more backstage passes", 15, 3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(4, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
        assertEquals(4, app.items[3].quality);
        assertEquals(4, app.items[4].quality);
    }

    @Test
    public void backstagePassesBetween5and10DaysInclusive_ThenQualityIncreasesBy2() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
        assertEquals(5, app.items[1].quality);
    }

    @Test
    public void backstagePassesLessThan5DaysInclusive_ThenQualityIncreasesBy3() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(7, app.items[1].quality);
    }

    @Test
    public void backstagePassesZeroOrLessDaysLeft_ThenQualityIsZero() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", -2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
    }

    @Test
    public void backstagePassesOlder_ThenQualityCannotExceedFifty() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void updateItemListFromOriginalApplication() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
        assertEquals("Aged Brie", items[1].name);
        assertEquals(1, items[1].sellIn);
        assertEquals(1, items[1].quality);
        assertEquals("Elixir of the Mongoose", items[2].name);
        assertEquals(4, items[2].sellIn);
        assertEquals(6, items[2].quality);
        assertEquals("Sulfuras, Hand of Ragnaros", items[3].name);
        assertEquals(0, items[3].sellIn);
        assertEquals(80, items[3].quality);
        assertEquals("Sulfuras, Hand of Ragnaros", items[4].name);
        assertEquals(-1, items[4].sellIn);
        assertEquals(80, items[4].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[5].name);
        assertEquals(14, items[5].sellIn);
        assertEquals(21, items[5].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[6].name);
        assertEquals(9, items[6].sellIn);
        assertEquals(50, items[6].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[7].name);
        assertEquals(4, items[7].sellIn);
        assertEquals(50, items[7].quality);
        assertEquals("Conjured Mana Cake", items[8].name);
        assertEquals(2, items[8].sellIn);
        assertEquals(5, items[8].quality);
    }

}
