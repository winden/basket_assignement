package denis.kozhevnikov.basket.total;

import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;
import denis.kozhevnikov.basket.utils.Implements;

public class TotalProcessorImpl
	implements TotalProcessor {

	@Override
	@Implements(TotalProcessor.class)
	public TotalResult process(SubtotalResult subtotalValue, DiscountResult discountValue) {
		return new TotalResultImpl(
			subtotalValue.getPrice().minus(discountValue.getPrice()),
			discountValue.isAnyDiscoutApplied());
	}
}