package kotako.java.info.bluebird.model;

import kotako.java.info.bluebird.data.SharedPreferenceEditor;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccessTokenManager {

    private SharedPreferenceEditor localStorage;

    private final String CONSUMER_KEY = "";
    private final String CONSUMER_SECRET = "";

    public AccessTokenManager() {
        localStorage = new SharedPreferenceEditor();
    }

    public String getConsumerKey() {
        return CONSUMER_KEY;
    }

    public String getConsumerSecret() {
        return CONSUMER_SECRET;
    }

    public String getAccessToken() throws UnsupportedOperationException {
        if (localStorage.contains("AccessToken")) return localStorage.getString("AccessToken");
        throw new UnsupportedOperationException("Not yet key created");
    }

    public String getTokenSecret() throws UnsupportedOperationException {
        if (localStorage.contains("TokenSecret")) return localStorage.getString("TokenSecret");
        throw new UnsupportedOperationException("Not yet key created");
    }

    public void storeAccessToken(AccessToken token) {
        localStorage.setString("AccessToken", token.getToken());
        localStorage.setString("TokenSecret", token.getTokenSecret());
    }

    private AccessToken addAccessToken(Twitter twitter) throws TwitterException {
        RequestToken requestToken = twitter.getOAuthRequestToken();
        String oauthURL = requestToken.getAuthenticationURL();
        // 初回起動的なアクティビティに遷移してPINコードの入力をうながす
        String pin = "";     // pinはアクティビティから入力してもらう
        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
        storeAccessToken(accessToken);

        return accessToken;
    }

    private String getAuthorizationURL(Twitter twitter) throws TwitterException {
        twitter.setOAuthConsumer(getConsumerKey(), getConsumerSecret());
        return twitter.getOAuthRequestToken().getAuthorizationURL();
    }

    public RequestToken getRequestToken(Twitter twitter) throws TwitterException {
        twitter.setOAuthConsumer(getConsumerKey(), getConsumerSecret());
        return twitter.getOAuthRequestToken();
    }

}
