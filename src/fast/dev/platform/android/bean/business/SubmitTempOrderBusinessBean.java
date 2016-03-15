package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class SubmitTempOrderBusinessBean extends BaseBusinessBean {
	
	private Data data;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		
		private String ORDER_NUM;

		public String getORDER_NUM() {
			return ORDER_NUM;
		}

		public void setORDER_NUM(String oRDER_NUM) {
			ORDER_NUM = oRDER_NUM;
		}
		
	}
	
}
