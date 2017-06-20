package kotako.java.info.bluebird.model.event;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class ContentTimeLine {
    private List<Tweet> list;

    public ContentTimeLine(List<Tweet> list){
        this.list = list;
    }

    public List<Tweet> getList(){
        if(list==null) return new ArrayList<>();
        return list;
    }
    public void setList(List<Tweet> list){
        this.list = list;
    }
}
