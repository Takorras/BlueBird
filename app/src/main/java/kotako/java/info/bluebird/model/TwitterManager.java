package kotako.java.info.bluebird.model;

import android.util.Log;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import kotako.java.info.bluebird.model.event.*;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class TwitterManager {
    private static TwitterSession twitterSession;
    private static TwitterApiClient apiClient;

    public TwitterSession getTwitterSession() {
        twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();

        if (twitterSession == null) {
            // TwitterSessionを作成した事がない場合はイベントを作成
            //!TODO SharedPreferenceからキーを検索したほうがいいのでは
            Log.d("Develop Messages", "FabricのgetActiveSessionしたけどSession取得できなかったよ");
            EventBus.getDefault().post(new SessionNotFound());
            return null;
        }
        Log.d("Develop Message", "Success to get Twitter Session");
        EventBus.getDefault().post(new TwitterSessionEvent());
        return twitterSession;
    }

    public void setTwitterSession(TwitterSession session) {
        twitterSession = session;
        new AccessTokenManager().storeAccessToken(session.getAuthToken().token, session.getAuthToken().secret);
        EventBus.getDefault().post(new TwitterSessionEvent());
    }

    public void getApiClient() {
        // !TODO イベントにユーザ情報をつけてあげて、イベント取得側からいい感じにやる
        if (twitterSession == null) twitterSession = getTwitterSession();
        apiClient = Twitter.getApiClient(twitterSession);
        Log.d("DevelopMassage", "Success to get Twitter REST API");
        EventBus.getDefault().post(new TwitterAPI());
    }

    public void updateStatus(String content) {
        if (apiClient == null) getApiClient();
        StatusesService statusesService = apiClient.getStatusesService();

        statusesService.update("hog", null, false, null, null, null, false, false, null).
                enqueue(new Callback<Tweet>() {
                    @Override
                    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                        // success
                        EventBus.getDefault().post(new UpdateStatusSuccessEvent());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        // failure
                    }
                });
    }

    public void getHomeTimeLine() {
        // !TODO オプションでHOMEタイムラインかUserリストか選択して取得できるようにする
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.homeTimeline(50, null, null, false, true, true, true).
                enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        Log.d("Develop Message", "タイムラインの取得に成功したよ");
                        EventBus.getDefault().post(new TimeLineEvent(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        Log.d("!!!", "しっぱい");
                        EventBus.getDefault().post(new FailEvent("失敗しちゃった"));
                    }
                });
    }

    public void getMentionTimeLine() {
        if (apiClient == null) return;
        StatusesService statusesService = apiClient.getStatusesService();
        statusesService.mentionsTimeline(50, null, null, false, false, true).
                enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        EventBus.getDefault().post(new TimeLineEvent(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        EventBus.getDefault().post(new FailEvent("miss"));
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
                        EventBus.getDefault().post(new RetweetSuccessEvent());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        EventBus.getDefault().post(new FailEvent("リツイートに失敗したよ"));
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
                        EventBus.getDefault().post(new RetweetSuccessEvent());
                    }

                    @Override
                    public void onFailure(Call<Tweet> call, Throwable t) {
                        EventBus.getDefault().post(new FailEvent("リツイートの解除に失敗したよ"));
                    }
                });
    }
}
