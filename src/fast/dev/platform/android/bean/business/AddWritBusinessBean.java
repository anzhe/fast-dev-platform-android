package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class AddWritBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private String ORDERNUM;

		public String getORDERNUM() {
			return ORDERNUM;
		}

		public void setORDERNUM(String oRDERNUM) {
			ORDERNUM = oRDERNUM;
		}

	}

}
