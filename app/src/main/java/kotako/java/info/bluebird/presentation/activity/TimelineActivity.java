package kotako.java.info.bluebird.presentation.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.twitter.sdk.android.core.models.Tweet;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.model.event.*;
import kotako.java.info.bluebird.model.TwitterManager;
import kotako.java.info.bluebird.presentation.adapter.TimelineListAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity{
    private TwitterManager twitter;
    private TimelineListAdapter adapter;
    private ArrayList<Tweet> tweetList;
    private Toolbar toolbar;
    private NavigationView nav;

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onTokenCreateEvent(SessionNotFound event) {
        startActivity(new Intent(getApplication(), LoginWithTwitterActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onHomeTimeLineEvent(final TimeLineEvent event) {
        tweetList.addAll(event.getList());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("!!!", "さあーできたでー");
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onFailureEvent(FailEvent event) {
        Toast.makeText(this, event.getText(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSessionEvent(TwitterSessionEvent event) {
        twitter.getApiClient();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onApiCluentEvent(TwitterAPI event) {
        if (nav != null) {
            TextView accountName = (TextView) nav.findViewById(R.id.text_drawer_account);
            TextView screenName = (TextView) nav.findViewById(R.id.text_drawer_screen_name);
            accountName.setText(event.getName());
            screenName.setText(event.getScreenName());
        }
        twitter.getHomeTimeLine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // ListViewの作成
        ListView listView = (ListView) findViewById(R.id.listView_timeline);
        tweetList = new ArrayList<>();
        adapter = new TimelineListAdapter(this, tweetList);
        listView.setAdapter(adapter);

        // TwitterAPIのリクエスト
        new Thread(new Runnable() {
            @Override
            public void run() {
                twitter = new TwitterManager();
                twitter.getTwitterSession();
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

