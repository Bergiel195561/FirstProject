package Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by apple on 10/05/17.
 */
@Data
public class Tweet {
    @Id
    private String id;
    private String content;

}
