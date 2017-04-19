package kotako.java.info.bluebird.model;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterModel {
    private String consumerKey;
    private String consumerSecret;
    private static Twitter twitter;
    private AccessToken accessToken;

    public TwitterModel(){
        twitter = TwitterFactory.getSingleton();
    }
}
