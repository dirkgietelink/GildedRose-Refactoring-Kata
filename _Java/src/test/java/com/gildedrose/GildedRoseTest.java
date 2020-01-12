package com.gildedrose;

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
    public void updateNormalItemWithQualityZero_ThenQualityStaysZero() {
        Item[] items = new Item[] { new Item("foo", 3, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateNormalItemWithSellInZero_ThenQualityCanBeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
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
