package com.tt.reactive;

import com.tt.reactive.stackoverflow.LoadFromStackOverflowTask;
import com.tt.reactive.util.AbstractFuturesTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class S01_Introduction extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(S01_Introduction.class);

	@Test
	public void blockingCall() throws Exception {
		String java = client.mostRecentQuestionAbout("java");
		log.debug("Most recent questions on java()", java);
	}

	@Test
	public void executorService() throws Exception {
		Callable<String> task = () -> client.mostRecentQuestionAbout("java");
		Future<String> javaQuestionFuture = executorService.submit(task);
		String javaQuestion = javaQuestionFuture.get();
		log.debug("Get question() ", javaQuestion);
	}

	public void waitForFirstOrAll() throws Exception{
		Future<String> java = findQuestionsAbout("java");
		Future<String> scala = findQuestionsAbout("scala");

		String javaValue = java.get();
		String scalaValue = scala.get();
	}

	private Future<String> findQuestionsAbout(String tag) {
		final Callable<String> task = new LoadFromStackOverflowTask(client, tag);
		return executorService.submit(task);
	}

}

