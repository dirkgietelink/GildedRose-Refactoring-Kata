package com.gildedrose.updater;

import com.gildedrose.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BackstagePassDecorator implements ItemUpdater {

    private static final int CONCERT_DATE_FAR_AWAY = 1;
    private static final int CONCERT_DATE_IN_ABOUT_A_WEEK = 2;
    private static final int CONCERT_DATE_VERY_CLOSE = 3;
    private static final int FIVE_DAYS = 5;
    private static final int TEN_DAYS = 10;

    @Getter
    private Item item;

    @Override
    public void updateQuality() {
        int qualityStepFactor = CONCERT_DATE_FAR_AWAY;

        if (item.sellIn <= DUE_DATE) {
            item.quality = MIN_QUALITY;
            return;
        } else if (item.sellIn <= FIVE_DAYS) {
            qualityStepFactor = CONCERT_DATE_VERY_CLOSE;
        } else if (item.sellIn <= TEN_DAYS) {
            qualityStepFactor = CONCERT_DATE_IN_ABOUT_A_WEEK;
        }
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }
}
