package fast.dev.platform.android.bean.business;

import java.util.ArrayList;

import fast.dev.platform.android.bean.DictData;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class DictBussniseBean extends BaseBusinessBean {

	private Data data;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		
		/**
		 * 保存案件类型和地区字典
		 */
		private ArrayList<DictData> dictList;

		public ArrayList<DictData> getDictList() {
			return dictList;
		}

		public void setDictList(ArrayList<DictData> dictList) {
			this.dictList = dictList;
		}
		
	}
	
}
