package denis.kozhevnikov.basket.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import com.jayway.awaitility.Awaitility;

public class ServiceListener {

	private AtomicLong count;
	private AtomicLong timeLastMessageProcessed;

	private long timeOutBetweenMessagesInSeconds;
	private long timeWaitingStarted;
	public final static long EXPECTED = 1000;

	public ServiceListener() {
		reset();
	}

	public void onMessage() {
		synchronized (count) {
			count.incrementAndGet();
			timeLastMessageProcessed.set(System.nanoTime());
		}
		System.out.println("Message: " + count.get());
	}

	synchronized public void reset() {
		count = new AtomicLong(0);
		timeLastMessageProcessed = new AtomicLong(0);
	}

	public void waitUntilAllExpectedMessagesProcessed(long aTimeOutBetweenMessagesInSeconds) {
		try {
			timeOutBetweenMessagesInSeconds = aTimeOutBetweenMessagesInSeconds;
			timeWaitingStarted = System.nanoTime();
			Awaitility.await().forever().until(
					isAllMessagesProcessedOrCountOfMessagesProcessedNotUpdated());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (isCountOfMessagesProcessedNotUpdatedForTooLong()) {
			throw new RuntimeException(
				String.format(
					"Time out of %d seconds between message processing expired",
					aTimeOutBetweenMessagesInSeconds));
		}
	}

	private Callable<Boolean> isAllMessagesProcessedOrCountOfMessagesProcessedNotUpdated() {
		return new Callable<Boolean>() {
			public Boolean call() throws Exception {
				synchronized (timeLastMessageProcessed) {
					return isAllMessagesProcessed() || isCountOfMessagesProcessedNotUpdatedForTooLong();
				}
			}
		};
	}

	private boolean isAllMessagesProcessed() {
		return EXPECTED == count.get();
	}

	private boolean isCountOfMessagesProcessedNotUpdatedForTooLong() {
		long latestHappened = timeWaitingStarted;
		if (latestHappened < timeLastMessageProcessed.get()) {
			latestHappened = timeLastMessageProcessed.get();
		}
		return (System.nanoTime() - latestHappened) / 1000000000 > timeOutBetweenMessagesInSeconds;
	}
}