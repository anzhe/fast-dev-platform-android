package view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.util.ToastUtils;

public class CustomDialog extends AlertDialog implements android.view.View.OnClickListener {

	private RatingBar evaluate_01, evaluate_02, evaluate_03, evaluate_04;
	private Button btn_cancel, btn_confirm;
	private TextView title;
	private Context context;
	private OnConfirmListener onConfirmListener;

	/**
	 * 确定键监听器
	 */
	public interface OnConfirmListener {

		public void comfirm(float evaluate_01, float evaluate_02, float evaluate_03, float evaluate_04);

	}

	public CustomDialog(Context context, OnConfirmListener onConfirmListener) {
		super(context);
		super.show();
		this.onConfirmListener = onConfirmListener;
		this.context = context;
		setCustomDialog();
	}

	private void setCustomDialog() {
		View mView = LayoutInflater.from(context).inflate(R.layout.dialog, null);
		title = (TextView) mView.findViewById(R.id.title);
		evaluate_01 = (RatingBar) mView.findViewById(R.id.evaluate_01);
		evaluate_02 = (RatingBar) mView.findViewById(R.id.evaluate_02);
		evaluate_03 = (RatingBar) mView.findViewById(R.id.evaluate_03);
		evaluate_04 = (RatingBar) mView.findViewById(R.id.evaluate_04);
		btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
		btn_confirm = (Button) mView.findViewById(R.id.btn_confirm);
		btn_cancel.setOnClickListener(this);
		btn_confirm.setOnClickListener(this);
		super.setContentView(mView);
	}

	@Override
	public void setContentView(int layoutResID) {

	}

	@Override
	public void setContentView(View view) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			super.dismiss();
			break;
		case R.id.btn_confirm:
			if (evaluate_01.getRating() == 0) {
				ToastUtils.showShort(context, "请选择专业水平");
				break;
			}
			if (evaluate_02.getRating() == 0) {
				ToastUtils.showShort(context, "请选择职业素养");
				break;
			}
			if (evaluate_03.getRating() == 0) {
				ToastUtils.showShort(context, "请选择响应速度");
				break;
			}
			if (evaluate_04.getRating() == 0) {
				ToastUtils.showShort(context, "请选择总体评价");
				break;
			}
			onConfirmListener.comfirm(evaluate_01.getRating(), evaluate_02.getRating(), evaluate_03.getRating(), evaluate_04.getRating());
			break;
		}
	}

}
