package fast.dev.platform.android.http.volley;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.content.Context;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.DeviceInfoUtils;

public class VolleyWrapper {

	/**
	 * 最大重试次数
	 */
	public static final int MAX_RETRY_NUM = 1;
	
	private int timeoutMs = 20 * 1000;// 超时时间
	
	private static SSLSocketFactory sslSocketFactory;
	private RequestQueue requestQueue;

	static {
		try {
			InputStream certificate = MyApplication.getInstance().getAssets().open(CommonData.HTTPS_CERT_FILE_NAME);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(certificate, CommonData.HTTPS_CERT_FILE_PASSWORD.toCharArray());
			certificate.close();
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keyStore, CommonData.HTTPS_CERT_FILE_PASSWORD.toCharArray());
			SSLContext sslContext = SSLContext.getInstance("TLS");
			TrustManager[] trustManagers = { new AllTrustX509TrustManager() };
			sslContext.init(keyManagerFactory.getKeyManagers(), trustManagers, null);
			sslSocketFactory = sslContext.getSocketFactory();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);

			TestinAgent.uploadException(MyApplication.getInstance(), "HTTPS证书初始化失败", e);
		}
	}

	public VolleyWrapper(Context context) {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
			
		});
		requestQueue = Volley.newRequestQueue(context, new HurlStack(null, sslSocketFactory));
	}
	
	public void post(String url, final HashMap<String, String> params, Listener<String> listener,
			ErrorListener errorListener, int retryNum) {
		StringRequest request = new StringRequest(Method.POST, url, listener, errorListener) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Connection", "Close");
				headers.put("Session", CommonUtils.getSession());
				headers.put("PhoneMode", android.os.Build.MODEL);
				headers.put("IMEI", DeviceInfoUtils.getDeviceId());
				headers.put("ClientType", "1");
				headers.put("Version", DeviceInfoUtils.getVersionName(MyApplication.getInstance()));
				headers.put("OS", "android" + android.os.Build.VERSION.RELEASE);
				headers.put("MAC", DeviceInfoUtils.getMac() != null ? DeviceInfoUtils.getMac() : "");
				headers.put("Resolution", DeviceInfoUtils.getResolution());
				headers.put("UserType", CommonUtils.getUserType());
				headers.put("UserID", CommonUtils.getUserId());
				headers.put("LawerID", CommonUtils.getLawyerId());
				return headers;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				for (Entry<String, String>  entry : params.entrySet()) {
					if (entry.getValue() == null) {
						paramsMap.put(entry.getKey(), "");
					} else {
						paramsMap.put(entry.getKey(), entry.getValue());
					}
				}
				return paramsMap;
			}

			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				for (String key : response.headers.keySet()) {
					if (key.equalsIgnoreCase("Session")) {
						CommonUtils.setSession(response.headers.get("Session"));
						break;
					}
				}

				return super.parseNetworkResponse(response);
			}

		};
		request.setRetryPolicy(new DefaultRetryPolicy(timeoutMs, retryNum, 1f));
		requestQueue.add(request);
	}

}
