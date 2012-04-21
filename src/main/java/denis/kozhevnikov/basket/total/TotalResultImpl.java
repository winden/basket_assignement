package denis.kozhevnikov.basket.total;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Implements;

public class TotalResultImpl
	implements TotalResult {

	private final boolean anyDiscoutApplied;
	private final PriceOfBasketItem internalPrice;

	public TotalResultImpl(PriceOfBasketItem aPrice, boolean anyDiscoutApplied) {
		this.anyDiscoutApplied = anyDiscoutApplied;
		internalPrice = aPrice;
	}

	@Override
	@Implements(TotalResult.class)
	public boolean isAnyDiscoutApplied() {
		return anyDiscoutApplied;
	}

	@Override
	@Implements(TotalResult.class)
	public PriceOfBasketItem getPrice() {
		return internalPrice;
	}
}