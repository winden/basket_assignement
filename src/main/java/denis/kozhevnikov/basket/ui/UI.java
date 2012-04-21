package denis.kozhevnikov.basket.ui;

import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;
import denis.kozhevnikov.basket.total.TotalResult;
import denis.kozhevnikov.basket.ui.exceptions.InputItemIsNotRecognisedException;
import denis.kozhevnikov.basket.ui.exceptions.InputParsingException;
import denis.kozhevnikov.basket.ui.exceptions.VisuableException;

/**
 * Interface for interactions with user that is capable of returning information
 * about what is needed to be placed into the basket.
 * 
 * @see InputParsingException for negative scenarios when an input text cannot be parsed.
 * @see InputItemIsNotRecognisedException for scenarios when users want to place an item of
 * the type currently not supported.
 * @see VisuableException for scenarios when we ask UI to show inforamtion about an exception.
 * @see SubtotalResult for functionality of showing sub-total information about the basket.
 * @see DiscountResult for functionality of showing discount information about the basket.
 * @see TotalResult  for functionality of showing discount information about the basket.
 */
public interface UI {

	/**
	 * Defines the input from where a user will put their information
	 * about what is needed to be placed into the basket.
	 * @param aInput generic object represents such input.
	 */
	void setInput(final Object aInput);

	/**
	 * Calculates information about what is needed to be placed into the basket.
	 * @return information about what is needed to be placed into the basket.
	 * @throws InputParsingException.
	 * @throws InputItemIsNotRecognisedException.
	 */
	BuyableBasketItems calculateListOfBasketItemsAtInput() throws InputParsingException, InputItemIsNotRecognisedException;

	/**
	 * Method to visualise exceptions, so that a user is aware that
	 * something went wrong.
	 */
	void visualiseException(final VisuableException aExcpetion);

	/**
	 * Shows sub-total information about the items in the basket.
	 * @param subtotalvalue represents the sub-total information.
	 */
	void showSubtotal(final SubtotalResult subtotalvalue);

	/**
	 * Shows discount information about the items in the basket.
	 * @param discountValue represents the discount information.
	 */
	void showDiscounts(final DiscountResult discountValue);

	/**
	 * Shows total information about the items in the basket.
	 * @param totalValue represents the total information.
	 */
	void showTotal(final TotalResult totalValue);
}