package com.tt.reactive;

import com.tt.reactive.util.AbstractFuturesTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S06_AllAny extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(S06_AllAny.class);

	@Test
	public void allOf() throws Exception {
		CompletableFuture<String> java = questions("java");
		CompletableFuture<String> scala = questions("scala");
		CompletableFuture<String> clojure = questions("clojure");
		CompletableFuture<String> groovy = questions("groovy");

		CompletableFuture<Void> allCompleted = CompletableFuture.allOf(java, scala, clojure, groovy);

		allCompleted.thenRun(() -> {
			try {
				log.debug("Loaded: ()", java.get());
				log.debug("Loaded: ()", scala.get());
				log.debug("Loaded: ()", clojure.get());
				log.debug("Loaded: ()", scala.get());
			}catch (Exception ex){

			}
				}
		);

	}

	@Test
	public void anyOf() throws Exception {
		CompletableFuture<String> java = questions("java");
		CompletableFuture<String> scala = questions("scala");
		CompletableFuture<String> clojure = questions("clojure");
		CompletableFuture<String> groovy = questions("groovy");

		CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(java, scala, clojure, groovy);




	}

}

