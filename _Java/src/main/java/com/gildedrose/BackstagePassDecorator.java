package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BackstagePassDecorator implements ItemUpdater {

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = 1;
        if (item.sellIn <= 0) {
            item.quality = 0;
            return;
        } else if (item.sellIn <= 5) {
            qualityStepFactor = 3;
        } else if (item.sellIn <= 10) {
            qualityStepFactor = 2;
        }
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }
}
