package fast.dev.platform.android.listener;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class MoneyTextFocusChangeListener implements OnFocusChangeListener {

	private EditText mEditText;
	private boolean resetToZero = false;
	
	public MoneyTextFocusChangeListener(EditText editText) {
		this(editText, true);
	}
	
	public MoneyTextFocusChangeListener(EditText editText, boolean resetToZero) {
		this.mEditText = editText;
		this.resetToZero = resetToZero;
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		Editable editable = mEditText.getText();
		if (hasFocus) {
			Selection.setSelection(mEditText.getEditableText(), editable.toString().length());// 设置光标的位置
		} else {
			if (TextUtils.isEmpty(editable.toString()) && resetToZero) {
				mEditText.setText("0");
			}
			if (editable.toString().endsWith(".")) {
				mEditText.setText(editable.toString().substring(0, editable.toString().length() - 1));
			}
		}
	}

}
