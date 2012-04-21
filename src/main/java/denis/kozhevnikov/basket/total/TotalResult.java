package denis.kozhevnikov.basket.total;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;

/**
 * Output result of work of TotalProcessor.
 * @see TotalProcessor
 */
public interface TotalResult {

	/**
	 * Provides information whether any discount has been applied.
	 * @return whether any discount has been applied.
	 */
	boolean isAnyDiscoutApplied();

	/**
	 * Represents total result in monetary type.
	 * @return total price.
	 */
	PriceOfBasketItem getPrice();
}