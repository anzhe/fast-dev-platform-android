package fast.dev.platform.android.business;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.LoadingActivity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.bean.VersionBean;
import fast.dev.platform.android.bean.VersionBean.AppMap;
import fast.dev.platform.android.bean.VersionBean.DicMap;
import fast.dev.platform.android.bean.VersionBean.OverImage;
import fast.dev.platform.android.bean.business.GetAppLatestVersionInfoBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.DeviceInfoUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 系统相关服务
 */
public class SystemService implements ErrorListener {

	private DictService dictService;
	
	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;

	private SharedPreferences dict_sp;
	
	public SystemService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;
		
		this.dict_sp = CommonUtils.dict_sp(context);
		
		this.dictService = new DictService(context, volleyWrapper);
	}

	/**
	 * 获取最新版本信息
	 * 
	 * @param showLoadingDialog
	 * @param showDialog 是否弹出更新对话框
	 * @param callback
	 */
	public void getAppLatestVersionInfo(final boolean showLoadingDialog, final boolean showDialog, final GetAppLatestVersionInfoCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			private boolean needUpdate = false;// 是否需要更新
			
			@Override
			public void onResponse(String response) {
				GetAppLatestVersionInfoBusinessBean getAppLatestVersionInfoBusinessBean = gson.fromJson(response, GetAppLatestVersionInfoBusinessBean.class);
				if (CommonUtils.handleRequestResultWithoutErrorInfo(mContext, getAppLatestVersionInfoBusinessBean)) {
					
					final VersionBean versionBean = getAppLatestVersionInfoBusinessBean.getData();
					
					DicMap dicMap = versionBean.getDictMap();
					if (dicMap != null) {
						if (dict_sp.getLong("case_version", 1) < dicMap.getCASEVERSION()) {
							ToastUtils.showShort(mContext, "案件类型字典有新版本，更新中……");
							dict_sp.edit().putLong("case_version", dicMap.getCASEVERSION()).apply();
							dictService.caseTypeDict(true, null);
						}
						if (dict_sp.getLong("area_version", 1) < dicMap.getAREAVERSION()) {
							ToastUtils.showShort(mContext, "地区字典有新版本，更新中……");
							dict_sp.edit().putLong("area_version", dicMap.getAREAVERSION()).apply();
							dictService.areaDict(true, null);
						}
					}
					
					LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
					View titleView = mLayoutInflater.inflate(R.layout.update_dialog_title, null);
					TextView version = (TextView) titleView.findViewById(R.id.version);
					String release_note = "";
					String btn_confirm = "";
					final AppMap appMap = versionBean.getAppMap();
					if (appMap != null) {
						if (DeviceInfoUtils.getVersionCode(mContext) < appMap.getVERSION_CODE()) {
							needUpdate = true;
							version.setText("更新   v" + appMap.getVERSION());
							release_note = appMap.getRELEASE_NOTE();
							btn_confirm = "下载新版本";
						} else {
							version.setText("提示");
							release_note = "该版本已是最新版本。";
							btn_confirm = "确定";
						}
						ListView mListView = (ListView) mLayoutInflater.inflate(R.layout.update_dialog, null);
						mListView.setAdapter(new ArrayAdapter<String>(mContext, R.layout.update_dialog_list_item,
								new String[] { release_note }));
						AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
						builder.setView(mListView);
						builder.setCustomTitle(titleView);
						if (needUpdate) {
							if (appMap.getIF_FORCE() == 0) {// 是否强制升级（1是，0否）
								builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog, int which) {
										if (callback != null) {
											List<OverImage> overImageList = versionBean.getOverImageList();
											StringBuffer sb = new StringBuffer();
											for (int i = 0; i < overImageList.size(); i++) {
												sb.append(overImageList.get(i).getIMAGE_URL());
												if (i != overImageList.size() - 1) {
													sb.append(";");
												}
											}
											callback.doCallback(sb.toString());
										}
									}

								});
							} else {
								builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog, int which) {
										MyApplication.getInstance().exit();
									}

								});
							}
						}
						builder.setPositiveButton(btn_confirm, new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (needUpdate) {
									ToastUtils.showShort(mContext, "开始下载…");

									DownloadManager mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
									Uri uri = Uri.parse(appMap.getFILE_URL());
									DownloadManager.Request request = new Request(uri);
									// 设置下载路径和文件名
									request.setDestinationInExternalPublicDir("cache", "lawApp.apk");
									request.setDescription("律师同行");
									request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
									request.setMimeType("application/vnd.android.package-archive");
									// 设置为可被媒体扫描器找到
									request.allowScanningByMediaScanner();
									// 设置为可见和可管理
									request.setVisibleInDownloadsUi(true);
									long download_id = mDownloadManager.enqueue(request);
									// 把当前下载的ID保存起来
									SharedPreferences cache_sp = mContext.getSharedPreferences("cache_sp", Context.MODE_PRIVATE);
									cache_sp.edit().putLong("download_id", download_id).apply();

									try {
										Field mShowing = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
										mShowing.setAccessible(true);
										mShowing.set(dialog, false);
									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									if (callback != null) {
										List<OverImage> overImageList = versionBean.getOverImageList();
										StringBuffer sb = new StringBuffer();
										for (int i = 0; i < overImageList.size(); i++) {
											sb.append(overImageList.get(i).getIMAGE_URL());
											if (i != overImageList.size() - 1) {
												sb.append(";");
											}
										}
										callback.doCallback(sb.toString());
									}
								}
							}

						});
						AlertDialog updateAlertDialog = builder.create();
						updateAlertDialog.setCancelable(false);
						updateAlertDialog.setCanceledOnTouchOutside(false);
						if (showDialog || needUpdate) {// 如果是需要更新的则尽管设置为不显示弹出框也会强制弹出
							updateAlertDialog.show();
						} else {
							if (callback != null) {
								List<OverImage> overImageList = versionBean.getOverImageList();
								StringBuffer sb = new StringBuffer();
								for (int i = 0; i < overImageList.size(); i++) {
									sb.append(overImageList.get(i).getIMAGE_URL());
									if (i != overImageList.size() - 1) {
										sb.append(";");
									}
								}
								callback.doCallback(sb.toString());
							}
						}
					}
				} else {
					if (mContext instanceof Activity && !((Activity) mContext).isFinishing()) {
						new AlertDialog.Builder(mContext).setTitle("提示").setMessage("网络异常，请稍后重试。")
								.setPositiveButton("点击重试", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(mContext, LoadingActivity.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
								mContext.startActivity(intent);
							}

						}).setNegativeButton("退出", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								MyApplication.getInstance().exit();
							}

						}).show();
					}
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("appType", "0");
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				CommonUtils.dismissProgressDialog();

				if (mContext instanceof Activity && !((Activity) mContext).isFinishing()) {
					new AlertDialog.Builder(mContext).setTitle("提示").setMessage("网络异常，请稍后重试。")
							.setPositiveButton("点击重试", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(mContext, LoadingActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							mContext.startActivity(intent);
						}

					}).setNegativeButton("退出", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							MyApplication.getInstance().exit();
						}

					}).show();
				}

				CrashReport.postCatchedException(error);

				TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
			}

		};
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/version/detail", queryParams, listener,
				errorListener, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface GetAppLatestVersionInfoCallback {

		public void doCallback(String output);

	}
	
}
