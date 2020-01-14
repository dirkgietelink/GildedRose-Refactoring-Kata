package com.gildedrose;

public class AgedBrieDecorator implements ItemUpdater {

    private Item item;

    public AgedBrieDecorator(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        int qualityStepFactor = 1;
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }
    @Override
    public void updateDaysBeforeSell() {
        item.sellIn--;
    }
}
