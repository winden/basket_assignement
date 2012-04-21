package denis.kozhevnikov.basket.discount;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.domain.IBuyableBasketItem;
import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(DiscountProcessorImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DiscountProcessorImplTest {

	@Autowired private DiscountProcessorImpl underTest;

	@Autowired private BuyableBasketItems basketTwoApples;
	@Autowired private PriceOfBasketItem basketTwoApplesExpectedDiscount;

	@Autowired private BuyableBasketItems basketTwoApplesTwoBreadsFourSoupsOneMilk;
	@Autowired private PriceOfBasketItem basketTwoApplesTwoBreadsFourSoupsOneMilkExpectedDiscount;

	@Autowired private BuyableBasketItems basketOneBreadOneSoupOneMilk;
	@Autowired private PriceOfBasketItem basketOneBreadOneSoupOneMilkExpectedDiscount;

	@Autowired private IBuyableBasketItem applePrototype;
	@Autowired private IBuyableBasketItem breadPrototype;
	@Autowired private IBuyableBasketItem milkPrototype;
	@Autowired private IBuyableBasketItem soupPrototype;

	@Test
	public void testProcessABasketWithTwoApples() {
		//given
		basketTwoApples.add(applePrototype);
		basketTwoApples.add(applePrototype);

		//when
		DiscountResult r = underTest.process(basketTwoApples);

		//then
		assertThat("Only apple discount has been applied", r.isAnyDiscoutApplied(), equalTo(true));
		assertThat(r.getPrice(), equalTo(basketTwoApplesExpectedDiscount));
	}

	@Test
	public void testProcessABasketWithTwoApplesTwoBreadsFourSoupsOneMilk() {
		//given
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(applePrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(applePrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(breadPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(breadPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);
		basketTwoApplesTwoBreadsFourSoupsOneMilk.add(milkPrototype);

		//when
		DiscountResult r = underTest.process(basketTwoApplesTwoBreadsFourSoupsOneMilk);

		//then
		assertThat("apple and bread discounts have been applied", r.isAnyDiscoutApplied(), equalTo(true));
		assertTrue(r.getPrice().equals(basketTwoApplesTwoBreadsFourSoupsOneMilkExpectedDiscount));
	}

	@Test
	public void testProcessABasketOneAppleOneBreadOneSoupOneMilk() {
		//given
		basketOneBreadOneSoupOneMilk.add(breadPrototype);
		basketOneBreadOneSoupOneMilk.add(soupPrototype);
		basketOneBreadOneSoupOneMilk.add(milkPrototype);

		//when
		DiscountResult r = underTest.process(basketOneBreadOneSoupOneMilk);

		//then
		assertThat("No discount has been applied", r.isAnyDiscoutApplied(), equalTo(false));
		assertThat(r.getPrice(), equalTo(basketOneBreadOneSoupOneMilkExpectedDiscount));
	}
}