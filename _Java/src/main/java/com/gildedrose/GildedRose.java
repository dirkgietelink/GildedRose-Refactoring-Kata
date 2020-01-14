package com.gildedrose;

class GildedRose {

    //    private static final String SULFURAS = "Sulfuras";
    //    private static final String BACKSTAGE_PASS = "Backstage pass";
    //    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            ItemUpdater itemUpdater = null;
            switch (item.name) {
                case AGED_BRIE:
                    itemUpdater = new AgedBrieDecorator(item);
                    itemUpdater.updateQuality();
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                    itemUpdater = new BackstagePassDecorator(item);
                    itemUpdater.updateQuality();
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    itemUpdater = new SulfurasDecorator(item);
                    itemUpdater.updateQuality();
                    break;
                default:
                    itemUpdater = new NormalItemDecorator(item);
                    itemUpdater.updateQuality();
                    break;
            }
            itemUpdater.updateDaysBeforeSell();
        }
    }



}