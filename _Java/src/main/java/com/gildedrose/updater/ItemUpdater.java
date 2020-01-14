package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * The ItemUpdater is an interface for classes that decorate the legacy class {@link Item}, which cannot be used
 * because of the goblin in  the corner.
 * It wraps the Item, and defines methods for updating the quality and the days before sell. The latter comes with a
 * default method.
 */
public interface ItemUpdater {

    int DUE_DATE = 0;
    int MIN_QUALITY = 0;
    int MAX_QUALITY = 50;

    /**
     * Retrieves the wrapped {@link Item}
     * @return
     */
    Item getItem();

    /**
     * Updates the quality of the decorated item.
     */
    void updateQuality();

    /**
     * Updates the days before sell of the decorated item.
     */
    default void updateDaysBeforeSell() {
        getItem().sellIn--;
    }

}
