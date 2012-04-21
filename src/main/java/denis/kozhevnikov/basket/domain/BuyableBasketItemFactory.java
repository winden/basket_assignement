package denis.kozhevnikov.basket.domain;

import denis.kozhevnikov.basket.tools.spring.AppContext;
import denis.kozhevnikov.basket.types.titleofbasketitem.TitleOfBasketItem;
import denis.kozhevnikov.basket.utils.FactoryMethod;

/**
 * Factory to create {@link IBuyableBasketItem}.
 */
public final class BuyableBasketItemFactory {

	private static final BuyableBasketItemImpl MILK = (BuyableBasketItemImpl) AppContext.getBean("milkPrototype");
	private static final BuyableBasketItemImpl BREAD = (BuyableBasketItemImpl)AppContext.getBean("breadPrototype");
	private static final BuyableBasketItemImpl APPLE = (BuyableBasketItemImpl) AppContext.getBean("applePrototype");
	private static final BuyableBasketItemImpl SOUP = (BuyableBasketItemImpl)AppContext.getBean("soupPrototype");

	@FactoryMethod
	public static IBuyableBasketItem valueOf(final String aValue)
			throws IllegalArgumentException {

		return getPrototypeByTitleName(TitleOfBasketItem.valueOf(aValue));
	}

	/**
	 * Method that maps title to the product type.
	 */
	private static BuyableBasketItemImpl getPrototypeByTitleName(final TitleOfBasketItem aTitle) {
		if (aTitle.equals(TitleOfBasketItem.valueOf("Apples"))) return APPLE;
		if (aTitle.equals(TitleOfBasketItem.valueOf("Milk"))) return MILK;
		if (aTitle.equals(TitleOfBasketItem.valueOf("Soup"))) return SOUP;
		if (aTitle.equals(TitleOfBasketItem.valueOf("Bread"))) return BREAD;

		throw new IllegalArgumentException(aTitle.toString());
	}
}