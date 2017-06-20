package kotako.java.info.bluebird.presentation.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.twitter.sdk.android.core.models.Tweet;

public class TweetPreviewCustomView extends RelativeLayout {

    private Tweet tweet;

    public TweetPreviewCustomView(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setTweet(Tweet tweet){
        //TODO IDだけのセットでも良い気がしなくもなくない
        this.tweet = tweet;
    }

    public Tweet getTweet(){
        return tweet;
    }
}
