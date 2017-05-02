package kotako.java.info.bluebird.model.loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

public abstract class AbstractAsyncTaskLoader<D> extends AsyncTaskLoader<D> {
    private D result;
    private boolean isStarted;

    public AbstractAsyncTaskLoader(Context context) {
        super(context);
        isStarted = false;
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }
        if (!isStarted || takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        isStarted = true;
    }

    @Override
    public void deliverResult(D data) {
        result = data;
        super.deliverResult(data);
    }
}
