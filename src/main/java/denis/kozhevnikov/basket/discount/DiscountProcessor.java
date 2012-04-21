package denis.kozhevnikov.basket.discount;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;

/**
 * Processor that works with product items in the basket and calculates discount for it.
 * @see DiscountResult as output result of the processor.
 * @see BuyableBasketItems as input parametr of this processor.
 */
public interface DiscountProcessor {

	/**
	 * Calculates discount for the given product items basket.
	 * @param basket the product item basket.
	 * @return the discount information.
	 */
	DiscountResult process(final BuyableBasketItems basket);
}