package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ItemUpdater implementation for Aged Brie.
 * The quality of Aged Brie increases per day.
 */
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
