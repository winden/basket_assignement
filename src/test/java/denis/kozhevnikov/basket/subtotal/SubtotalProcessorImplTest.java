package denis.kozhevnikov.basket.subtotal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.domain.IBuyableBasketItem;
import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(SubtotalProcessorImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SubtotalProcessorImplTest {

	@Autowired private BuyableBasketItems basketTwoApplesTwoBreadsFourSoupsOneMilk;

	@Autowired private IBuyableBasketItem applePrototype;
	@Autowired private IBuyableBasketItem breadPrototype;
	@Autowired private IBuyableBasketItem milkPrototype;
	@Autowired private IBuyableBasketItem soupPrototype;

	@Test
	public void testProcess() {
		//given
		int appleExpectedCount = 2, breadExpectedCount = 2, 
		    milkExpectedCount = 1, soupExpectedCount = 4;
		PriceOfBasketItem expectedPrice = PriceOfBasketItem.valueOf(BigDecimal.valueOf(7.50));
		for (int i = 0; i < appleExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(applePrototype);
		for (int i = 0; i < breadExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(breadPrototype);
		for (int i = 0; i < milkExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(milkPrototype);
		for (int i = 0; i < soupExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);

		//when
		SubtotalResult r = new SubtotalProcessorImpl().process(basketTwoApplesTwoBreadsFourSoupsOneMilk);

		//then
		assertThat(r.getPrice(), equalTo(expectedPrice));
	}
}