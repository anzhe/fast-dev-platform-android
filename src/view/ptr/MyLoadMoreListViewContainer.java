package view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class MyLoadMoreListViewContainer extends MyLoadMoreContainerBase {

	private ListView mListView;

	public MyLoadMoreListViewContainer(Context context) {
		super(context);
	}

	public MyLoadMoreListViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void addFooterView(View view) {
		mListView.addFooterView(view);
	}

	@Override
	protected void removeFooterView(View view) {
		mListView.removeFooterView(view);
	}

	@Override
	protected AbsListView retrieveAbsListView() {
		mListView = (ListView) getChildAt(0);
		return mListView;
	}

}
