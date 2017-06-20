package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.twitter.sdk.android.core.models.Tweet;
import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.presentation.customview.TweetPreviewCustomView;

import java.util.ArrayList;

public class TimelineRecAdapter extends RecyclerView.Adapter<TimelineRecAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Tweet> list;

    public TimelineRecAdapter(Context context, ArrayList<Tweet> list) {
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
        Tweet item = list.get(i);
        if (item.retweeted) {
            viewHolder.retweetedView.setText("retweeted by" + item.user.name);
            item = item.retweetedStatus;
        }
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
        ImageView userIcView;
        TextView userNameView;
        TextView screenNameView;
        TextView tweetView;
        TextView retweetedView;

        CustomViewHolder(View itemView) {
            super(itemView);
            userIcView = (ImageView) itemView.findViewById(R.id.img_ic_user);
            userNameView = (TextView) itemView.findViewById(R.id.text_name_user);
            screenNameView = (TextView) itemView.findViewById(R.id.text_screenname_user);
            tweetView = (TextView) itemView.findViewById(R.id.text_context_tweet);
            retweetedView = (TextView) itemView.findViewById(R.id.text_retweeted);
        }
    }
}
