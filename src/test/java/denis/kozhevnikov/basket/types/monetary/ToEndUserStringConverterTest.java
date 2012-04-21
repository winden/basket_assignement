package denis.kozhevnikov.basket.types.monetary;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(ToEndUserStringConverter.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ToEndUserStringConverterTest {

	@Autowired private PriceOfBasketItem lessThenPoundPence;
	@Autowired private PriceOfBasketItem lessThenPoundPenny;
	@Autowired private PriceOfBasketItem moreThenPoundPence;
	@Autowired private PriceOfBasketItem moreThenPoundPenny;

	@Autowired private String lessThenPoundPenceExpectedResult;
	@Autowired private String lessThenPoundPennyExpectedResult;
	@Autowired private String moreThenPoundPenceExpectedResult;
	@Autowired private String moreThenPoundPennyExpectedResult;

	@Test
	public void testLessThenPound() {
		assertThat(ToEndUserStringConverter.convert(lessThenPoundPence), equalTo(lessThenPoundPenceExpectedResult));
		assertThat(ToEndUserStringConverter.convert(lessThenPoundPenny), equalTo(lessThenPoundPennyExpectedResult));
	}

	@Test
	public void testMoreThenPound() {
		assertThat(ToEndUserStringConverter.convert(moreThenPoundPence), equalTo(moreThenPoundPenceExpectedResult));
		assertThat(ToEndUserStringConverter.convert(moreThenPoundPenny), equalTo(moreThenPoundPennyExpectedResult));
	}
}