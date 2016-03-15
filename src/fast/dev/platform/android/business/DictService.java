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
import android.text.TextUtils;
import fast.dev.platform.android.bean.DictBean;
import fast.dev.platform.android.bean.business.DictBussniseBean;
import fast.dev.platform.android.bean.business.SystemDictBussniseBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.database.AreaDBService;
import fast.dev.platform.android.database.CaseTypeDBService;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 字典相关服务
 */
public class DictService implements ErrorListener {

	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;
	
	private SharedPreferences dict_sp;

	public DictService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;
		this.dict_sp = CommonUtils.dict_sp(context);
	}

	/**
	 * 系统字典
	 * 
	 * @param showLoadingDialog
	 * @param callback
	 */
	public void systemDict(final boolean showLoadingDialog, final SystemDictCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				SystemDictBussniseBean systemDictBussniseBean = gson.fromJson(response, SystemDictBussniseBean.class);
				if (CommonUtils.handleRequestResult(mContext, systemDictBussniseBean)) {
					if (systemDictBussniseBean.getData().getDictList() != null && !systemDictBussniseBean.getData().getDictList().isEmpty()) {
						for (DictBean dictBean : systemDictBussniseBean.getData().getDictList()) {
							dict_sp.edit().putString(dictBean.getDICT_CODE(), dictBean.getDICT_VALUE()).apply();
						}
						String DICT_IMAGE_UPLORD = dict_sp.getString("DICT_IMAGE_UPLORD", "");
						if (!TextUtils.isEmpty(DICT_IMAGE_UPLORD)) {
							CommonData.FILE_SERVER_UPLOAD_URL = DICT_IMAGE_UPLORD;
							DICT_IMAGE_UPLORD = DICT_IMAGE_UPLORD.substring(7);
							DICT_IMAGE_UPLORD = DICT_IMAGE_UPLORD.substring(0, DICT_IMAGE_UPLORD.indexOf("/"));
							CommonData.FILE_SERVER_GET_IMAGE_URL = "http://" + DICT_IMAGE_UPLORD + "/FileManager/get?vid=";
						}
					}
				}
				if (callback != null) {
					callback.loadFinished();
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("dicttype", "3");
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/dict/list", queryParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}

	/**
	 * 地区字典
	 * 
	 * @param showLoadingDialog
	 * @param callback
	 */
	public void areaDict(final boolean showLoadingDialog, final AreaDictCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				DictBussniseBean dictBussniseBean = gson.fromJson(response, DictBussniseBean.class);
				boolean success = false;
				if (CommonUtils.handleRequestResultWithoutErrorInfo(mContext, dictBussniseBean)) {
					if (dictBussniseBean.getData().getDictList() != null && !dictBussniseBean.getData().getDictList().isEmpty()) {
						AreaDBService dbService = new AreaDBService(mContext);
						dbService.detele();
						dbService.batchInsert(dictBussniseBean.getData().getDictList());
						
						success = true;
					}
				}
				
				if (callback != null) {
					callback.loadFinished(success);
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("dicttype", "0");
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/dict/list", queryParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	/**
	 * 案件类型字典
	 * 
	 * @param showLoadingDialog
	 * @param callback
	 */
	public void caseTypeDict(final boolean showLoadingDialog, final CaseTypeDictCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				DictBussniseBean dictBussniseBean = gson.fromJson(response, DictBussniseBean.class);
				boolean success = false;
				if (CommonUtils.handleRequestResultWithoutErrorInfo(mContext, dictBussniseBean)) {
					if (dictBussniseBean.getData().getDictList() != null && !dictBussniseBean.getData().getDictList().isEmpty()) {
						CaseTypeDBService dbService = new CaseTypeDBService(mContext);
						dbService.detele();
						dbService.batchInsert(dictBussniseBean.getData().getDictList());
						
						success = true;
					}
				}
				
				if (callback != null) {
					callback.loadFinished(success);
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("dicttype", "1");
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/dict/list", queryParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface SystemDictCallback {

		public void loadFinished();

	}
	
	public interface AreaDictCallback {

		public void loadFinished(boolean success);

	}
	
	public interface CaseTypeDictCallback {
		
		public void loadFinished(boolean success);
		
	}
	
}
