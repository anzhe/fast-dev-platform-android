package fast.dev.platform.android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.TextUtils;

public class InputValidateUtils {

	/**
	 * 验证非法字符
	 * 
	 * @param context
	 * @param input
	 * @param tag
	 * 
	 * @return
	 */
	public static boolean validate(Context context, String input, String tag) {
		//String pattern = "[`~#$^&*()+=|{}''\\[\\]<>/#￥¥&*——+|{}【】‘’-]";
		String pattern = "[&*‘’^]";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		if (m.find()) {
			ToastUtils.showShort(context, tag + "含有非法字符：" + m.group());
			return false;
		}
		return true;
	}

	/**
	 * 判断手机格式是否正确
	 * 
	 * @param mobile_num
	 * 
	 * @return
	 */
	public static boolean isMobileNo(String mobile_num) {
		Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(mobile_num);
		return m.matches();
	}

	/**
	 * 判断email格式是否正确
	 * 
	 * @param email
	 * 
	 * @return
	 */
	public static boolean isEmail(String email) {
		String pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 验证空格
	 * 
	 * @param input
	 * 
	 * @return
	 */
	public static boolean validateSpace(String input) {
		String pattern = ".*\\s.*";
		return input.matches(pattern);
	}

	/**
	 * 验证用户名，只能为数字和字母
	 * 
	 * @param input
	 * 
	 * @return
	 */
	public static boolean validateUsername(String input) {
		String pattern = "^[A-Za-z0-9]+$";
		return input.matches(pattern);
	}

	/**
	 * 验证身份证号
	 * 
	 * @param input
	 * 
	 * @return
	 */
	public static boolean validatePerscard(String input) {
		if (TextUtils.isEmpty(input)) {
			return false;
		}
		String regularExpression = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
		return input.matches(regularExpression);
	}

	/**
	 * 身份证号码校验，严格
	 * 
	 * @param id_num
	 * 
	 * @return
	 */
	public static boolean validateIDNum(String id_num) {
		return new IdcardValidator().isValidatedAllIdcard(id_num);
	}
	
}
