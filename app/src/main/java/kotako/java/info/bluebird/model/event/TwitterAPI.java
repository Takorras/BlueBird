package kotako.java.info.bluebird.model.event;

import com.twitter.sdk.android.core.models.User;

public class TwitterAPI {

    private String name;
    private String screenName;
    private com.twitter.sdk.android.core.models.User user;

    public TwitterAPI(){}

    public TwitterAPI(User user){
        this.user = user;
    }

    public TwitterAPI(String name, String screenName){
        this.name = name;
        this.screenName = screenName;
    }

    public String getName(){
        return name;
    }
    public String getScreenName(){
        return screenName;
    }
    public User getUser(){
        return user;
    }
}
