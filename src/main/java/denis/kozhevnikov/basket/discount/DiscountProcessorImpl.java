package denis.kozhevnikov.basket.discount;

import java.math.BigDecimal;
import java.util.List;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Implements;

public class DiscountProcessorImpl
	implements DiscountProcessor {

	private List<DiscountRule> rules;
	public void setRules(List<DiscountRule> rules) {this.rules = rules;}

	@Override
	@Implements(DiscountProcessor.class)
	public DiscountResult process(BuyableBasketItems basket) {
		String resultUIText = "";
		PriceOfBasketItem resultPrice = PriceOfBasketItem.valueOf(BigDecimal.valueOf(0.00));
		boolean resultAnyDiscountApplied = false;

		for (DiscountRule rule : rules) {
			DiscountRuleResult ruleResult = rule.apply(basket);
			resultAnyDiscountApplied = resultAnyDiscountApplied || ruleResult.isDiscountApplied();
			resultPrice = resultPrice.plus(ruleResult.getDiscountValue());
			resultUIText = addUITextAboutThisDiscount(resultUIText, ruleResult);
		}

		if (resultAnyDiscountApplied) resultUIText = removeLastCharacterWhichIsNewLineChar(resultUIText);

		return new DiscountResultImpl(resultUIText, resultPrice, resultAnyDiscountApplied);
	}

	private String addUITextAboutThisDiscount(String resultUIText, DiscountRuleResult ruleResult) {
		return resultUIText + ruleResult.getUIText() + (ruleResult.isDiscountApplied()? "\n": "");
	}

	private String removeLastCharacterWhichIsNewLineChar(String resultUIText) {
		return resultUIText.substring(0, (resultUIText.length() - 1));
	}
}