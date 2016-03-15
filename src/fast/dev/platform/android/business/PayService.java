package fast.dev.platform.android.business;

import java.util.HashMap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.content.Context;
import android.text.TextUtils;
import fast.dev.platform.android.bean.business.QueryOrderStatusBussniseBean;
import fast.dev.platform.android.bean.business.SubmitTempOrderBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 支付相关服务
 */
public class PayService implements ErrorListener {

	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;

	public PayService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;
	}

	/**
	 * 提交临时订单数据
	 * 
	 * @param showLoadingDialog
	 * @param submitParams
	 * @param callback
	 */
	public void submitTempOrder(final boolean showLoadingDialog, HashMap<String, String> submitParams, final SubmitTempOrderCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				SubmitTempOrderBusinessBean submitTempOrderBusinessBean = gson.fromJson(response, SubmitTempOrderBusinessBean.class);
				if (CommonUtils.handleRequestResult(mContext, submitTempOrderBusinessBean)) {
					if (callback != null) {
						callback.doCallback(submitTempOrderBusinessBean.getData().getORDER_NUM());
					}
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/orderTemp/save", submitParams, listener, this, 0);
	}

	/**
	 * 查询微信订单状态
	 * 
	 * @param queryParams
	 */
	public void queryOrderStatus(final boolean showLoadingDialog, HashMap<String, String> queryParams, final QueryOrderStatusCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				QueryOrderStatusBussniseBean queryOrderStatusBussniseBean = gson.fromJson(response, QueryOrderStatusBussniseBean.class);
				if (CommonUtils.handleRequestResult(mContext, queryOrderStatusBussniseBean)) {
					if (TextUtils.equals(queryOrderStatusBussniseBean.getData().getTRADE_STATE(), "SUCCESS")) {
						if (callback != null) {
							callback.doCallback();
						}
					}
				}
				
				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/wxpay/query", queryParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface SubmitTempOrderCallback {

		public void doCallback(String orderNum);

	}
	
	public interface QueryOrderStatusCallback {

		public void doCallback();

	}
	
}
