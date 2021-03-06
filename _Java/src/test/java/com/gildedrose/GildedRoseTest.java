package com.gildedrose;

import com.gildedrose.exception.UnsupportedItemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GildedRoseTest {

    /***** NORMAL ITEM *****/

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
    public void updateNormalItemSellDatePassed_ThenQualityDegradeTimesTwo() {
        Item[] items = new Item[] { new Item("foo", -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void updateNormalItemSellInIsZero_ThenQualityDegradeTimesTwo() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }


    /***** AGED BRIE *****/

    @Test
    public void updateAgedBrie_ThenQualityIncreases() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 5, 3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void updateAgedBrieLessStrictNamed_ThenQualityIncreases() {
        Item[] items = new Item[] {
            new Item("agEd bRie", 5, 3),
            new Item("AGED BRIE", 5, 3),
            new Item("  Aged Brie  ", 5, 3),
            new Item("This is also Aged Brie", 5, 3)};
        GildedRose app = new GildedRose(items);
        app.setStrictNameMatcher(false);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(4, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
        assertEquals(4, app.items[3].quality);
    }

    @Test
    public void updateAgedBrie_ThenQualityCannotExceedFifty() {
        Item[] items = new Item[] {new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }


    /*****  SULFURAS *****/

    @Test
    public void updateSulfuras_ThenSellDateNeverChangesAndAlwaysPositive() {
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(Integer.MAX_VALUE, items[0].sellIn);
    }

    @Test
    public void updateSulfuras_ThenQualityIsAlwaysEighty() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 5, 13),
            new Item("Sulfuras, Hand of Ragnaros", 5, 80),
        };
        assertEquals(13, items[0].quality);
        assertEquals(80, items[1].quality);
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
    }

    @Test
    public void updateSulfurasUnstrictNamed_ThenQualityIsAlwaysEighty() {
        Item[] items = new Item[] {
            new Item("Sulfuras", 5, 80),
            new Item("Another Sulfuras", 5, 13),
        };
        assertEquals(80, items[0].quality);
        assertEquals(13, items[1].quality);
        GildedRose app = new GildedRose(items);
        app.setStrictNameMatcher(false);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(80, items[1].quality);
    }


    /***** BACKSTAGE PASSES *****/

    @Test
    public void updateBackstagePassesMoreThan10Days_ThenQualityIncreasesBy1() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void updateBackstagePassesUnstrictNamedMoreThan10Days_ThenQualityIncreasesBy1() {
        Item[] items = new Item[] {
            new Item("Backstage pass", 11, 3),
            new Item("Backstage passes", 12, 3),
            new Item("backSTage paSSeS in a different case", 13, 3),
            new Item("Backstage passes to Whatever concert", 14, 3),
            new Item("Here are some more backstage passes", 15, 3)};
        GildedRose app = new GildedRose(items);
        app.setStrictNameMatcher(false);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(4, app.items[1].quality);
        assertEquals(4, app.items[2].quality);
        assertEquals(4, app.items[3].quality);
        assertEquals(4, app.items[4].quality);
    }

    @Test
    public void updateBackstagePassesBetween5and10DaysInclusive_ThenQualityIncreasesBy2() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
        assertEquals(5, app.items[1].quality);
    }

    @Test
    public void updateBackstagePassesLessThan5DaysInclusive_ThenQualityIncreasesBy3() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(7, app.items[1].quality);
    }

    @Test
    public void updateBackstagePassesZeroOrLessDaysLeft_ThenQualityIsZero() {
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
    public void updateBackstagePasses_ThenQualityCannotExceedFifty() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 20, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 8, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 4, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
        assertEquals(50, app.items[2].quality);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
        assertEquals(50, app.items[2].quality);
    }


    /***** CONJURED ITEM *****/

    @Test
    public void updateConjuredItem_ThenDegradeSellInAndQualityByTwo() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void updateConjuredItemWithQualityZeroOrOne_ThenQualityCannotBeNegative() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 3, 0),
            new Item("Conjured Mana Cake", 3, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    public void updateConjuredItemSellDatePassedOrZero_ThenQualityDegradeWithFour() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 0, 5),
            new Item("Conjured Mana Cake", -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
        assertEquals(-2, app.items[1].sellIn);
        assertEquals(1, app.items[1].quality);
    }


    /***** ORIGINAL FROM TexttestFixture.java *****/

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
        assertEquals(Integer.MAX_VALUE, items[3].sellIn);
        assertEquals(80, items[3].quality);
        assertEquals("Sulfuras, Hand of Ragnaros", items[4].name);
        assertEquals(Integer.MAX_VALUE, items[4].sellIn);
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
        assertEquals(4, items[8].quality);
    }


    /***** TECHNICAL EDGE CASES *****/

    @Test
    public void updateNullItem_Then() {
        Item[] items = new Item[] { null };
        GildedRose app = new GildedRose(items);
        Exception exception = assertThrows(
            UnsupportedItemException.class,
            () -> app.updateQuality());

        assertEquals("Item cannot be null", exception.getMessage());
    }

    @Test
    public void updateItemWithNullName_Then() {
        Item[] items = new Item[] {
            new Item(null, 1, 5) };
        GildedRose app = new GildedRose(items);
        Exception exception = assertThrows(
            UnsupportedItemException.class,
            () -> app.updateQuality());

        assertEquals("Item name cannot be null", exception.getMessage());
    }

}
