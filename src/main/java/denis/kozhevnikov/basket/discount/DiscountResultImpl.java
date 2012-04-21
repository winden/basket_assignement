package denis.kozhevnikov.basket.discount;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Implements;

public class DiscountResultImpl
	implements DiscountResult {

	private final String uiText;
	private final PriceOfBasketItem price;
	private final boolean anyDiscountApplied;

	public DiscountResultImpl(String aUIText, PriceOfBasketItem aPrice, boolean aAnyDiscountApplied) {
		uiText = aUIText;
		price = aPrice;
		anyDiscountApplied = aAnyDiscountApplied;
	}

	@Override
	@Implements(DiscountResult.class)
	public String getUIText() {
		return uiText;
	}

	@Override
	@Implements(DiscountResult.class)
	public PriceOfBasketItem getPrice() {
		return price;
	}

	@Override
	@Implements(DiscountResult.class)
	public boolean isAnyDiscoutApplied() {
		return anyDiscountApplied;
	}
}