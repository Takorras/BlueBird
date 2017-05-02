package kotako.java.info.bluebird;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {
    private static MainApplication mainApplication;
    @Override
    public void onCreate(){
        super.onCreate();
        mainApplication = this;
        Log.d("BlueBird","application created");
    }

    // Activityじゃない場所からContextとか使いたい時用
    public static synchronized MainApplication getInstance(){
        return mainApplication;
    }
}
