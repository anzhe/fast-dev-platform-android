package view.ptr;

import android.view.View;
import android.widget.AbsListView;

public interface MyLoadMoreContainer {

	public void setShowLoadingForFirstPage(boolean showLoading);

	public void setAutoLoadMore(boolean autoLoadMore);

	public void setOnScrollListener(AbsListView.OnScrollListener l);

	public void setLoadMoreView(View view);

	public void setLoadMoreUIHandler(MyLoadMoreUIHandler handler);

	public void setLoadMoreHandler(MyLoadMoreHandler handler);

	public void loadMoreFinish(boolean emptyResult, boolean hasMore, boolean showLoadedFinishText);

	public void loadMoreError(int errorCode, String errorMessage);

}
