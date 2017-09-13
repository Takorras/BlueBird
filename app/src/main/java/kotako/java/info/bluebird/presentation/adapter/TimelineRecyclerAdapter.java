package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.Tweet;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.model.entity.TweetEntity;

import java.util.ArrayList;

public class TimelineRecyclerAdapter extends RecyclerView.Adapter<TimelineRecyclerAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<TweetEntity> list;
    
    public TimelineRecyclerAdapter(Context context, ArrayList<TweetEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_tweet_preview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, final int i) {
        if (list == null) return;

        // 描画と処理
        Tweet item = list.get(i).getTweet();
        if (item.retweeted) {
            viewHolder.retweetedView.setText("retweeted by" + item.user.name);
            item = item.retweetedStatus;
        }
        Glide.with(context).load(item.user.profileImageUrl).into(viewHolder.userIconView);
        viewHolder.userNameView.setText(item.user.name);
        viewHolder.screenNameView.setText("@"+item.user.screenName);
        viewHolder.tweetView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView userIconView;
        TextView userNameView;
        TextView screenNameView;
        TextView tweetView;
        TextView retweetedView;

        CustomViewHolder(View itemView) {
            super(itemView);
            userIconView = (ImageView)  itemView.findViewById(R.id.img_ic_user);
            userNameView = (TextView) itemView.findViewById(R.id.text_name_user);
            screenNameView = (TextView) itemView.findViewById(R.id.text_screenname_user);
            tweetView = (TextView) itemView.findViewById(R.id.text_context_tweet);
            retweetedView = (TextView) itemView.findViewById(R.id.text_retweeted);
        }
    }
}
