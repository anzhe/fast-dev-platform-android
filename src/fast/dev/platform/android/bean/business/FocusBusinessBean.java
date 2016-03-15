package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class FocusBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private String ATTEN_NUMBER;

		public String getATTEN_NUMBER() {
			return ATTEN_NUMBER;
		}

		public void setATTEN_NUMBER(String aTTEN_NUMBER) {
			ATTEN_NUMBER = aTTEN_NUMBER;
		}

	}

}
