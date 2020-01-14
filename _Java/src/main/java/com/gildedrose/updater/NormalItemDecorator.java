package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This represents a "normal" ItemUpdater. It behaves like most Items: The quality decreases by 1 per day, or by 2 when
 * the due date  has passed.
 */
@AllArgsConstructor
public class NormalItemDecorator implements ItemUpdater {

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = (item.sellIn > DUE_DATE) ? -1 : -2;
        item.quality = Math.max(item.quality + qualityStepFactor, MIN_QUALITY);
    }
}
