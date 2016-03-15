package fast.dev.platform.android.business;

import java.util.HashMap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.content.Context;
import fast.dev.platform.android.bean.business.SubmitInformBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 咨询相关服务
 */
public class ConsultService implements ErrorListener {

	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;

	public ConsultService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;
	}

	/**
	 * 提交举报内容
	 * 
	 * @param showLoadingDialog
	 * @param callback
	 */
	public void submitInform(final boolean showLoadingDialog, HashMap<String, String> submitParams, final SubmitInformCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				SubmitInformBusinessBean submitInformBusinessBean = gson.fromJson(response, SubmitInformBusinessBean.class);
				if (CommonUtils.handleRequestResult(mContext, submitInformBusinessBean)) {
					ToastUtils.showShort(mContext, submitInformBusinessBean.getResultdesc());
					if (callback != null) {
						callback.doCallback();
					}
				}
				
				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/report/save", submitParams, listener, this, 0);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface SubmitInformCallback {

		public void doCallback();

	}
	
}
