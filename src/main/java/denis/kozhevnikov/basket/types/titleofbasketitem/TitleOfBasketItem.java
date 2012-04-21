package denis.kozhevnikov.basket.types.titleofbasketitem;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import denis.kozhevnikov.basket.utils.Constructor;
import denis.kozhevnikov.basket.utils.FactoryMethod;
import denis.kozhevnikov.basket.utils.Implements;

/**
 * Class represents an immutable objects for titles could be placed to the basket.
 * Implementation note: uses TitleOfBasketItemEnum for internal representation.
 * 
 * @see TitleOfBasketItemEnum
 */
final public class TitleOfBasketItem
	implements Comparable<TitleOfBasketItem> {

	TitleOfBasketItemEnum internalTitle;

	@Constructor
	private TitleOfBasketItem(final String aTitle) {
		internalTitle =TitleOfBasketItemEnum.fromValue(aTitle);
	}

	/**
	 * @throws IllegalArgumentException if the parameter is not found in the preset
	 * defined in {@link TitleOfBasketItemEnum}.
	 */
	@FactoryMethod
	public static TitleOfBasketItem valueOf(final String aTitle) {
		return new TitleOfBasketItem(aTitle);
	}

	@Override
	public String toString() {
		return internalTitle.toString();
	}

	@Override
	public boolean equals(final Object aObj) {
		if (this == aObj) return true;
		return aObj instanceof TitleOfBasketItem &&
			new EqualsBuilder().append(internalTitle, ((TitleOfBasketItem)aObj).internalTitle).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(internalTitle).toHashCode();
	}

	@Override
	@Implements(Comparable.class)
	public int compareTo(TitleOfBasketItem aObj) {
		if (this == aObj) return 0;
		return new CompareToBuilder()
		.append(internalTitle.getValue(), ((TitleOfBasketItem)aObj).internalTitle.getValue())
		.toComparison();
	}
}