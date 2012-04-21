package denis.kozhevnikov.basket.utils;

import org.junit.Test;

@ClassUnderTest(ServiceListener.class)
public class ServiceListenerTest {

	@Test(expected=RuntimeException.class)
	public void shouldFailWhenZeroMessagesSentAndThousandExpected() {
		ServiceListener listener = new ServiceListener();
		listener.waitUntilAllExpectedMessagesProcessed(2);
	}

	@Test
	public void shouldnotFailWhenExpectedCountOfMessagesAsExpected() {
		ServiceListener listener = new ServiceListener();
		for (int i = 0; i < ServiceListener.EXPECTED; i++) {
			listener.onMessage();
		}
		listener.waitUntilAllExpectedMessagesProcessed(200000);
	}

	@Test(expected=RuntimeException.class)
	public void shouldFailWhenLessThanExpectedMessagesSent() {
		final ServiceListener listener = new ServiceListener();
		new Thread(
			new Runnable() {
				public void run() {
					for (int i = 0; i < ServiceListener.EXPECTED -1; i++) {
						listener.onMessage();
					}
				}
			}
		).start();
		listener.waitUntilAllExpectedMessagesProcessed(10);
	}

	@Test(expected=RuntimeException.class)
	public void shouldFailWhenSeveralTimesUsedAndExpectedNumberNotSent() {
		final ServiceListener listener = new ServiceListener();
		new Thread(
			new Runnable() {
				public void run() {
					for (int i = 0; i < ServiceListener.EXPECTED; i++) {
						listener.onMessage();
					}
				}
			}
		).start();
		listener.waitUntilAllExpectedMessagesProcessed(5000);
		listener.reset();
		listener.waitUntilAllExpectedMessagesProcessed(5);
	}
}