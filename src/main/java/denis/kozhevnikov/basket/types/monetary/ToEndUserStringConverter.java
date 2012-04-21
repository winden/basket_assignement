package denis.kozhevnikov.basket.types.monetary;

import java.math.BigDecimal;

public class ToEndUserStringConverter {

	public static String convert(final PriceOfBasketItem aItem) {
		if (aItem.compareTo(PriceOfBasketItem.valueOf(BigDecimal.valueOf(1.0))) < 0) {
			return "" + aItem.times(PriceOfBasketItem.valueOf(BigDecimal.valueOf(100))).internalPrice.intValue() + "p";
		}
		return "£"+ aItem.toString();
	}

}
