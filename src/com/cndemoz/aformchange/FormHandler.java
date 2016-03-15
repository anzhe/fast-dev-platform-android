package com.cndemoz.aformchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 
 * 表单处理器
 * 
 * @author ken.cai
 * 
 */
public class FormHandler {
    private static final String TAG = FormHandler.class.getSimpleName();

    // 获得所有EditText view
    private List<View> views;

    private HashMap<Integer, String> preTextMap;
    private HashMap<Integer, Boolean> isTexTChangeMap;

    public FormHandler() {
	init(null);
    }

    public FormHandler(List<View> views) {
	init(views);
    }

    @SuppressLint("UseSparseArrays")
    private void init(List<View> views) {
	this.views = views;
	preTextMap = new HashMap<Integer, String>();
	isTexTChangeMap = new HashMap<Integer, Boolean>();
    }

    public FormHandler setViews(List<View> views) {
	this.views = views;
	return this;
    }

    public FormHandler add(View view) {
	this.views.add(view);
	return this;
    }

    // 获取初始值
    // 监听EditText改变
    //
    public void initTextAndTextChangedListener() {
	if (views == null || views.size() == 0) {
	    return;
	}
	for (View view : views) {

	    TextView editText = (TextView) view;

	    preTextMap.put(editText.getId(), editText.getText() + "");// 获取初始值

	    editText.addTextChangedListener(new TextWatcher() { // 监听EditText改变

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		    for (View view : views) {
			//
			TextView editText = (TextView) view;
			if (TextUtils.equals(preTextMap.get(editText.getId()), editText.getText())) {
			    isTexTChangeMap.put(editText.getId(), false);// 没有改变
			} else {
			    isTexTChangeMap.put(editText.getId(), true);// 有改变
			}
		    }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	    });
	}

    }

    // EditText改变
    @SuppressWarnings("rawtypes")
    public boolean isTextChange() {
	for (Map.Entry entry : isTexTChangeMap.entrySet()) {
	    Log.d(TAG, entry.getKey() + "=" + entry.getValue());
	    if ((Boolean) entry.getValue()) {
		return true;
	    }
	}
	return false;
    }

    // 返回时获取EditText值 与 初始值对比，如果有一个不同 返回 false
    //

    // 考虑不是EditText的情况

}
