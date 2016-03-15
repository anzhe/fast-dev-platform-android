package fast.dev.platform.android.util;

import org.json.JSONObject;

public class JsonUtil {
	public static JSONObject GetMethodJSONparse(String str) {
		JSONObject message = null;
		try {
			JSONObject jsonObject = new JSONObject(str);
			int code = jsonObject.getInt("code");
			message = jsonObject.getJSONObject("data");
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

}
