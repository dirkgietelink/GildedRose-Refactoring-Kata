package com.gildedrose;

public class ItemUpdaterFactory {

    //    private static final String SULFURAS = "Sulfuras";
    //    private static final String BACKSTAGE_PASS = "Backstage pass";

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    public ItemUpdater createUpdater(Item item) {
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
}
