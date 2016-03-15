package fast.dev.platform.android.bean.business;

import java.util.ArrayList;

import fast.dev.platform.android.bean.DictBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class SystemDictBussniseBean extends BaseBusinessBean {

	private Data data;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		
		private ArrayList<DictBean> dictList;

		public ArrayList<DictBean> getDictList() {
			return dictList;
		}

		public void setDictList(ArrayList<DictBean> dictList) {
			this.dictList = dictList;
		}

	}
	
}
