package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
