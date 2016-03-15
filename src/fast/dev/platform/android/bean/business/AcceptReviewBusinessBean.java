package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class AcceptReviewBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private double BALANCE;
		private String code;

		public double getBALANCE() {
			return BALANCE;
		}

		public void setBALANCE(double bALANCE) {
			BALANCE = bALANCE;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

}
