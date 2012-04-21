package denis.kozhevnikov.basket.domain;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.types.titleofbasketitem.TitleOfBasketItem;

/**
 * Interface for items can be placed into the basket.
 */
public interface IBuyableBasketItem {
	PriceOfBasketItem getPrice();
	TitleOfBasketItem getTitle();
}