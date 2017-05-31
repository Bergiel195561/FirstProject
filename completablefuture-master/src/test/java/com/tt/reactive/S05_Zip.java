package com.tt.reactive;

import com.tt.reactive.util.AbstractFuturesTest;
import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class S05_Zip extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(S05_Zip.class);

	public void thenCombine() throws Exception {
		CompletableFuture<String> java = questions("java");
		CompletableFuture<String> scala = questions("scala");

		CompletableFuture<Integer> both = java.thenCombine(scala, (String javaTitle, String scalaTitle) ->
				javaTitle.length() + scalaTitle.length());
		both.thenAccept(length -> log.debug("Total length: ()", length));

	}

	@Test
	public void either() throws Exception {
		CompletableFuture<String> java = questions("java");
		CompletableFuture<String> scala = questions("scala");


		CompletableFuture<String> both = java.applyToEither(scala, title -> title.toUpperCase());
	}

}

