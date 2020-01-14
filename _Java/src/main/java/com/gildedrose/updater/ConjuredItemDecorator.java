package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ConjuredItemDecorator implements ItemUpdater {

    @Getter
    Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = (item.sellIn > DUE_DATE) ? -2 : -4;
        item.quality = Math.max(item.quality + qualityStepFactor, MIN_QUALITY);
    }
}
