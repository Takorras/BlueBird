package kotako.java.info.bluebird.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.model.TwitterManager;
import kotako.java.info.bluebird.model.entity.TweetEntity;
import kotako.java.info.bluebird.model.event.ContentTimeLine;
import kotako.java.info.bluebird.presentation.adapter.ScrollListener;
import kotako.java.info.bluebird.presentation.adapter.TimelineRecyclerAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TimeLineFragment extends Fragment {

    private Context context;
    private ArrayList<TweetEntity> tweetList;
    private RecyclerView recyclerView;
    private TimelineRecyclerAdapter adapter;
    private TwitterManager twitter;

    private int DEFAULT_LOAD = 50;

    public TimeLineFragment() {
        // Required empty public constructor
    }

    public static TimeLineFragment newInstance() {
        // !TODO なんかしらのキーワードを受け取ってユーザリストとかパブリックとか切り替えたい
        return new TimeLineFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context.getApplicationContext());
        this.context = context.getApplicationContext();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      再生成された時
        if (savedInstanceState != null) {
            tweetList = (ArrayList<TweetEntity>) savedInstanceState.getSerializable("TweetList");
            adapter = new TimelineRecyclerAdapter(context, tweetList);
            twitter = new TwitterManager();
            twitter.getTwitterSession();
            return;
        }

        tweetList = new ArrayList<>();
        adapter = new TimelineRecyclerAdapter(context, tweetList);
        twitter = new TwitterManager();
        twitter.getTwitterSession();
        twitter.getHomeTimeLineAsync(DEFAULT_LOAD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_timeline);
//      境界線をひく
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new ScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int currentPage) {
                //twitter.getHomeTimeLineAsync(DEFAULT_LOAD);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // TwitterManagerからタイムラインを受け取ったら、リストに追加
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void timelineget(ContentTimeLine event) {
        tweetList.addAll(event.getList());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
