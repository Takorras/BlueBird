package kotako.java.info.bluebird.model;

import kotako.java.info.bluebird.data.SharedPreferenceEditor;
import twitter4j.auth.AccessToken;

class AccessTokenManager {

    private SharedPreferenceEditor localStorage;

    public AccessTokenManager() {
        localStorage = new SharedPreferenceEditor();
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
