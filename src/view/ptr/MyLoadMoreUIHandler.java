package view.ptr;

public interface MyLoadMoreUIHandler {

	public void onLoading(MyLoadMoreContainer container);

	public void onLoadFinish(MyLoadMoreContainer container, boolean empty, boolean hasMore, boolean showLoadedFinishText);

	public void onWaitToLoadMore(MyLoadMoreContainer container);

	public void onLoadError(MyLoadMoreContainer container, int errorCode, String errorMessage);

}
