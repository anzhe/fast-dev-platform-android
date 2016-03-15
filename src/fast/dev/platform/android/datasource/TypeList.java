package fast.dev.platform.android.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import fast.dev.platform.android.bean.DictData;
import fast.dev.platform.android.bean.business.BaseWheelListDataSource;
import fast.dev.platform.android.database.CaseTypeDBService;

public class TypeList extends BaseWheelListDataSource {

	private CaseTypeDBService dbService;
	private String[] provices;
	private List<DictData> case_types = new ArrayList<DictData>();
	
	private boolean showAll = true;// 是否显示全部选项

	public TypeList(Context context) {
		this(context, true);
	}
	
	public TypeList(Context context, boolean showAll) {
		this.showAll = showAll;
		dbService = new CaseTypeDBService(context);
		case_types = dbService.queryViaLevel(DictData.class, "1");
		setProviceData();
	}

	public void setProviceData() {
		if (showAll) {
			provices = new String[case_types.size() + 1];
			provices[0] = "全部";
			for (int i = 0; i < case_types.size(); i++) {
				provices[i + 1] = case_types.get(i).getDICT_NAME();
			}
		} else {
			provices = new String[case_types.size()];
			for (int i = 0; i < case_types.size(); i++) {
				provices[i] = case_types.get(i).getDICT_NAME();
			}
		}
	}

	@Override
	public JSONObject getCityData() throws JSONException {
		JSONObject jo = new JSONObject();
		if (showAll) {
			jo.put("全部", "全部+");
		}
		for (int i = 0; i < case_types.size(); i++) {
			StringBuffer case_buff = new StringBuffer();
			List<DictData> case_son = dbService.query(DictData.class, "" + getCaseId(case_types.get(i).getDICT_NAME()));
			for (int j = 0; j < case_son.size(); j++) {
				case_buff.append(case_son.get(j).getDICT_NAME() + "+");
			}
			jo.put(case_types.get(i).getDICT_NAME(), case_buff.toString());
		}
		return jo;
	}

	@Override
	public String[] getProviceData() {
		return provices;
	}

	private long getCaseId(String caseName) {
		for (int i = 0; i < case_types.size(); i++) {
			if (caseName.equals(case_types.get(i).getDICT_NAME())) {
				return case_types.get(i).getID();
			}
		}
		return 0;
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
