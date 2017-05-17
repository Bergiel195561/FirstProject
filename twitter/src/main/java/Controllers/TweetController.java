package Controllers;

import Model.Tweet;
import MongoDB.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by apple on 10/05/17.
 */

@RestController
public class TweetController {
    private final TweetRepository repository;

    @Autowired
    public TweetController(TweetRepository repository) {
        this.repository = repository;
    }

    /**
     * Pobiera z bazy liste wszstkich tweetów
     *
     * @return Lista tweetów w bazie
     */

    @RequestMapping(value = "tweets", method = RequestMethod.GET)
    public String getAll() {
        return "Siema";
    }


}
