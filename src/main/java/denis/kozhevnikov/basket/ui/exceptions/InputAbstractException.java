package denis.kozhevnikov.basket.ui.exceptions;

import denis.kozhevnikov.basket.utils.Constructor;
import denis.kozhevnikov.basket.utils.Implements;

/**
 * Basic class for UI exceptions.
 * Allows to store user friendly description about what went wrong and more technical info.
 */
public abstract class InputAbstractException extends Exception 
	implements VisuableException{

	private final String userFriendlyExceptionInformation;
	@Implements(VisuableException.class)
	public String getUserFriendlyExceptionInformation() { return userFriendlyExceptionInformation; }

	private final String technicalExceptionInformation;
	@Implements(VisuableException.class)
	public String getTechnicalExceptionInformation() { return technicalExceptionInformation; }

	@Constructor
	protected InputAbstractException(final String aUIInformation, final String aTechnicalInformation) {
		userFriendlyExceptionInformation = aUIInformation;
		technicalExceptionInformation = aTechnicalInformation;
	}

	private static final long serialVersionUID = -1631429072786096022L;
}