package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AgedBrieDecorator implements ItemUpdater {

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = 1;
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }
}
