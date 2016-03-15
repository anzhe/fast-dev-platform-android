package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class PayWritBussniseBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private double BALANCE;
		private double PACKET;
		private double FROZEN_AMOUNT;

		public double getBALANCE() {
			return BALANCE;
		}

		public void setBALANCE(double bALANCE) {
			BALANCE = bALANCE;
		}

		public double getPACKET() {
			return PACKET;
		}

		public void setPACKET(double pACKET) {
			PACKET = pACKET;
		}

		public double getFROZEN_AMOUNT() {
			return FROZEN_AMOUNT;
		}

		public void setFROZEN_AMOUNT(double fROZEN_AMOUNT) {
			FROZEN_AMOUNT = fROZEN_AMOUNT;
		}

	}

}
