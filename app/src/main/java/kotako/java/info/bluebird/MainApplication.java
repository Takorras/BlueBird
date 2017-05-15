package kotako.java.info.bluebird;

import android.app.Application;
import android.util.Log;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainApplication extends Application {

    private static MainApplication mainApplication;
    @Override
    public void onCreate(){
        super.onCreate();

        String CONSUMER_KEY = "";
        String CONSUMER_SECRET = "";
        TwitterAuthConfig authConfig = new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        mainApplication = this;
        Log.d("BlueBird","application created");
    }

    // Activityじゃない場所からContextとか使いたい時用
    public static synchronized MainApplication getInstance(){
        return mainApplication;
    }
}
