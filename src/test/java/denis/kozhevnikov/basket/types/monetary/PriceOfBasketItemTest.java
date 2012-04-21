package denis.kozhevnikov.basket.types.monetary;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(PriceOfBasketItem.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PriceOfBasketItemTest {

	@Autowired private PriceOfBasketItem sumPrice;
	@Autowired private PriceOfBasketItem addedPrice;
	@Autowired private PriceOfBasketItem priceUnderTest;

	@Autowired private PriceOfBasketItem Price0_20Copy;
	@Autowired private PriceOfBasketItem Price0_20;
	
	@Test public void testValueOf() {
		assertThat("We can create object through the ValueOf() method",
			priceUnderTest, is(equalTo(priceUnderTest)));
	}

	@Test public void testAdd() {
		assertThat("We can add price to another price object through add() method",
			priceUnderTest.plus(addedPrice), equalTo(sumPrice));
	}

	@Test public void testEqual() {
		assertThat(Price0_20, equalTo(Price0_20Copy));
	}

}