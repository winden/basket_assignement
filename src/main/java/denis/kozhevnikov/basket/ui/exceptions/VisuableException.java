package denis.kozhevnikov.basket.ui.exceptions;

/**
 * Interface for exceptions that could be shown to a user, 
 * so that they are aware that something went wrong.
 */
public interface VisuableException {
	String getUserFriendlyExceptionInformation();
	String getTechnicalExceptionInformation();
}