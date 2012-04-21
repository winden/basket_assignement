package denis.kozhevnikov.basket.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.Constructor;

/**
 * First class collection class for product items in the basket.
 */
final public class BuyableBasketItems {
	private Map<IBuyableBasketItem, Integer> internalList;
	
	@Constructor
	public BuyableBasketItems() {
		internalList = new TreeMap<IBuyableBasketItem, Integer>();
	}

	/**
	 * Add product items to the collection.
	 * @param aPrice
	 * @return
	 */
	public boolean add(final IBuyableBasketItem aItem) {
		if (internalList.containsKey(aItem))
			internalList.put(aItem, internalList.get(aItem) +1);
		else
			internalList.put(aItem, 1);
		return true;
	}

	@Override
	public String toString() {
		return internalList.toString();
	}

	public int howMany(final IBuyableBasketItem predicateItem) {
		if (internalList.containsKey(predicateItem))
			return internalList.get(predicateItem).intValue();
		else return 0;
	}

	public PriceOfBasketItem getFullPrice() {
		PriceOfBasketItem result = PriceOfBasketItem.valueOf(BigDecimal.valueOf(0.00));
		for (Map.Entry<IBuyableBasketItem, Integer> entry: internalList.entrySet())
			result = result.plus(
				entry.getKey().getPrice().times(
					PriceOfBasketItem.valueOf(BigDecimal.valueOf(entry.getValue().longValue()))));

		return result;
	}
}