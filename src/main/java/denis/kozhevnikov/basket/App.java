package denis.kozhevnikov.basket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import denis.kozhevnikov.basket.discount.DiscountProcessor;
import denis.kozhevnikov.basket.discount.DiscountResult;
import denis.kozhevnikov.basket.domain.BuyableBasketItems;
import denis.kozhevnikov.basket.subtotal.SubtotalProcessor;
import denis.kozhevnikov.basket.subtotal.SubtotalResult;
import denis.kozhevnikov.basket.tools.spring.AppContext;
import denis.kozhevnikov.basket.total.TotalProcessor;
import denis.kozhevnikov.basket.total.TotalResult;
import denis.kozhevnikov.basket.ui.UI;
import denis.kozhevnikov.basket.ui.exceptions.InputAbstractException;

final public class App {

	private UI gui;

	private DiscountProcessor discountProcessor;
	private SubtotalProcessor subtotalProcessor;
	private TotalProcessor totalProcessor;

	private void initialiseSpringContext() {
		gui = (UI) AppContext.getBean("gui");
		subtotalProcessor = (SubtotalProcessor)AppContext.getBean("subtotalProcessor");
		totalProcessor = (TotalProcessor)AppContext.getBean("totalProcessor");
		discountProcessor = (DiscountProcessor)AppContext.getBean("discountProcessor");
	}

	private void doStuff(final String aInput) {
		initialiseSpringContext();

		gui.setInput(aInput);
		BuyableBasketItems basket = null;
		try {
			basket = gui.calculateListOfBasketItemsAtInput();
		} catch (InputAbstractException e) {
			gui.visualiseException(e);
			return;
		}
		SubtotalResult subtotalValue = subtotalProcessor.process(basket);
		gui.showSubtotal(subtotalValue);

		DiscountResult discountValue = discountProcessor.process(basket);
		gui.showDiscounts(discountValue);

		TotalResult totalValue = totalProcessor.process(subtotalValue, discountValue);
		gui.showTotal(totalValue);
	}

	public static void main(String[] args) {
		try {
			new App().doStuff(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setGui(final UI aGui) { gui = aGui; }
	public UI getGui() { return gui; }
	public final DiscountProcessor getDiscountProcessor() {return discountProcessor;}
	public final void setDiscountProcessor(DiscountProcessor discountProcessor) {this.discountProcessor = discountProcessor;}
	public final SubtotalProcessor getSubtotalProcessor() {return subtotalProcessor;}
	public final void setSubtotalProcessor(SubtotalProcessor subtotalProcessor) {this.subtotalProcessor = subtotalProcessor;}
	public final TotalProcessor getTotalProcessor() {return totalProcessor;}
	public final void setTotalProcessor(TotalProcessor totalProcessor) {this.totalProcessor = totalProcessor;}
}