package kotako.java.info.bluebird.model;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import kotako.java.info.bluebird.model.entity.TweetEntity;
import kotako.java.info.bluebird.model.event.*;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class TwitterManager {
    private TwitterSession twitterSession;
    private static TwitterApiClient apiClient;

    public TwitterSession getTwitterSession() {
        twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession == null) {
            // TwitterSessionを作成する
            EventBus.getDefault().post(new ObtainTwitterToken());
            return null;
        }
        getApiClient();
        return twitterSession;
    }

    public void setTwitterSession(TwitterSession session) {
        twitterSession = session;
        new AccessTokenManager().storeAccessToken(session.getAuthToken().token, session.getAuthToken().secret);
    }

    public void getApiClient() {
        // !TODO イベントにユーザ情報をつけてあげて、イベント取得側からいい感じにやる
        apiClient = Twitter.getApiClient(twitterSession);
    }

    public void updateStatus(String content) {
        if (apiClient == null) getApiClient();
        StatusesService statusesService = apiClient.getStatusesService();

        statusesService.update("hog", null, false, null, null, null, false, false, null).
                enqueue(new Callback<Tweet>() {
                    @Override
                    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                        // success
                        EventBus.getDefault().post(new onSuccessUpdate());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        // failure
                    }
                });
    }

    public void getHomeTimeLineAsync(int n) {
        // !TODO オプションでHOMEタイムラインかUserリストか選択して取得できるようにする
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.homeTimeline(n, null, null, false, true, true, true).
                enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        ArrayList<TweetEntity> tweetList = new ArrayList<>();
                        for (Tweet tweet : response.body()) tweetList.add(new TweetEntity(tweet));
                        EventBus.getDefault().post(new ContentTimeLine(tweetList));
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        EventBus.getDefault().post(new MakeToast("タイムラインの取得に失敗しました"));
                        EventBus.getDefault().post(new ContentTimeLine(null));
                    }
                });
    }

    public void getHomeTimeLineAsync(int n, long sinceId, long maxId) {
        // !TODO オプションでHOMEタイムラインかUserリストか選択して取得できるようにする
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.homeTimeline(n, sinceId, maxId, false, true, true, true).
                enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        ArrayList<TweetEntity> tweetList = new ArrayList<>();
                        for (Tweet tweet : response.body()) tweetList.add(new TweetEntity(tweet));
                        EventBus.getDefault().post(new ContentTimeLine(tweetList));
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        EventBus.getDefault().post(new MakeToast("タイムラインの取得に失敗しました"));
                        EventBus.getDefault().post(new ContentTimeLine(null));
                    }
                });
    }

    public void getMentionTimeLine() {
        if (apiClient == null) getApiClient();
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.mentionsTimeline(50, null, null, false, false, true).
                enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        ArrayList<TweetEntity> tweetList = new ArrayList<>();
                        for (Tweet tweet : response.body()) tweetList.add(new TweetEntity(tweet));
                        EventBus.getDefault().post(new ContentTimeLine(tweetList));
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        EventBus.getDefault().post(new MakeToast("タイムラインの取得に失敗しました"));
                        EventBus.getDefault().post(new ContentTimeLine(null));
                    }
                });
    }

    public void retweet(Long id) {
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.retweet(id, false).
                enqueue(new Callback<Tweet>() {
                    @Override
                    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                        EventBus.getDefault().post(new onSuccessRetweet());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        EventBus.getDefault().post(new MakeToast("リツイートに失敗しました"));
                    }
                });
    }

    public void unRetweet(Long id) {
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.unretweet(id, false).
                enqueue(new Callback<Tweet>() {
                    @Override
                    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                        EventBus.getDefault().post(new onSuccessRetweet());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        EventBus.getDefault().post(new MakeToast("リツイート解除に失敗しました"));
                    }
                });
    }
}
