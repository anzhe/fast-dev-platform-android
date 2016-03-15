package fast.dev.platform.android.http.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.DeviceInfoUtils;

public class OkHttpWrapper {

	private final long CONNECTION_TIMEOUT = 20 * 1000;// 连接超时时间
	private final long SOCKET_TIMEOUT = 20 * 1000;// Socket超时时间
	
	private static OkHttpWrapper instance = null;
	
	private static OkHttpClientManager mOkHttpClientManager = null;
	private static OkHttpClient httpsClient = null;
	
	private static OkHttpClient httpClient = null;
	
	private static Request.Builder requestBuilder = null;

	private OkHttpWrapper() throws IOException {
		/**
		 * HTTPS
		 */
		mOkHttpClientManager = OkHttpClientManager.getInstance();
		httpsClient = mOkHttpClientManager.getOkHttpClient();
		/**
		 * 设置超时时间
		 */
		httpsClient.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
		httpsClient.setReadTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
		/**
		 * 读取证书
		 */
		InputStream inputStream = MyApplication.getInstance().getAssets().open(CommonData.HTTPS_CERT_FILE_NAME);
		mOkHttpClientManager.setCertificates(null, inputStream, CommonData.HTTPS_CERT_FILE_PASSWORD);
		
		/**
		 * HTTP
		 */
		httpClient = new OkHttpClient();
		/**
		 * 设置超时时间
		 */
		httpClient.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
		httpClient.setReadTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
		
		requestBuilder = new Request.Builder();
	}
	
	public static OkHttpWrapper getInstance() throws IOException {
		if (instance == null) {
			instance = new OkHttpWrapper();
		}
		return instance;
	}
	
	private void addHeaders() {
		requestBuilder.header("Connection", "Close");
		requestBuilder.header("Session", CommonUtils.getSession());
		requestBuilder.header("PhoneMode", android.os.Build.MODEL);
		requestBuilder.header("IMEI", DeviceInfoUtils.getDeviceId());
		requestBuilder.header("ClientType", "1");
		requestBuilder.header("Version", DeviceInfoUtils.getVersionName(MyApplication.getInstance()));
		requestBuilder.header("OS", "android" + android.os.Build.VERSION.RELEASE);
		requestBuilder.header("MAC", DeviceInfoUtils.getMac() != null ? DeviceInfoUtils.getMac() : "");
		requestBuilder.header("Resolution", DeviceInfoUtils.getResolution());
		requestBuilder.header("UserType", CommonUtils.getUserType());
		requestBuilder.header("UserID", CommonUtils.getUserId());
		requestBuilder.header("LawerID", CommonUtils.getLawyerId());
	}

	public String post(String url, HashMap<String, String> params) throws Exception {
		return post(url, params, false);
	}
	
	public String post(String url, HashMap<String, String> params, boolean useHttps) throws Exception {
		addHeaders();
		
		FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
		if (params != null) {
			for (Entry<String, String> param : params.entrySet()) {
				formEncodingBuilder.add(param.getKey(), param.getValue() == null ? "" : param.getValue());
			}
		}
		Request request = requestBuilder.url(url).post(formEncodingBuilder.build()).build();
		Response response = null;
		if (useHttps) {
			response = httpsClient.newCall(request).execute();
		} else {
			response = httpClient.newCall(request).execute();
		}
		if (response.isSuccessful()) {
			CommonUtils.setSession(response.header("Session"));
		}
		return response.body().string();
	}
	
}
