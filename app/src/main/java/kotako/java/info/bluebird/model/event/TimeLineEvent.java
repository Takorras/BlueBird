package kotako.java.info.bluebird.model.event;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class TimeLineEvent {
    private List<Tweet> list;

    public TimeLineEvent(List<Tweet> list){
        this.list = list;
    }

    public List<Tweet> getList(){
        return list;
    }
    public void setList(List<Tweet> list){
        this.list = list;
    }
}
