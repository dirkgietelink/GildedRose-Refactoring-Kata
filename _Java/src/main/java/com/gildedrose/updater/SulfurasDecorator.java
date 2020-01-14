package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ItemUpdater implementation for Sulfuras, whatever that may be.
 * Sulfuras is special as its quality is always 80, and it has no sell date.
 */
@AllArgsConstructor
public class SulfurasDecorator implements ItemUpdater {

    private static final int MAX_QUALITY_SULFURAS = 80;

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        item.quality = MAX_QUALITY_SULFURAS;
    }

    @Override
    public void updateDaysBeforeSell() {
        item.sellIn = Integer.MAX_VALUE;
    }

}
