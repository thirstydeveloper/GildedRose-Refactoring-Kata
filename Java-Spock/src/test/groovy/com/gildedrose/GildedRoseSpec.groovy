package com.gildedrose

import spock.lang.Specification
import spock.lang.PendingFeature

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    /* STANDARD ITEMS */

    def "should preserve item name"() {
        given: "the application any item"
        GildedRose app = inventory(
            anItem(name: 'something', quality: 100, sellIn: 20)
        )

        when: "updating quality"
        app.updateQuality();

        then: "the name is correct"
        onlyItemIn(app).name == 'something'
    }

    def "decrease item quality and sellIn by 1 up to sell-by date for standard items"() {
        given: "a standard item in inventory"
        Item standardItem = anItem(quality: 100, sellIn: 20)
        GildedRose app = inventory(standardItem)

        when: "updating quality"
        app.updateQuality()

        then: "quality reduced by 1"
        standardItem.quality == 99
        standardItem.sellIn == 19
    }

    def "item quality never goes negative"() {
        given: "a zero-quality item"
        Item zeroQuality = anItem(quality: 0, sellIn: 20)
        GildedRose app = inventory(zeroQuality)

        when: "updating quality"
        app.updateQuality()

        then: "quality reduced by 1"
        zeroQuality.quality == 0
    }

    def "item sellIn can go negative"() {
        given: "an item that expires tomorrow"
        Item expiredItem = anItem(quality: 100, sellIn: 0)
        GildedRose app = inventory(expiredItem)

        when: "updating quality"
        app.updateQuality()

        then: "sellIn reduced to -1"
        expiredItem.sellIn == -1
    }

    @PendingFeature
    def "expired items degrade in quality twice as fast"() {
    }

    @PendingFeature
    def 

    static GildedRose inventory(Item... items) {
        new GildedRose(items)
    }

    static Item anItem(Map args) {
        String name = args.get('name', 'nothing special')
        int quality = args.get('quality')
        int sellIn = args.get('sellIn')

        assert quality != null : "missing quality for item"
        assert sellIn != null : "missing sellIn for item"

        new Item(name, sellIn, quality)
    }

    static Item onlyItemIn(GildedRose inventory) {
        assert inventory.items.size() == 1

        inventory.items[0]
    }

//- All items have a SellIn value which denotes the number of days we have to sell the item
//- All items have a Quality value which denotes how valuable the item is
//- At the end of each day, our system lowers both values for every item
//- Once the sell by date has passed, quality degrades twice as fast
//- The quality of an item is never negative
//- "Aged Brie" actually increases in Quality the older it gets
//- The quality of an item is never more than 50
//	- except Sulfuras
//- "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
//	- has a quality of 80
//	- quality never alters
//- "Backstage passes"
//	- like aged brie, increases in Quality as its SellIn value approaches
//	- Quality increases by 2 when there are 10 days or less
//	- increases by 3 when there are 5 days or less
//	- drops to 0 after the concert
}
