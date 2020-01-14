package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.exception.UnsupportedItemException;

/**
 * Factory class for creating {@link ItemUpdater}s.
 */
public class ItemUpdaterFactory {

    private static final String SULFURAS = "sulfuras";
    private static final String BACKSTAGE_PASS = "backstage pass";
    private static final String BRIE = "brie";
    private static final String CONJURED = "conjured";

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    /**
     * Creates a new {@link ItemUpdater} implementation based on the name of the provided {@link Item}.
     *
     * @param item
     * @param strictNameMatcher
     * @return
     * @throws UnsupportedItemException
     */
    public ItemUpdater createUpdater(Item item, boolean strictNameMatcher) {
        if (item == null) {
            throw new UnsupportedItemException("Item cannot be null");
        } else if (item.name == null) {
            throw new UnsupportedItemException("Item name cannot be null");
        }
        if (strictNameMatcher) {
            return createUpdaterStrict(item);
        } else {
            return createUpdaterLenient(item);
        }
    }

    /**
     * Creates ItemUpdater by matching exact String.
     *
     * @param item
     * @return
     */
    private ItemUpdater createUpdaterStrict(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return new AgedBrieDecorator(item);
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                return new BackstagePassDecorator(item);
            case SULFURAS_HAND_OF_RAGNAROS:
                return new SulfurasDecorator(item);
            case CONJURED_MANA_CAKE:
                return new ConjuredItemDecorator(item);
            default:
                return new NormalItemDecorator(item);
        }
    }

    /**
     * Creates ItemUpdater by matching if a shortened string is contained, while ignoring case sensitivity.
     *
     * @param item
     * @return
     */
    private ItemUpdater createUpdaterLenient(Item item) {
        String lowerCaseItemName = item.name.toLowerCase();

        if (lowerCaseItemName.contains(BRIE)) {
            return new AgedBrieDecorator(item);
        } else if (lowerCaseItemName.contains(BACKSTAGE_PASS)) {
            return new BackstagePassDecorator(item);
        } else if (lowerCaseItemName.contains(SULFURAS)) {
            return new SulfurasDecorator(item);
        } else if (lowerCaseItemName.contains(CONJURED)) {
            return new ConjuredItemDecorator(item);
        }
        return new NormalItemDecorator(item);
    }
}
