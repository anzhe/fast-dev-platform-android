package fast.dev.platform.android.util;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.HttpGet;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.util.Log;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.okhttp.OkHttpWrapper;

@SuppressWarnings("deprecation")
public class HttpUtils {

	private HttpUtils() {
	}

	/** 单例的HttpClient,典型的单例模式，构造方法private，调用getHttpClient返回的只有一个对象 */
	public static DefaultHttpClient httpClient;
	public static CookieStore cookieStore;// 定义一个Cookie来保存session

	// private static final String TAG = "HttpUtil";

	public static DefaultHttpClient getHttpClient() {
		// 这个经常来设置超时时间
		BasicHttpParams httpParams = new BasicHttpParams();
		// 从ConnectionManager管理的连接池中取出连接的超时时间
		ConnManagerParams.setTimeout(httpParams, 1000);
		// 连接超时(通过网络与服务器建立连接的超时时间)
		HttpConnectionParams.setConnectionTimeout(httpParams, CommonData.HTTP_TIMEOUT_CONNECT);
		// 请求超时(从服务器获取响应数据需要等待的时间)
		HttpConnectionParams.setSoTimeout(httpParams, CommonData.HTTP_TIMEOUT_SOCKET);

		// 维持一组协议
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		// 设置我们的HttpClient支持HTTP和HTTPS两种模式
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		// 使用线程安全的连接管理来创建HttpClient
		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(httpParams, schemeRegistry);

		httpClient = new DefaultHttpClient(cm, httpParams);
		return httpClient;
	}

	/**
	 * 通用的get请求方法，用HttpClient实现
	 * 
	 * @param url
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String httpGet(String url) throws IllegalStateException, IOException {
		HttpClient httpClient = getHttpClient();
		HttpGet httpRequestGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpRequestGet);
		HttpEntity entity = response.getEntity();
		// 从返回报文的实体中获取输入流
		// InputStream is = entity.getContent();
		String resultData = EntityUtils.toString(entity);
		return resultData;
		// return is;
	}

	/**
	 * 通用的get请求方法，用HttpClient实现,键值对以HashMap方式传入
	 * 
	 * @param url
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String httpGet(String url, HashMap<String, String> hm) throws IllegalStateException, IOException {
		Iterator<Entry<String, String>> itr = hm.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; itr.hasNext(); i++) {
			if (i == 0)
				sb.append("?");
			if (i > 0)
				sb.append("&");
			sb.append(itr.next().toString());
		}
		String currentTime = DateUtils.getCurrentTime();
		String watchword = MD5Util.toMD5(currentTime + CommonData.SECRET);
		sb.append("&time=" + currentTime + "&watchword=" + watchword);
		return httpGet(url + sb.toString());
	}

	/**
	 * 通用的post方法
	 * 
	 * @param url
	 * @param params
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String post(String url, HashMap<String, String> params) throws Exception {
		return post(url, params, false);
	}
	
	/**
	 * 通用的post方法
	 * 
	 * @param url
	 * @param params
	 * @param useHttps
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static String post(String url, HashMap<String, String> params, boolean useHttps) throws Exception {
		String response = null;
		try {
			response = OkHttpWrapper.getInstance().post(url, params, useHttps);
		} catch (Exception e) {
			e.printStackTrace();
			
			CrashReport.postCatchedException(e);
			
			TestinAgent.uploadException(MyApplication.getInstance(), "调用服务端接口报错", e);
		}
		return response;
		
		/*HttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);

		httpPost.addHeader("Connection", "Close");
		httpPost.addHeader("Session", CommonUtils.getSession());// 传入sessionid。
		httpPost.addHeader("PhoneMode", android.os.Build.MODEL);// 传入当前手机品牌
		httpPost.addHeader("IMEI", DeviceInfoUtils.getDeviceId());// 传入当前手机串号
		httpPost.addHeader("ClientType", "1");// 1 Android 2 IOS
		httpPost.addHeader("Version", DeviceInfoUtils.getVersionName(App.getInstance()));// 传入客户端版本
		httpPost.addHeader("OS", "android" + android.os.Build.VERSION.RELEASE);// 设备系统类型
		// android
		// 4.2/ios
		// 7.0
		httpPost.addHeader("MAC", DeviceInfoUtils.getMac());// 设备的MAC地址
		httpPost.addHeader("Resolution", DeviceInfoUtils.getResolution());// 设备的分辨率

		httpPost.addHeader("UserType", CommonUtils.getUserType());// 1 user 2 lawer
		httpPost.addHeader("UserID", CommonUtils.getUserId());// 1
															// userID，与lawerID互斥
		httpPost.addHeader("LawerID", CommonUtils.getLawyerId());// 1
																// userID，与lawerID互斥

		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
		Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
		HttpResponse response = httpClient.execute(httpPost);
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() != 200) {
			Log.e("请求响应状态：", statusLine.toString());
			throw new RuntimeException("请求响应状态：" + statusLine.toString());
		}
		Header[] headers = response.getHeaders("Session");
		if (headers != null && headers.length > 0) {
			String session = headers[0].getValue();
			CommonUtils.setSession(session);
		}
		HttpEntity entity = response.getEntity();
		String resultData = EntityUtils.toString(entity);
		return resultData;*/
	}

	/**
	 * 报文模式的http请求(post)
	 */
	public static String httpPost_baowen(String url, String content) throws ClientProtocolException, IOException {
		HttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(content, "utf-8"));
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String resultData = EntityUtils.toString(entity);
		Log.i("Mark", "1");
		return resultData;
	}

	/**
	 * 用post上传文件的方法
	 */
	public static void uploadFile(String actionUrl, String filepath) {
		try {
			URL url = new URL(actionUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data");
			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(filepath);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			/* close streams */
			fStream.close();
			ds.flush();
			/* 取得Response内容 ，开始请求 */
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			System.out.println(b.toString());
			/* 关闭DataOutputStream */
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
