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

	private Future<String> findQuestionsAbout(String tag) {
		final Callable<String> task = new LoadFromStackOverflowTask(client, tag);
		return executorService.submit(task);
	}

}

