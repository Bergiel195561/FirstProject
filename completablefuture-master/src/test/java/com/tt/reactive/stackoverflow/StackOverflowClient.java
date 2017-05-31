package com.tt.reactive.stackoverflow;

import org.jsoup.nodes.Document;

public interface StackOverflowClient {

	String mostRecentQuestionAbout(String tag);
	Document mostRecentQuestionsAbout(String tag);

}
