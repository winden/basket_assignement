package denis.kozhevnikov.basket.subtotal;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;

/**
 * Output result of work of SubtotalProcessor.
 * @see SubtotalProcessor
 * @see PriceOfBasketItem
 */
public interface SubtotalResult {

	/**
	 * Represents sub total result in monetary type.
	 * @return sub-total price.
	 */
	PriceOfBasketItem getPrice();
}