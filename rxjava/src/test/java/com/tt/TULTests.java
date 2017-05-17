package com.tt;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CompletableFuture;



/**
 * Created by dubelm on 2017-05-15.
 */
public class TULTests {

    private static Logger logger = LoggerFactory.getLogger(TULTests.class);

    @Test
    public void test1(){
        CompletableFuture<String> future = CompletableFuture.completedFuture("ABC");
        CompletableFuture<Integer> integerCompletableFuture =  future.thenApply((String s) -> s.length());
    }

    @Test
    public void test2(){
        Observable<String> obs = Observable.just("A", "BCB", "C");
        Observable<Integer> ints =  obs.map((String s) -> s.length());

        ints.subscribe(
                (Integer i) -> System.out.println(i),
                ex -> logger.error("Whoopsy Daisy", ex)
        );
    }

}
