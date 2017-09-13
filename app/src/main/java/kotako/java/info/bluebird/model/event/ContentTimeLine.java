package kotako.java.info.bluebird.model.event;

import kotako.java.info.bluebird.model.entity.TweetEntity;

import java.util.ArrayList;
import java.util.List;

public class ContentTimeLine {
    private List<TweetEntity> tweetList;

    public ContentTimeLine(List<TweetEntity> tweetList) {
        this.tweetList = tweetList;
    }

    public List<TweetEntity> getList() {
        if (tweetList == null) return new ArrayList<>();
        return tweetList;
    }
}