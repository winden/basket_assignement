package denis.kozhevnikov.basket.subtotal;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;

/**
 * Processor that works with product items in the basket and calculates sub-total result.
 * @see BuyableBasketItems as input parametr to the processor.
 * @see SubtotalResult as output parametr of this processor.
 */
public interface SubtotalProcessor {

	/**
	 * Calculates Sub total result for the given product items.
	 * @param basket the basket of product items.
	 * @return the sub-total result.
	 */
	SubtotalResult process(final BuyableBasketItems basket);
}