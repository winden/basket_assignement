package denis.kozhevnikov.basket.types.titleofbasketitem;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(TitleOfBasketItem.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TitleOfBasketItemTest {

	@Autowired private TitleOfBasketItem titleUnderTest;
	@Autowired private TitleOfBasketItem copyOfTitleUnderTest;
	@Autowired private TitleOfBasketItem differentFromTitleUnderTest;
	@Autowired private String orange;
	
	@Test public void testValueOf() {
		assertThat("We can create object through the ValueOf() method",
				titleUnderTest, is(equalTo(titleUnderTest)));
	}

	@Test (expected=IllegalArgumentException.class)
	public void shouldFailWhenTryingToCreateAnItemNotInPreset() {
		TitleOfBasketItem.valueOf(orange);
	}

	@Test public void testEqualsObjectPositive() {
		assertThat("Two objects contain information about the same basket item are equal",
				titleUnderTest, equalTo(copyOfTitleUnderTest));
	}

	@Test public void testEqualsObjectNegative() {
		assertThat("Two objects contain information about different basket items are not equal",
				titleUnderTest, not(equalTo(differentFromTitleUnderTest)));
	}
}