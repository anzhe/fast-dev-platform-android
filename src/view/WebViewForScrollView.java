package view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class WebViewForScrollView extends WebView {

	public WebViewForScrollView(Context context) {
		super(context);
	}

	public WebViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WebViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
