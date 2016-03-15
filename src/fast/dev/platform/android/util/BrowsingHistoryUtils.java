package fast.dev.platform.android.util;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import fast.dev.platform.android.model.BrowsingHistory;

public class BrowsingHistoryUtils {

	/**
	 * 记录浏览历史
	 * 
	 * @param userId
	 * @param itemId
	 * @param browsingType 浏览类型，0为个人消息列表，1为咨询列表，2为案件委托列表，3为文书产品列表，4位法律援助列表，5为文书定制列表，6为律师列表
	 */
	public static void record(String userId, String itemId, int browsingType) {
		BrowsingHistory browsingHistory = new Select().from(BrowsingHistory.class)
				.where("userId=? AND itemId=? AND browsingType=?", userId, itemId, browsingType).executeSingle();
		if (browsingHistory == null) {
			browsingHistory = new BrowsingHistory();
			browsingHistory.userId = userId;
			browsingHistory.itemId = itemId;
			browsingHistory.browsingType = browsingType;
			browsingHistory.save();
		}
	}
	
	/**
	 * 查询用户记录集合
	 * 
	 * @param userId
	 * @param browsingType 浏览类型，0为个人消息列表，1为咨询列表，2为案件委托列表，3为文书产品列表，4位法律援助列表，5为文书定制列表，6为律师列表
	 * 
	 * @return
	 */
	public static List<String> getBrowsingHistories(String userId, int browsingType) {
		List<String> browsingItemIds = new ArrayList<String>();
		List<BrowsingHistory> browsingHistories = new Select().from(BrowsingHistory.class)
				.where("userId=? AND browsingType=?", userId, browsingType).execute();
		for (BrowsingHistory browsingHistory : browsingHistories) {
			browsingItemIds.add(browsingHistory.itemId);
		}
		return browsingItemIds;
	}
	
	/**
	 * 删除浏览历史
	 * 
	 * @param userId
	 * @param itemId
	 * @param browsingType 浏览类型，0为个人消息列表，1为咨询列表，2为案件委托列表
	 */
	public static void deleteHistory(String userId, String itemId, int browsingType) {
		new Delete().from(BrowsingHistory.class)
				.where("userId=? AND itemId=? AND browsingType=?", userId, itemId, browsingType).execute();
	}
	
	/**
	 * 是否浏览过
	 * 
	 * @param userId
	 * @param itemId
	 * @param browsingType 浏览类型，0为个人消息列表，1为咨询列表，2为案件委托列表，3为文书产品列表，4位法律援助列表，5为文书定制列表，6为律师列表
	 * 
	 * @return
	 */
	public static boolean inHistory(String userId, String itemId, int browsingType) {
		BrowsingHistory browsingHistory = new Select().from(BrowsingHistory.class)
				.where("userId=? AND itemId=? AND browsingType=?", userId, itemId, browsingType).executeSingle();
		if (browsingHistory == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 清空所有的浏览历史记录
	 */
	public static void clearHistory() {
		new Delete().from(BrowsingHistory.class).execute();
	}
	
}
