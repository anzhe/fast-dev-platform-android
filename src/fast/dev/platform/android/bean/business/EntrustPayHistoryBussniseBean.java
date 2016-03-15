package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class EntrustPayHistoryBussniseBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private double PAID;// 用户已付款金额

		public double getPAID() {
			return PAID;
		}

		public void setPAID(double pAID) {
			PAID = pAID;
		}

	}

}
