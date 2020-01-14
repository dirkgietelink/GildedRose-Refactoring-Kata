package com.gildedrose.updater;

import com.gildedrose.Item;

public interface ItemUpdater {

    int DUE_DATE = 0;
    int MIN_QUALITY = 0;
    int MAX_QUALITY = 50;

    Item getItem();

    void updateQuality();

    default void updateDaysBeforeSell() {
        getItem().sellIn--;
    }

}
