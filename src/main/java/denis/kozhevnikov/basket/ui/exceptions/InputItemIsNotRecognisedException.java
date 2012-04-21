package denis.kozhevnikov.basket.ui.exceptions;

/**
 * Exception class when a user wants to add something that is not defined in
 * {@link TitleOfBasketItem}.
 * 
 * @see TitleOfBasketItem
 */
public class InputItemIsNotRecognisedException extends InputAbstractException{

	public InputItemIsNotRecognisedException(final String aUIInformation, final String aTechnicalInformation) {
		super(aUIInformation, aTechnicalInformation);
	}

	private static final long serialVersionUID = 5189921959666178702L;
}