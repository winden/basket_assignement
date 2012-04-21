package denis.kozhevnikov.basket.discount;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.domain.IBuyableBasketItem;
import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.types.monetary.ToEndUserStringConverter;
import denis.kozhevnikov.basket.utils.Constructor;

/**
 * Discount rule to be applied to a basket of product items.
 */
public class DiscountRule {

	private final DiscountInfo discount;

	@Constructor
	public DiscountRule(final DiscountInfo aInfo) {
		discount = aInfo;
	}

	public DiscountRuleResult apply(final BuyableBasketItems aItems) {
		int timesOfDiscount = Integer.MAX_VALUE;
		for (IBuyableBasketItem predicateItem: discount.getDiscountPredicate().keySet()) {

			int times = howManyTimeThisPredicateItemIsInTheBasket(aItems,
					predicateItem, discount.getDiscountPredicate().get(predicateItem));

			timesOfDiscount = times < timesOfDiscount? times: timesOfDiscount;
			if (times == 0) 
				return new DiscountRuleResult("", PriceOfBasketItem.valueOf(BigDecimal.valueOf(0.00)),false);
		}

		PriceOfBasketItem discountValue = 
				discount.getTargetDiscountedItem().getPrice().times(PriceOfBasketItem.valueOf(
						BigDecimal.valueOf(discount.getValue() * timesOfDiscount)));

		String UIText = discount.getTargetDiscountedItem().getTitle() + " " 
						+ new DecimalFormat("#.#").format(100*discount.getValue()) + "% off:"
						+ " -" + ToEndUserStringConverter.convert(discountValue);

		return new DiscountRuleResult(UIText, discountValue, true);
	}

	private int howManyTimeThisPredicateItemIsInTheBasket(
			final BuyableBasketItems aItems,
			final IBuyableBasketItem predicateItem,
			final Integer countOfItemsToGetDiscount) {

		return aItems.howMany(predicateItem) / countOfItemsToGetDiscount.intValue();
	}
}