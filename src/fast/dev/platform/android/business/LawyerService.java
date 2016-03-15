package fast.dev.platform.android.business;

import java.util.HashMap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.content.Context;
import android.content.SharedPreferences;
import fast.dev.platform.android.bean.business.GetLawyerInfoBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 律师相关服务
 */
public class LawyerService implements ErrorListener {

	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;
	private SharedPreferences user_sp;

	public LawyerService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;

		user_sp = CommonUtils.user_sp(context);
	}

	/**
	 * 获取律师信息
	 * 
	 * @param showLoadingDialog
	 * @param callback
	 */
	public void getLawyerInfo(final boolean showLoadingDialog, final GetLawyerInfoCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("lawerid", user_sp.getString("lawyerid", ""));
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetLawyerInfoBusinessBean getLawyerInfoBusinessBean = gson.fromJson(response, GetLawyerInfoBusinessBean.class);
				if (CommonUtils.handleRequestResult(mContext, getLawyerInfoBusinessBean)) {
					user_sp.edit().putString("relName", getLawyerInfoBusinessBean.getData().getREL_NAME()).apply();
					user_sp.edit().putString("myPhone", getLawyerInfoBusinessBean.getData().getMY_PHONE()).apply();
					user_sp.edit().putString("email", getLawyerInfoBusinessBean.getData().getEMAIL()).apply();
					user_sp.edit().putString("province", getLawyerInfoBusinessBean.getData().getPROVINCE()).apply();
					user_sp.edit().putString("city", getLawyerInfoBusinessBean.getData().getCITY()).apply();
					user_sp.edit().putString("district", getLawyerInfoBusinessBean.getData().getCOUNTY()).apply();
					user_sp.edit().putString("licenceNum", getLawyerInfoBusinessBean.getData().getLICENCE_NUM()).apply();
					user_sp.edit().putString("layFirm", getLawyerInfoBusinessBean.getData().getLAY_FIRM()).apply();
					user_sp.edit().putString("address", getLawyerInfoBusinessBean.getData().getADDRESS()).apply();
					user_sp.edit().putString("idnumber", getLawyerInfoBusinessBean.getData().getID_NUMBER()).apply();
					user_sp.edit().putString("worktime", getLawyerInfoBusinessBean.getData().getWORK_TIME()).apply();
					user_sp.edit().putString("goodfield", getLawyerInfoBusinessBean.getData().getGOOD_FIELD()).apply();
					user_sp.edit().putString("profile", getLawyerInfoBusinessBean.getData().getPROFILE()).apply();
					user_sp.edit().putString("idphotoface", getLawyerInfoBusinessBean.getData().getID_PHOTO_FACE()).apply();
					user_sp.edit().putString("idphotooppo", getLawyerInfoBusinessBean.getData().getID_PHOTO_OPPO()).apply();
					user_sp.edit().putString("licencephotoface", getLawyerInfoBusinessBean.getData().getLICENCE_PHOTO_FACE()).apply();
					user_sp.edit().putString("licencephotooppo", getLawyerInfoBusinessBean.getData().getLICENCE_PHOTO_OPPO()).apply();
					user_sp.edit().putInt("status", getLawyerInfoBusinessBean.getData().getSTATUS()).apply();

					if (callback != null) {
						callback.doCallback();
					}
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/detail", queryParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface GetLawyerInfoCallback {

		public void doCallback();

	}
	
}
