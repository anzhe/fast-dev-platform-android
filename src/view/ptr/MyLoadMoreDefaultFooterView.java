package view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import in.srain.cube.R;

public class MyLoadMoreDefaultFooterView extends RelativeLayout implements MyLoadMoreUIHandler {

	private TextView mTextView;

	private boolean showLoadedFinishText = true;

	public MyLoadMoreDefaultFooterView(Context context) {
		this(context, null);
	}

	public MyLoadMoreDefaultFooterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyLoadMoreDefaultFooterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setupViews();
	}

	private void setupViews() {
		LayoutInflater.from(getContext()).inflate(R.layout.cube_views_load_more_default_footer, this);
		mTextView = (TextView) findViewById(R.id.cube_views_load_more_default_footer_text_view);
	}

	@Override
	public void onLoading(MyLoadMoreContainer container) {
		setVisibility(VISIBLE);
		mTextView.setText(R.string.cube_views_load_more_loading);
	}

	@Override
	public void onLoadFinish(MyLoadMoreContainer container, boolean empty, boolean hasMore, boolean showLoadedFinishText) {
		mTextView.setVisibility(View.VISIBLE);
		if (empty) {
			setVisibility(VISIBLE);
			mTextView.setText(fast.dev.platform.android.R.string.load_more_loaded_empty);
			return;
		}
		if (hasMore) {
			setVisibility(INVISIBLE);
		} else {
			if (showLoadedFinishText) {
				setVisibility(VISIBLE);
				mTextView.setText(R.string.cube_views_load_more_loaded_no_more);
			} else {
				mTextView.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onWaitToLoadMore(MyLoadMoreContainer container) {
		setVisibility(VISIBLE);
		mTextView.setText(R.string.cube_views_load_more_click_to_load_more);
	}

	@Override
	public void onLoadError(MyLoadMoreContainer container, int errorCode, String errorMessage) {
		mTextView.setText(R.string.cube_views_load_more_error);
	}

	public boolean isShowLoadedFinishText() {
		return showLoadedFinishText;
	}

	public void setShowLoadedFinishText(boolean showLoadedFinishText) {
		this.showLoadedFinishText = showLoadedFinishText;
	}

}
