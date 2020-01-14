package com.gildedrose;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class GildedRose {

    Item[] items;

    public void updateQuality() {
        ItemUpdaterFactory itemUpdaterFactory = new ItemUpdaterFactory();
        for (int i = 0; i < items.length; i++) {
            ItemUpdater itemUpdater = itemUpdaterFactory.createUpdater(items[i]);
            itemUpdater.updateQuality();
            itemUpdater.updateDaysBeforeSell();
        }
    }
}