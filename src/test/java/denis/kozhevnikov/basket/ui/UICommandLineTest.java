package denis.kozhevnikov.basket.ui;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.ui.exceptions.InputItemIsNotRecognisedException;
import denis.kozhevnikov.basket.ui.exceptions.InputParsingException;
import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(UICommandLine.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UICommandLineTest {
	@Autowired private String priceBasketInputTextNegative;
	@Autowired private String priceBasketInputTextPositive;
	@Autowired private String priceBasketInputTextWithNoItems;
	
	@Autowired private UICommandLine commandLineUIUnderTest;

	@Test (expected=InputParsingException.class)
	public void testCalculateListOfBasketItemsAtInputNegatve() throws InputParsingException, InputItemIsNotRecognisedException {
		commandLineUIUnderTest.setInput(priceBasketInputTextNegative);
		commandLineUIUnderTest.calculateListOfBasketItemsAtInput();
	}

	@Test public void testCalculateListOfBasketItemsAtInputPositive() throws InputParsingException, InputItemIsNotRecognisedException {
		commandLineUIUnderTest.setInput(priceBasketInputTextPositive);
		assertThat("We have something as a result, so that we can work with it", 
			commandLineUIUnderTest.calculateListOfBasketItemsAtInput(),
			notNullValue(BuyableBasketItems.class));
	}

	@Test (expected=InputParsingException.class)
	public void shouldFailWhenNoItemsAskedToPutIntoBasket() throws InputParsingException, InputItemIsNotRecognisedException {
		commandLineUIUnderTest.setInput(priceBasketInputTextWithNoItems);
		commandLineUIUnderTest.calculateListOfBasketItemsAtInput();
	}
}