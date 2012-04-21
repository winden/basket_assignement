package denis.kozhevnikov.basket.discount;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Constructor;

/**
 * Data Structure that holds result of applying a discount rule to the basket.
 *
 *@see DiscountRule
 */
class DiscountRuleResult {
	private final String UIText;
	private final PriceOfBasketItem discountValue;
	private final boolean isDiscountApplied;

	@Constructor
	public DiscountRuleResult(final String aText, final PriceOfBasketItem aValue, final boolean isApplied) {
		discountValue = aValue;
		UIText = aText;
		isDiscountApplied = isApplied;
	}

	public String getUIText() { return UIText;}
	public PriceOfBasketItem getDiscountValue() { return discountValue;}
	public boolean isDiscountApplied() {return isDiscountApplied;}
}