package kotako.java.info.bluebird.presentation.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.twitter.sdk.android.core.models.Tweet;
import kotako.java.info.bluebird.R;

public class TweetPreviewCustomView extends RelativeLayout {
    @BindView(R.id.text_context_tweet)  TextView content;
    @BindView(R.id.img_ic_user)  ImageView userIcon;
    @BindView(R.id.text_name_user)  TextView userName;
    @BindView(R.id.text_screenname_user)  TextView userScreenName;

    public TweetPreviewCustomView(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindView(Tweet item){
        content.setText(item.text);
        userName.setText(item.user.name);
        userScreenName.setText(item.user.screenName);
    }

}
