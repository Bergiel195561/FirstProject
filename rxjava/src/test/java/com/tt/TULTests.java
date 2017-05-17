package com.tt;

import com.tt.cache.CacheServer;
import com.tt.dao.Person;
import com.tt.dao.PersonDao;
import com.tt.weather.Weather;
import com.tt.weather.WeatherClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.observables.BlockingObservable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * Created by dubelm on 2017-05-15.
 */
public class TULTests {

    private static Logger logger = LoggerFactory.getLogger(TULTests.class);
    private PersonDao personDao = new PersonDao();
    private WeatherClient weatherClient = new WeatherClient();

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

    @Test
    public void  test3() {
        Person byId = personDao.findById(23);
        print(byId);

    }
    @Test
    public void  test3Reaktywnie() {
        Observable<Person> personObservable = personDao.rxFindById(23);

        Observable<String> map = personObservable.map(Person::toString);

        map.subscribe(this::print);

    }

    public void print(Object obj){
        logger.info("Got {}", obj);
    }

    @Test
    public void  test4(){
        Observable<Person> personObservable = personDao
                .rxFindById(23)
                .timeout(1250, TimeUnit.MILLISECONDS);

        personObservable.subscribe(this::print);
    }

    @Test
    public void test5(){
        Observable<Person> person = personDao.rxFindById(23);
        Observable<Weather> oslo = weatherClient.rxFetch("Oslo");

        Observable<String> str = person.zipWith(oslo,
                (Person p, Weather w ) -> p+":" +w);

        //str.subscribe(this::print);
        BlockingObservable<String> blocking =  str.toBlocking(); // Czekamy na wywołanie wątku, inaczej sie nie wyswietlaja logi
        blocking.subscribe(this::print);

    }

    @Test
    public void test6() {
        CacheServer c1 = new CacheServer();
        CacheServer c2 = new CacheServer();

        Observable<String> s1 = c1.rxFindBy(123);
        Observable<String> s2 = c2.rxFindBy(123);


        Observable<String> stringObservable = s1
                .mergeWith(s2)
                .first();
        stringObservable
                .toBlocking()
                .subscribe(this::print);

    }

    @Test
    public void test7() {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .toBlocking()
                .subscribe(this::print);
    }

    List<String> childrenOf (File file) {
        return Arrays
                .asList(file.listFiles())
                .stream()
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @Test
    public void test8() { //Odpytywanie wszystkich plików w folderze
        File tmp = new File("./");
        //System.out.println(childrenOf(tmp));
        Observable
                .interval(1, TimeUnit.SECONDS)
                .concatMapIterable(x -> childrenOf(tmp))
                .distinct() //zwraca tylko jeden event zwiazany z danym plikiem, ze zostal dodany
                .toBlocking()
                .subscribe(this::print);
    }

    @Test
    public void test10() {
        Observable<Long> soap = verySlowSoapService();
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();
        soap
                .timeout(2, TimeUnit.SECONDS, testScheduler)
                .doOnError(ex -> logger.warn("Ooops: "+ ex))
                .retry(4)
                .onErrorReturn(ex -> -1L)
//                .toBlocking()
                .subscribe(testSubscriber);
//Zasymulowanie określonego czasu np. 10 s wykonuje się od razu w 1 s
        testSubscriber.assertNoErrors();
        testSubscriber.assertNoValues();

        testScheduler.advanceTimeBy(9999, TimeUnit.MILLISECONDS);

        testSubscriber.assertNoErrors();
        testSubscriber.assertNoValues();

        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
    }

    Observable<Long> verySlowSoapService(){
        return Observable.timer(10, TimeUnit.MINUTES);
    }
}
