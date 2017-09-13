package kotako.java.info.bluebird.presentation.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;

public class TweetPreviewCustomView extends RelativeLayout {

    public TweetPreviewCustomView(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        //ButterKnife.bind(this);
    }
}
