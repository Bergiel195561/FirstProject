package com.tt.reactive;

import com.tt.reactive.util.AbstractFuturesTest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class S03_Map extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(S03_Map.class);

	@Test
	public void oldSchool() throws Exception {
		final CompletableFuture<Document> java =
				CompletableFuture.supplyAsync(() ->
								client.mostRecentQuestionsAbout("java"),
						executorService
				);

		final Document document = java.get();       //blocks
		final Element element = document.
				select("a.question-hyperlink").get(0);
		final String title = element.text();
		final int length = title.length();
		log.debug("Length: {}", length);
	}

}

