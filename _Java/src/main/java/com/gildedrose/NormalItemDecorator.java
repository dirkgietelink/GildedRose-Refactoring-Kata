package com.gildedrose;

public class NormalItemDecorator implements ItemUpdater {

    private Item item;

    public NormalItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        int qualityStepFactor = (item.sellIn > 0) ? -1 : -2;
        item.quality = Math.max(item.quality + qualityStepFactor, MIN_QUALITY);
    }

    @Override
    public void updateDaysBeforeSell() {

    }
}
