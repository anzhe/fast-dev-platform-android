package com.cndemoz.aformchange;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * 
 * View 处理器
 * 
 * @author ken.cai
 * 
 */
public class ViewsUtil {

    /**
     * 获得所有view
     * 
     * @param view
     * @return
     */
    public static List<View> getAllChildViews(View view) {

	List<View> allchildren = new ArrayList<View>();

	if (view instanceof ViewGroup) {

	    ViewGroup vp = (ViewGroup) view;

	    for (int i = 0; i < vp.getChildCount(); i++) {

		View viewchild = vp.getChildAt(i);

		allchildren.add(viewchild);

		allchildren.addAll(getAllChildViews(viewchild));
	    }
	}

	return allchildren;

    }

    /**
     * 获得所有EditText View
     * 
     * @param view
     * @return
     */
    public static List<View> getAllEditTexts(View view) {
	List<View> allchildren = new ArrayList<View>();

	for (View viewTemp : getAllChildViews(view)) {
	    if (viewTemp instanceof EditText) {
		allchildren.add(viewTemp);
	    }
	}

	return allchildren;
    }

    public static List<View> getAllEditTexts(Activity activity) {
	return getAllEditTexts(activity.getWindow().getDecorView());
    }
    // /**
    // * 表单数据改变提示用户关闭页面
    // */
    // public static void textChangeAlertDialog(final Activity activity) {
    // final CustomAlertDialog customAlertDialog = new
    // CustomAlertDialog(activity);
    // customAlertDialog.setTitleText(R.string.warnning);
    // customAlertDialog.setContextText(R.string.confirm_giveup_edit);
    // customAlertDialog.setOnPositiveListener(new OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // if (customAlertDialog != null && customAlertDialog.isShowing()) {
    // customAlertDialog.dismiss();
    // }
    // activity.finish();
    // }
    // });
    // customAlertDialog.setOnNegativeListener(new OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // if (customAlertDialog != null && customAlertDialog.isShowing()) {
    // customAlertDialog.dismiss();
    // }
    // }
    // });
    // customAlertDialog.show();
    // }
}
