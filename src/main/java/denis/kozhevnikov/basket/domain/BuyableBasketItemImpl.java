package denis.kozhevnikov.basket.domain;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.types.titleofbasketitem.TitleOfBasketItem;
import denis.kozhevnikov.basket.utils.Implements;

final public class BuyableBasketItemImpl 
	implements IBuyableBasketItem, Comparable<BuyableBasketItemImpl> {
	
	private PriceOfBasketItem price;
	private TitleOfBasketItem title;

	@Override
	@Implements(IBuyableBasketItem.class)
	public PriceOfBasketItem getPrice() {
		return price;
	}

	@Override
	@Implements(IBuyableBasketItem.class)
	public TitleOfBasketItem getTitle() {
		return title;
	}

	public void setPrice(final PriceOfBasketItem price) {
		this.price = price;
	}

	public void setTitle(final TitleOfBasketItem title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Item[Title:" + title.toString() +",Price:" +price.toString()+ "]";
	}

	@Override
	public boolean equals(final Object aObj) {
		if (this == aObj) return true;
		return aObj instanceof BuyableBasketItemImpl &&
			new EqualsBuilder()
			.append(price, ((BuyableBasketItemImpl)aObj).price)
			.append(title, ((BuyableBasketItemImpl)aObj).title)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(price).append(title).toHashCode();
	}	

	@Override
	@Implements(Comparable.class)
	public int compareTo(BuyableBasketItemImpl aObj) {
		if (this == aObj) return 0;
		return new CompareToBuilder()
		.append(price, ((BuyableBasketItemImpl)aObj).price)
		.append(title, ((BuyableBasketItemImpl)aObj).title)
		.toComparison();
	}
}