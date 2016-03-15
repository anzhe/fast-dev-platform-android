package fast.dev.platform.android.datasource;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import fast.dev.platform.android.bean.business.BaseWheelListDataSource;

public class NationDataSource extends BaseWheelListDataSource {

	private String[] provice = new String[] { "汉族", "畲族", "壮族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "满族", "朝鲜族", "彝族",
			"布依族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "僳僳族", "佤族", "高山族", "拉祜族", "水族", "东乡族", "纳西族",
			"景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族",
			"乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族" };

	@Override
	public String[] getProviceData() {
		return provice;
	}

	@Override
	public JSONObject getCityData() throws JSONException {
		return null;
	}

	@Override
	public JSONObject getDistrictData() throws JSONException {
		return null;
	}

	@Override
	public int[] getOneIds() {
		return null;
	}

	@Override
	public String[] getOneNames() {
		return null;
	}

	@Override
	public Map<String, Object[]> getChildrenNamesAndIdsViaParentId(int parentId) {
		return null;
	}

}
