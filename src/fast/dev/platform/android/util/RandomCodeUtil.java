package fast.dev.platform.android.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 生成4位字母和数字组合的随机验证码工具类
 * @author Administrator
 *
 */
public class RandomCodeUtil {

	/**
	 * 
	 * @Title: generateWord
	 * @author xia
	 * @Description: 生成4位随机邀请码
	 * @param @return    
	 * @return String 
	 * @date 2015年10月20日 下午4:36:11
	 */
	public static String generateWord() {  
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",  
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        
        return result;
    }
	
	/**
	 * 
	 * @Title: getOrderNum
	 * @author xia
	 * @Description: 生成订单号
	 * @param @return    
	 * @return String 
	 * @date 2015年10月20日 下午4:41:56
	 */
	public static String getOrderNum(){
		// 生成订单号
		int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
		int r2 = (int) (Math.random() * (10));
		long now = System.currentTimeMillis();// 一个13位的时间戳
		String ordernum = String.valueOf(r1) + String.valueOf(r2)+ String.valueOf(now);// 订单ID
		return ordernum;
	}
}
