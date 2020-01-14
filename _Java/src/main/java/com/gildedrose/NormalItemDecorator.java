package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NormalItemDecorator implements ItemUpdater {

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = (item.sellIn > 0) ? -1 : -2;
        item.quality = Math.max(item.quality + qualityStepFactor, MIN_QUALITY);
    }
}
