package kotako.java.info.bluebird.model.entity;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.io.Serializable;

public class TweetEntity implements Serializable {

    private long tweetId;
    private Tweet tweet;
    private String iconUrl;
    private String content;

    public TweetEntity(Tweet tweet){
        this.tweet = tweet;
    }

    public Tweet getTweet(){
        return tweet;
    }
    public String getContent() {
        return tweet.text;
    }

    public User getUser() {
        return tweet.user;
    }

}
