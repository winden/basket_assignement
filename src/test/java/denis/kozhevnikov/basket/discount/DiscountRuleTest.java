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

@ClassUnderTest(DiscountRule.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DiscountRuleTest {

	@Autowired private DiscountRule appleDiscount;
	@Autowired private DiscountRule breadDiscount;

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
	public void testApplyAppleDiscountOnABasketTwoApples() {
		//given
		basketTwoApples.add(applePrototype);
		basketTwoApples.add(applePrototype);

		//when
		DiscountRuleResult r = appleDiscount.apply(basketTwoApples);

		//then
		assertThat("apple discount has been applied", r.isDiscountApplied(), equalTo(true));
		assertThat(r.getDiscountValue(), equalTo(basketTwoApplesExpectedDiscount));

	}

	@Test
	public void testApplyBreadDiscountOnABasketTwoApplesTwoBreadsFourSoupsOneMilk() {
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
		DiscountRuleResult r = breadDiscount.apply(basketTwoApplesTwoBreadsFourSoupsOneMilk);

		//then
		assertThat("bread discount has been applied", r.isDiscountApplied(), equalTo(true));
		assertThat(r.getDiscountValue(), equalTo(basketTwoApplesTwoBreadsFourSoupsOneMilkExpectedDiscount));
	}

	@Test
	public void testApplyBreadDiscountOnABasketOneBreadOneSoupOneMilk() {
		//given
		basketOneBreadOneSoupOneMilk.add(breadPrototype);
		basketOneBreadOneSoupOneMilk.add(soupPrototype);
		basketOneBreadOneSoupOneMilk.add(milkPrototype);

		//when
		DiscountRuleResult r = breadDiscount.apply(basketOneBreadOneSoupOneMilk);

		//then
		assertThat("bread discount has been applied", r.isDiscountApplied(), equalTo(false));
		assertThat(r.getDiscountValue(), equalTo(basketOneBreadOneSoupOneMilkExpectedDiscount));
	}
}