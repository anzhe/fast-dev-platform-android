package fast.dev.platform.android.bean.business;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseWheelListDataSource {

	public abstract String[] getProviceData();

	public abstract JSONObject getCityData() throws JSONException;

	public abstract JSONObject getDistrictData() throws JSONException;

	public abstract int[] getOneIds();

	public abstract String[] getOneNames();

	/**
	 * 根据父id获取子的id和名称
	 * 
	 * @param parentId
	 * 
	 * @return
	 */
	public abstract Map<String, Object[]> getChildrenNamesAndIdsViaParentId(int parentId);

}
