package denis.kozhevnikov.basket.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import denis.kozhevnikov.basket.types.monetary.PriceOfBasketItem;
import denis.kozhevnikov.basket.utils.ClassUnderTest;

@ClassUnderTest(BuyableBasketItems.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BuyableBasketItemsTest {

	@Autowired private BuyableBasketItems underTest;

	@Autowired private BuyableBasketItems basketTwoApplesTwoBreadsFourSoupsOneMilk;

	@Autowired private IBuyableBasketItem applePrototype;
	@Autowired private IBuyableBasketItem breadPrototype;
	@Autowired private IBuyableBasketItem milkPrototype;
	@Autowired private IBuyableBasketItem soupPrototype;

	@Test
	public void testAddandHowMany() {
		//given
		int appleExpectedCount = 2, breadExpectedCount = 1, 
		    milkExpectedCount = 3, soupExpectedCount = 4;
		for (int i = 0; i < appleExpectedCount; i++) underTest.add(applePrototype);
		for (int i = 0; i < breadExpectedCount; i++) underTest.add(breadPrototype);
		for (int i = 0; i < milkExpectedCount; i++) underTest.add(milkPrototype);
		for (int i = 0; i < soupExpectedCount; i++) underTest.add(soupPrototype);

		//when
		int appleCount = underTest.howMany(applePrototype);
		int breadCount = underTest.howMany(breadPrototype);
		int milkCount = underTest.howMany(milkPrototype);
		int soupCount = underTest.howMany(soupPrototype);

		//then
		assertThat(appleCount, equalTo(appleExpectedCount));
		assertThat(breadCount, equalTo(breadExpectedCount));
		assertThat(milkCount, equalTo(milkExpectedCount));
		assertThat(soupCount, equalTo(soupExpectedCount));
	}

	@Test
	public void testGetFullPrice() {
		//given
		int appleExpectedCount = 2, breadExpectedCount = 2, 
		    milkExpectedCount = 1, soupExpectedCount = 4;
		PriceOfBasketItem expectedPrice = PriceOfBasketItem.valueOf(BigDecimal.valueOf(7.50));
		for (int i = 0; i < appleExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(applePrototype);
		for (int i = 0; i < breadExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(breadPrototype);
		for (int i = 0; i < milkExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(milkPrototype);
		for (int i = 0; i < soupExpectedCount; i++) basketTwoApplesTwoBreadsFourSoupsOneMilk.add(soupPrototype);

		//when
		PriceOfBasketItem price = basketTwoApplesTwoBreadsFourSoupsOneMilk.getFullPrice();

		//then
		assertThat(price, equalTo(expectedPrice));
	}
}