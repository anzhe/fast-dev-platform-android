package fast.dev.platform.android.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import fast.dev.platform.android.bean.DictData;
import fast.dev.platform.android.bean.business.BaseWheelListDataSource;
import fast.dev.platform.android.database.AreaDBService;

public class AreaWheelListDataSource extends BaseWheelListDataSource {

	private AreaDBService dbService;
	private String[] proviceNames;
	private int[] proviceIds;
	
	/**
	 * 省-市
	 */
	private Map<String, String[]> provice2city = new HashMap<String, String[]>();
	/**
	 * 市-区
	 */
	private Map<String, String[]> city2district = new HashMap<String, String[]>();
	
	/**
	 * 是否需要显示"全部"选项
	 */
	private boolean needShowAll = false;

	public AreaWheelListDataSource(Context context, boolean needShowAll) {
		this.needShowAll = needShowAll;
		
		dbService = new AreaDBService(context);
		classifyData();
	}

	/**
	 * 归类数据
	 */
	private void classifyData() {
		List<DictData> proviceList = dbService.queryViaLevel(DictData.class, "1");
		if (needShowAll) {
			proviceNames = new String[proviceList.size() + 1];
			proviceNames[0] = "全部";
			for (int i = 0; i < proviceList.size(); i++) {
				proviceNames[i + 1] = proviceList.get(i).getDICT_NAME();
				
				List<DictData> cityList = dbService.queryChildrenViaParentId(DictData.class, proviceList.get(i).getID() + "");
				String[] cities = new String[cityList.size() + 1];
				cities[0] = "全部";
				for (int j = 0; j < cityList.size(); j++) {
					cities[j + 1] = cityList.get(j).getDICT_NAME();
					
					List<DictData> districtList = dbService.queryChildrenViaParentId(DictData.class, cityList.get(j).getID() + "");
					String[] districts = new String[districtList.size() + 1];
					districts[0] = "全部";
					for (int k = 0; k < districtList.size(); k++) {
						districts[k + 1] = cityList.get(k).getDICT_NAME();
					}
					city2district.put(cityList.get(j).getDICT_NAME(), districts);
				}
				provice2city.put(proviceList.get(i).getDICT_NAME(), cities);
			}
		} else {
			proviceNames = new String[proviceList.size()];
			proviceIds = new int[proviceList.size()];
			for (int i = 0; i < proviceList.size(); i++) {
				proviceNames[i] = proviceList.get(i).getDICT_NAME();
				proviceIds[i] = proviceList.get(i).getID();
				
				/*List<DictData> cityList = dbService.queryChildrenViaParentId(DictData.class, proviceList.get(i).getID() + "");
				String[] cities = new String[cityList.size()];
				for (int j = 0; j < cityList.size(); j++) {
					cities[j] = cityList.get(j).getDICT_NAME();
					
					List<DictData> districtList = dbService.queryChildrenViaParentId(DictData.class, cityList.get(j).getID() + "");
					String[] districts = new String[districtList.size()];
					for (int k = 0; k < districtList.size(); k++) {
						districts[k] = districtList.get(k).getDICT_NAME();
					}
					city2district.put(cityList.get(j).getDICT_NAME(), districts);
				}
				provice2city.put(proviceList.get(i).getDICT_NAME(), cities);*/
			}
		}
	}

	/**
	 * 根据父id获取子的id和名称
	 * 
	 * @param parentId
	 * 
	 * @return
	 */
	public Map<String, Object[]> getChildrenNamesAndIdsViaParentId(int parentId) {
		Map<String, Object[]> namesAndIds = new HashMap<String, Object[]>();
		List<DictData> children = dbService.queryChildrenViaParentId(DictData.class, parentId + "");
		Integer[] ids = new Integer[children.size()];
		String[] names = new String[children.size()];
		for (int i = 0; i < children.size(); i++) {
			ids[i] = children.get(i).getID();
			names[i] = children.get(i).getDICT_NAME();
		}
		if (ids.length == 0) {
			ids = new Integer[] { -1 };
		}
		if (names.length == 0) {
			names = new String[] { "" };
		}
		namesAndIds.put("ids", ids);
		namesAndIds.put("names", names);
		return namesAndIds;
	}
	
	@Override
	public JSONObject getCityData() throws JSONException {
		JSONObject obj = new JSONObject();
		return obj;
	}
	
	@Override
	public JSONObject getDistrictData() throws JSONException {
		JSONObject obj = new JSONObject();
		return obj;
	}

	@Override
	public String[] getProviceData() {
		return proviceNames;
	}

	public int[] getOneIds() {
		return proviceIds;
	}
	
	public String[] getOneNames() {
		return proviceNames;
	}

	public Map<String, String[]> getProvice2city() {
		return provice2city;
	}

	public Map<String, String[]> getCity2district() {
		return city2district;
	}
	
}
