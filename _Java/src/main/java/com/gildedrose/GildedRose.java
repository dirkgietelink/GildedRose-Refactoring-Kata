package com.gildedrose;

import com.gildedrose.updater.ItemUpdater;
import com.gildedrose.updater.ItemUpdaterFactory;
import lombok.AllArgsConstructor;

/**
 * Gilded Rose application for managing the quality and due dates of the stock available.
 */
@AllArgsConstructor
class GildedRose {

    Item[] items;

    /**
     * Updates the quality and number of days before sell for all items provided at construction time of the GilderRose
     * application. The change made to the items is based on a single day.
     */
    public void updateQuality() {
        ItemUpdaterFactory itemUpdaterFactory = new ItemUpdaterFactory();
        for (int i = 0; i < items.length; i++) {
            ItemUpdater itemUpdater = itemUpdaterFactory.createUpdater(items[i]);
            itemUpdater.updateQuality();
            itemUpdater.updateDaysBeforeSell();
        }
    }
}