package denis.kozhevnikov.basket.discount;

import java.util.Map;

import denis.kozhevnikov.basket.domain.IBuyableBasketItem;

/**
 * Data Structure to store information about a discount.
 */
public class DiscountInfo {
	//percentage
	private Double value;
	//item to be discounted
	private IBuyableBasketItem targetDiscountedItem;
	//items should be in the basket to get this discount
	private Map<IBuyableBasketItem, Integer> discountPredicate;

	public Double getValue() {return value;}
	public void setValue(Double value) {this.value = value;}
	public IBuyableBasketItem getTargetDiscountedItem() {return targetDiscountedItem;}
	public void setTargetDiscountedItem(IBuyableBasketItem targetDiscountedItem) {this.targetDiscountedItem = targetDiscountedItem;}
	public Map<IBuyableBasketItem, Integer> getDiscountPredicate() {return discountPredicate;}
	public void setDiscountPredicate(Map<IBuyableBasketItem, Integer> discountPredicate) {this.discountPredicate = discountPredicate;}
}