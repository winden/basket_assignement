package denis.kozhevnikov.basket.ui.exceptions;

import denis.kozhevnikov.basket.utils.Constructor;

/**
 * Exception class when a user input doesn't match a pattern:
 * $> PriceBasket item1 item2 item3 ...
 */
public class InputParsingException extends InputAbstractException {

	@Constructor
	public InputParsingException(final String aUIInformation, final String aTechnicalInformation) {
		super(aUIInformation, aTechnicalInformation);
	}

	private static final long serialVersionUID = -8510860202812118934L;
}
