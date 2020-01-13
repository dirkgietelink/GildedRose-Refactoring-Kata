package com.gildedrose;

class GildedRose {

    //    private static final String SULFURAS = "Sulfuras";
    //    private static final String BACKSTAGE_PASS = "Backstage pass";
    //    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private static final int MAX_QUALITY_SULFURAS = 80;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            switch (item.name) {
                case AGED_BRIE:
                    updateAgedBrie(item);
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                    updateBackstagePasses(item);
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    updateSulfuras(item);
                    item.sellIn = Integer.MAX_VALUE;
                    return;
                default:
                    updateNormalItem(item);
                    break;
            }
            item.sellIn--;
        }
    }

    private void updateAgedBrie(Item item) {
        int qualityStepFactor = 1;
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }

    private void updateBackstagePasses(Item item) {
        int qualityStepFactor = 1;
        if (item.sellIn <= 0) {
            item.quality = 0;
            return;
        } else if (item.sellIn <= 5) {
            qualityStepFactor = 3;
        } else if (item.sellIn <= 10) {
            qualityStepFactor = 2;
        }
        item.quality = Math.min(item.quality + qualityStepFactor, MAX_QUALITY);
    }

    private void updateSulfuras(Item item) {
        item.quality = MAX_QUALITY_SULFURAS;
    }

    private void updateNormalItem(Item item) {
        int qualityStepFactor = (item.sellIn > 0) ? -1 : -2;
        item.quality = Math.max(item.quality + qualityStepFactor, MIN_QUALITY);
    }

    public void updateQuality_OldMess() {
        // old code
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals(AGED_BRIE)
                    && !items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals(AGED_BRIE)) {
                    if (!items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}