package denis.kozhevnikov.basket.total;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;
import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(TotalProcessorImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

public class TotalProcessorImplTest {

	@Autowired private PriceOfBasketItem expectedPrice;
	@Autowired private SubtotalResult subtotal;
	@Autowired private DiscountResult discount;

	@Test
	public void testProcess() {
		//given spring autowired beans 

		//when
		TotalResult r = new TotalProcessorImpl().process(subtotal, discount);

		//then
		assertThat(r.getPrice(), equalTo(expectedPrice));
		assertThat(r.isAnyDiscoutApplied(), equalTo(true));
	}
}