package denis.kozhevnikov.basket.subtotal;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Constructor;
import denis.kozhevnikov.basket.utils.Implements;

public class SubtotalResultImpl 
	implements SubtotalResult {

	private final PriceOfBasketItem internalPrice;

	@Constructor
	public SubtotalResultImpl(final PriceOfBasketItem aPrice) {
		internalPrice = aPrice;
	}

	@Override
	@Implements(SubtotalResult.class)
	public PriceOfBasketItem getPrice() {
		return internalPrice;
	}
}