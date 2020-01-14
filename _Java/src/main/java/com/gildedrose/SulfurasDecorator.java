package com.gildedrose;

public class SulfurasDecorator implements ItemUpdater {

    private static final int MAX_QUALITY_SULFURAS = 80;

    private Item item;

    public SulfurasDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        item.quality = MAX_QUALITY_SULFURAS;
    }

    @Override
    public void updateDaysBeforeSell() {
        item.sellIn = Integer.MAX_VALUE;
    }

}
