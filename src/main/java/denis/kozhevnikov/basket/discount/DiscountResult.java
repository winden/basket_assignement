package denis.kozhevnikov.basket.discount;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;

/**
 * Output result of work of DiscountProcessor.
 * @see DiscountProcessor
 */
public interface DiscountResult {

	/**
	 * Represents UIText information for GUI.
	 * @return text to be shown in the UI.
	 */
	String getUIText();

	/**
	 * Represents discount value in monetary type.
	 * @return price value of the discount.
	 */
	PriceOfBasketItem getPrice();

	/**
	 * Provides information whether any discount has been applied.
	 * @return whether any discount has been applied.
	 */
	boolean isAnyDiscoutApplied();
}