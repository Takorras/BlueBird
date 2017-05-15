package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.twitter.sdk.android.core.models.Tweet;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.presentation.customview.TweetPreviewCustomView;

import java.util.*;

public class TimelineListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Tweet> list;

    public TimelineListAdapter(Context context,ArrayList<Tweet> list){
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Tweet getItem(int p){
        return list.get(p);
    }

    @Override
    public long getItemId(int p){
        return p;
    }

    @Override
    public View getView(int p,View convertView,ViewGroup viewGroup){
        final TweetPreviewCustomView view;
        if(convertView == null){
            view = (TweetPreviewCustomView) layoutInflater.inflate(R.layout.view_tweet_preview,null);

        }else{
            view = (TweetPreviewCustomView) convertView;
        }

        if(getItem(p).retweeted){
            view.bindView(getItem(p).retweetedStatus);
        }
        view.bindView(getItem(p));
        return view;
    }
}
