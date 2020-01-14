package com.gildedrose;

import com.gildedrose.updater.ItemUpdater;
import com.gildedrose.updater.ItemUpdaterFactory;
import lombok.Setter;

/**
 * Gilded Rose application for managing the quality and due dates of the stock available.
 * A parameter <b>strictNameMatcher</b> can be set as configuration, which defines how strict the application is in
 * matching the item name to a type of Item. The default is strict.
 * {@see ItemUpdaterFactory}
 */
class GildedRose {

    Item[] items;

    @Setter
    private boolean strictNameMatcher = true;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Updates the quality and number of days before sell for all items provided at construction time of the GilderRose
     * application. The change made to the items is based on a single day.
     */
    public void updateQuality() {
        ItemUpdaterFactory itemUpdaterFactory = new ItemUpdaterFactory();
        for (int i = 0; i < items.length; i++) {
            ItemUpdater itemUpdater = itemUpdaterFactory.createUpdater(items[i], strictNameMatcher);
            itemUpdater.updateQuality();
            itemUpdater.updateDaysBeforeSell();
        }
    }
}