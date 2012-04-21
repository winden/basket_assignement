package denis.kozhevnikov.basket.total;

import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;

/**
 * Processor that works with sub-total and discount information and calculates total result.
 * @see DiscountResult as input parametr to the processor.
 * @see SubtotalResult as input parametr to this processor.
 * @see TotalResult as output parametr of this processor.
 */
public interface TotalProcessor {

	/**
	 * Calculates total result price
	 * @param subtotalValue sub-total information
	 * @param discountValue discount information
	 * @return the total result price.
	 */
	TotalResult process(final SubtotalResult subtotalValue, final DiscountResult discountValue);
}