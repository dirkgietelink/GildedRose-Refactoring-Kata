package com.gildedrose;

public interface ItemUpdater {

    static final int MIN_QUALITY = 0;
    static final int MAX_QUALITY = 50;

    void updateQuality();

    void updateDaysBeforeSell();

}
