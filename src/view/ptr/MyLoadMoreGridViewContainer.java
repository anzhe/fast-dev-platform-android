package view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class MyLoadMoreGridViewContainer extends MyLoadMoreContainerBase {

	private GridViewWithHeaderAndFooter mGridView;

	public MyLoadMoreGridViewContainer(Context context) {
		super(context);
	}

	public MyLoadMoreGridViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void addFooterView(View view) {
		mGridView.addFooterView(view);
	}

	@Override
	protected void removeFooterView(View view) {
		mGridView.removeFooterView(view);
	}

	@Override
	protected AbsListView retrieveAbsListView() {
		View view = getChildAt(0);
		mGridView = (GridViewWithHeaderAndFooter) view;
		return mGridView;
	}

}
