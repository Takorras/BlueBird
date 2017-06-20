package kotako.java.info.bluebird.presentation.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class ScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager manager;

    private int previousTotal = 0;
    private int currentPage = 1;
    private boolean isLoading = true;

    public ScrollListener(LinearLayoutManager manager) {
        this.manager = manager;
        isLoading = true;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = manager.getItemCount();
        int firstVisibleItem = manager.findFirstVisibleItemPosition();

        if (isLoading) {
            isLoading = totalItemCount > previousTotal;
            previousTotal = totalItemCount;
        }

        int visibleThreshold = 2;
        if(!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
            currentPage++;
            onLoadMore(currentPage);
            isLoading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
