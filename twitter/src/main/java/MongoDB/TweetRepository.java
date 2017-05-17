package MongoDB;

import Model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by apple on 10/05/17.
 */
@Repository
public interface TweetRepository extends MongoRepository <Tweet, String> {

    /**
     * Publiczna metoda zwracająca listę wszystkich tweetów w bazie danych
     * @return List<Tweet>
     */

    public List<Tweet> findAll();

}
