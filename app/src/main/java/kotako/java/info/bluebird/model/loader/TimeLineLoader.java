package kotako.java.info.bluebird.model.loader;

import android.content.Context;
import android.util.Log;
import kotako.java.info.bluebird.model.TwitterModel;
import kotako.java.info.bluebird.model.event.SessionNotFound;
import org.greenrobot.eventbus.EventBus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TimeLineLoader extends AbstractAsyncTaskLoader<ResponseList<Status>> {
    private EventBus eventBus;

    public TimeLineLoader(Context context) {
        super(context);
        eventBus = EventBus.getDefault();
    }

    @Override
    public ResponseList<Status> loadInBackground() {
        Log.d("TimeLineLoader", "get twitter token...");

        try {
            return new TwitterModel().getTwitter().getHomeTimeline();
        } catch (UnsupportedOperationException e) {
            // token is not created
            Log.d("TimeLineLoader", e.getLocalizedMessage());
            eventBus.post(new SessionNotFound());
            return null;
        } catch (TwitterException e) {
            //
            Log.d("TimeLineLoader", e.getErrorMessage());
            return null;
        }
    }
}
