package denis.kozhevnikov.basket.types.monetary;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import denis.kozhevnikov.basket.utils.Constructor;
import denis.kozhevnikov.basket.utils.FactoryMethod;
import denis.kozhevnikov.basket.utils.Implements;

/**
 * Class represents an immutable objects for price.
 */
final public class PriceOfBasketItem 
	implements Comparable<PriceOfBasketItem> {

	final BigDecimal internalPrice;

	@Constructor
	private PriceOfBasketItem(final BigDecimal aPrice) {
		internalPrice = aPrice;
	}

	@FactoryMethod
	public static PriceOfBasketItem valueOf(final BigDecimal aPrice) {
		return new PriceOfBasketItem(aPrice);
	}

	/**
	 * Method implements operation '+' 
	 * @param aPrice price we add to the given
	 * @return a new price that is a sum of a given and passed as a parameter.
	 */
	public PriceOfBasketItem plus(final PriceOfBasketItem aPrice) {
		return valueOf(this.internalPrice.add(aPrice.internalPrice));
	}

	/**
	 * Method implements operation '-' 
	 * @param aPrice price we subtract to the given
	 * @return a new price that is a subtraction of a given and passed as a parameter.
	 */
	public PriceOfBasketItem minus(final PriceOfBasketItem aPrice) {
		return valueOf(this.internalPrice.subtract(aPrice.internalPrice));
	}

	/**
	 * Method implements operation '*' 
	 * @param aPrice price we multiply to the given
	 * @return a new price that is a multiplication of a given and passed as a parameter.
	 */
	public PriceOfBasketItem times(final PriceOfBasketItem aPrice) {
		return valueOf(this.internalPrice.multiply(aPrice.internalPrice));
	}

	@Override
	public String toString() {
		return new DecimalFormat("#.00").format(internalPrice.doubleValue());
	}

	@Override
	public boolean equals(final Object aObj) {
		if (this == aObj) return true;
		return aObj instanceof PriceOfBasketItem &&
				internalPrice.compareTo(((PriceOfBasketItem)aObj).internalPrice) == 0;
				//we want to compare objects with no taking into account their scale
				//new EqualsBuilder().append(internalPrice, ((PriceOfBasketItem)aObj).internalPrice).isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(internalPrice).toHashCode();
	}

	@Override
	@Implements(Comparable.class)
	public int compareTo(PriceOfBasketItem aObj) {
		if (this == aObj) return 0;
		return new CompareToBuilder()
		.append(internalPrice, ((PriceOfBasketItem)aObj).internalPrice)
		.toComparison();
	}
}