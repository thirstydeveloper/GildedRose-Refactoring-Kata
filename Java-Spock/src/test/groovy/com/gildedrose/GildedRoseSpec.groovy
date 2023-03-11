package com.gildedrose

import spock.lang.Specification

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    /* STANDARD ITEMS */

    def "should preserve item name"() {
        given: "an item"
        Item[] items = [new Item("foo", 0, 0)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the name is correct"
        app.items[0].name == "foo"
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
