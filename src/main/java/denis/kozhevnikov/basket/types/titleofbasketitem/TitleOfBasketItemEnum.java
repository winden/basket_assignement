package denis.kozhevnikov.basket.types.titleofbasketitem;

import denis.kozhevnikov.basket.utils.Constructor;
import denis.kozhevnikov.basket.utils.FactoryMethod;

/**
 * Representation of the Items can be placed into the basket.
 */
enum TitleOfBasketItemEnum {
	Apples("Apples"),
	Milk("Milk"),
	Bread("Bread"),
	Soup("Soup");

	private String value;

	@Constructor
	TitleOfBasketItemEnum(final String aValue) { value = aValue;}

	/**
	 * Enum is capable of being represented in text values.
	 * @return string representation of the enum value.
	 */
	public String getValue() { return value; }

	/**
	 * Method that converts preset of values to a Title type.
	 * @param aValue that we'd like to convert into a value.
	 * @return a value, or throw IllegalArgumentException if the parameter
	 * is not found in the preset.
	 */
	@FactoryMethod
	public static TitleOfBasketItemEnum fromValue(final String aValue) {
		for (TitleOfBasketItemEnum c: TitleOfBasketItemEnum.values())
			if (c.getValue().equalsIgnoreCase(aValue.trim())) return c;

		if (treatApplesTypo(aValue)) return TitleOfBasketItemEnum.Apples;

		throw new IllegalArgumentException(aValue);
	}

	/*
	 * It's not clear from the brief what is an item name for apples: Apple or Apples.
	 * So this method, perhaps, should be removed when it's cleared out by business. 
	 */
	private static boolean treatApplesTypo(final String aValue) {
		return "apple".equalsIgnoreCase(aValue.trim());
	}

	@Override
	public String toString() {
		return value.toString();
	}
}