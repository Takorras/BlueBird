package kotako.java.info.bluebird.presentation.activity;

import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.model.loader.TimeLineLoader;
import kotako.java.info.bluebird.model.event.TokenCreateEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import twitter4j.ResponseList;
import twitter4j.Status;

public class TimelineActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ResponseList<Status>> {

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onTokenCreateEvent(TokenCreateEvent event) {
        startActivity(new Intent(getApplication(), AuthorizationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        if (getSupportLoaderManager().getLoader(1) == null) {
            Bundle args = new Bundle();
            getSupportLoaderManager().initLoader(1, args, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public Loader<ResponseList<Status>> onCreateLoader(int id, Bundle args) {
        return new TimeLineLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<ResponseList<Status>> loader, ResponseList<Status> statuses) {
        if (statuses == null) return;
        Log.d("BlueBird", statuses.get(0).getText());
        getLoaderManager().destroyLoader(loader.getId());
    }

    @Override
    public void onLoaderReset(Loader<ResponseList<Status>> loader) {
        // do nothing
    }
}

