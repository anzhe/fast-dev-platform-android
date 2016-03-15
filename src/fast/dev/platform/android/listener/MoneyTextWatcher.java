package fast.dev.platform.android.listener;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 实现金额输入验证，仅能输入两个小数点
 */
public class MoneyTextWatcher implements TextWatcher {

	private EditText mEditText;
	private int selectionStart;
	private int selectionEnd;
	private boolean limit = false;
	private double limit_num;

	public MoneyTextWatcher(EditText editText) {
		this(editText, false, 0);
	}

	public MoneyTextWatcher(EditText editText, boolean limit, double limit_num) {
		this.mEditText = editText;
		this.limit = limit;
		this.limit_num = limit_num;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String input = s.toString();
		if (TextUtils.isEmpty(input)) {
			return;
		}
		if (input.startsWith(".")) {// 禁止第一个数字为小数点
			mEditText.setText("");
			return;
		}
		if (input.contains("..")) {// 禁止输入多个小数点
			mEditText.setText(input.replace("..", "."));
			return;
		}
		if (input.length() > 1 && input.startsWith("0") && !input.contains(".")) {
			mEditText.setText(input.substring(1));
		}
		if (input.contains(".")) {
			int index = input.indexOf(".");
			String prefix = input.substring(0, index);
			String suffix = input.substring(index + 1);
			if (suffix.length() > 2) {
				input = prefix + "." + suffix.substring(0, 2);
				mEditText.setText(input);
			}
		}
		if (limit) {
			selectionStart = this.mEditText.getSelectionStart();
			selectionEnd = this.mEditText.getSelectionEnd();
			Editable editable = this.mEditText.getText();
			if (editable.toString().endsWith(".")) {
				return;
			}
			if (Double.parseDouble(editable.toString()) > limit_num) {
				editable.delete(selectionStart - 1, selectionEnd);
				this.mEditText.setText(editable);
				//this.mEditText.setSelection(selectionStart);
			}
		}
		
		Selection.setSelection(mEditText.getEditableText(), mEditText.getText().length());// 设置光标的位置
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

}
