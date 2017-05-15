package kotako.java.info.bluebird.model;

import kotako.java.info.bluebird.data.SharedPreferenceEditor;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccessTokenManager {

    private SharedPreferenceEditor localStorage;

    private final String CONSUMER_KEY = "TGyOU5p150FkcoD1J1BOwA9eG";
    private final String CONSUMER_SECRET = "wh3r28ycn0sM4xKxWTO7TDuFRxYOKtPy9m80XuSeDQhT6Tm4bx";

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

    public void storeAccessToken(String token,String secret){
        localStorage.setString("AccessToken",token);
        localStorage.setString("TokenSecret",secret);
    }
}
