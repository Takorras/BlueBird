package kotako.java.info.bluebird.model;

import twitter4j.Twitter;
import twitter4j.TwitterStream;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import twitter4j.TwitterException;

public class TwitterModel {
    private static Twitter twitter;
    private static TwitterStream twitterStream;
    private static Configuration config;
    private AccessTokenManager manager;

    public Twitter getTwitter() throws UnsupportedOperationException {
        if (twitter != null) return twitter;

        // create twitter instance
        if (manager == null) manager = new AccessTokenManager();
        twitter = getTwitterInstance();
        twitter.setOAuthConsumer(manager.getConsumerKey(), manager.getConsumerSecret());
        // throwException when not yet token created
        twitter.setOAuthAccessToken(new AccessToken(manager.getAccessToken(), manager.getTokenSecret()));
        return twitter;
    }

    private Twitter getTwitterInstance() {
        TwitterFactory factory = new TwitterFactory();
        return factory.getInstance();
    }

    private TwitterStream getTwitterStream() {
        if (twitterStream != null) return twitterStream;

        if (manager == null) manager = new AccessTokenManager();

        Configuration twitterConfig = new ConfigurationBuilder()
                .setOAuthConsumerKey(manager.getConsumerKey())
                .setOAuthConsumerSecret(manager.getConsumerSecret())
                .setOAuthAccessToken(manager.getAccessToken())
                .setOAuthAccessTokenSecret(manager.getTokenSecret())
                .build();
        twitterStream = new TwitterStreamFactory(twitterConfig).getInstance();
        return twitterStream;
    }


    public void tweet(String content) throws TwitterException {
        if (twitter == null) twitter = getTwitter();
        twitter.updateStatus(content);
    }
}
