package com.gildedrose;

public class SulfurasDecorator implements ItemUpdater {

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

    }
}
