package fast.dev.platform.android.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.text.TextUtils;

public class MathUtils {

	/**
	 * 格式化金额为两位小数且不四舍五入，返回double类型
	 * 
	 * @param money
	 * 
	 * @return
	 */
	public static double formatMoneyTo2Dot(double money) {
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(2);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
		return Double.parseDouble(formater.format(money));
	}

	/**
	 * 格式化金额为两位小数且不四舍五入，返回字符串类型
	 * 
	 * @param money
	 * 
	 * @return
	 */
	public static String formatMoneyTo2DotInString(double money) {
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(2);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
		String result = formater.format(money);
		if (!result.contains(".")) {
			result = result + ".00";
		}
		if (result.indexOf(".") == result.length() - 2) {
			result = result + "0";
		}
		return result;
	}
	
	/**
	 * 格式化金额为两位小数且不四舍五入，返回字符串类型
	 * 
	 * @param money
	 * 
	 * @return
	 */
	public static String formatMoneyTo2DotInString(String money) {
		if (TextUtils.isEmpty(money)) {
			return "0.00";
		}
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(2);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
		String result = formater.format(Double.parseDouble(money));
		if (!result.contains(".")) {
			result = result + ".00";
		}
		if (result.indexOf(".") == result.length() - 2) {
			result = result + "0";
		}
		return result;
	}
	
	/**
	 * 向上取整
	 * 
	 * @param num
	 * 
	 * @return
	 */
	public static int ceil(String num) {
		if (TextUtils.isEmpty(num)) {
			return -1;
		}
		return (int) Math.ceil(Double.parseDouble(num));
	}
	
	/**
	 * 向上取整
	 * 
	 * @param num
	 * 
	 * @return
	 */
	public static int ceil(double num) {
		return (int) Math.ceil(num);
	}
	
}
