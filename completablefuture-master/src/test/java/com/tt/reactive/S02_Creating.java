package com.tt.reactive;

import com.tt.reactive.util.AbstractFuturesTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class S02_Creating extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(S02_Creating.class);

	@Test
	public void completed() throws Exception {
		CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.completedFuture(42);
		Integer valueOfCompleted = integerCompletableFuture.get();


	}

	@Test
	public void supplyAsync() throws Exception {
		CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> client.mostRecentQuestionAbout("java"));
		log.debug("Got question ()", java.get()); //getem blokuje glowny watek na pobranie tej wartosci

	}

	@Test
	public void supplyAsyncWithCustomExecutor() throws Exception {
		CompletableFuture<String> java =
				CompletableFuture.supplyAsync(() -> client.mostRecentQuestionAbout("java"), executorService);
		log.debug("Got question ()", java.get());

	}
}

