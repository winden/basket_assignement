package denis.kozhevnikov.basket.ui;

import java.util.Arrays;
import java.util.List;

import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.domain.BuyableBasketItemFactory;
import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;
import denis.kozhevnikov.basket.tools.spring.AppContext;
import denis.kozhevnikov.basket.total.TotalResult;
import denis.kozhevnikov.basket.types.monetary.ToEndUserStringConverter;
import denis.kozhevnikov.basket.ui.exceptions.InputItemIsNotRecognisedException;
import denis.kozhevnikov.basket.ui.exceptions.InputParsingException;
import denis.kozhevnikov.basket.ui.exceptions.VisuableException;
import denis.kozhevnikov.basket.utils.Implements;

/**
 * Command line implementation of UI {@link UI}.
 *
 * @see UI
 */
public class UICommandLine 
	implements UI {

	private String internalInputString;

	final private String priceBasketInputText = (String)AppContext.getBean("priceBasketInputText");
	final private String parsingUIExceptionText = (String)AppContext.getBean("parsingUIExceptionText");
	final private String inputItemIsNotRecognisedUIExceptionText = (String)AppContext.getBean("inputItemIsNotRecognisedUIExceptionText");
	final private String totalTextNoDiscounts = (String)AppContext.getBean("totalTextNoDiscounts");
	final private String totalTextDiscounts = (String)AppContext.getBean("totalTextDiscounts");
	final private String subtotalText = (String)AppContext.getBean("subtotalText");
	final private String noDiscountText = (String)AppContext.getBean("noDiscountText");

	private String makeASafeCopyOfTheIputString(final String aInput) {
		return new String(aInput);
	}

	@Override
	@Implements(UI.class)	
	public BuyableBasketItems calculateListOfBasketItemsAtInput() 
		throws InputParsingException, InputItemIsNotRecognisedException {
		BuyableBasketItems result = new BuyableBasketItems();
		List<String> inputItems = splitInput();

		if (! checkThatFirstInputIsPriceBasket(inputItems.get(0))) 
			throw new InputParsingException(parsingUIExceptionText, 
				"The first element of [" + internalInputString + "] is not " + priceBasketInputText + ".");

		if (! checkThatItemsToBePutIntoTheBasketProvided(inputItems)) 
			throw new InputParsingException(parsingUIExceptionText, 
					"No items to be put into the basket provided [" + internalInputString + "].");

		try {
			for (String inputItem: inputItems.subList(1, inputItems.size())) {
				result.add(BuyableBasketItemFactory.valueOf(inputItem));
			}
		} catch (IllegalArgumentException e) {
			//TODO: provide more technical information
			throw new InputItemIsNotRecognisedException(inputItemIsNotRecognisedUIExceptionText, null);
		}
		return result;
	}

	private boolean checkThatFirstInputIsPriceBasket(final String aValue) {
		if (aValue == null) return false;
		return priceBasketInputText.equalsIgnoreCase(aValue.trim());
	}

	private boolean checkThatItemsToBePutIntoTheBasketProvided(List<String> aItems) {
		if (aItems == null) return false;
		return aItems.size() > 1;
	}

	private List<String> splitInput() {
		return Arrays.asList(internalInputString.split("\\s+"));
	}

	@Override
	@Implements(UI.class)	
	public void setInput(Object aInput) {
		internalInputString = makeASafeCopyOfTheIputString((String)aInput);
	}

	@Override
	@Implements(UI.class)
	public void visualiseException(final VisuableException aExcpetion) {
		final String UItext = aExcpetion.getUserFriendlyExceptionInformation();
		final String techText = aExcpetion.getTechnicalExceptionInformation();

		if (UItext != null && UItext.length() > 0 )
			System.out.println(aExcpetion.getUserFriendlyExceptionInformation());

		if (techText != null && techText.length() > 0 ) {
			System.out.println("Additional Information:");
			System.out.println(techText);
		}
	}

	@Override
	@Implements(UI.class)
	public void showSubtotal(SubtotalResult subtotalvalue) {
		System.out.println(subtotalText + " " + ToEndUserStringConverter.convert(subtotalvalue.getPrice()));
	}

	@Override
	@Implements(UI.class)
	public void showDiscounts(DiscountResult discountValue) {
		if (discountValue.isAnyDiscoutApplied())
			System.out.println(discountValue.getUIText());
		else
			System.out.println(noDiscountText);
	}

	@Override
	@Implements(UI.class)
	public void showTotal(TotalResult totalValue) {
		final String result = totalValue.isAnyDiscoutApplied() ? totalTextDiscounts : totalTextNoDiscounts;
		System.out.println(result + " " + ToEndUserStringConverter.convert(totalValue.getPrice()));
	}
}