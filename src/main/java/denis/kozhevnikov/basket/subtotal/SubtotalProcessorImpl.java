package denis.kozhevnikov.basket.subtotal;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.utils.Implements;

public class SubtotalProcessorImpl
	implements SubtotalProcessor {

	@Override
	@Implements(SubtotalProcessor.class)
	public SubtotalResult process(BuyableBasketItems basket) {
		return new SubtotalResultImpl(basket.getFullPrice());
	}
}