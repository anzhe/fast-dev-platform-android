package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class GetAlipayAccountBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private int type;
		private Account account;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}

		public class Account {

			private String ALI_ACCOUNT;
			private String NAME;

			public String getALI_ACCOUNT() {
				return ALI_ACCOUNT;
			}

			public void setALI_ACCOUNT(String aLI_ACCOUNT) {
				ALI_ACCOUNT = aLI_ACCOUNT;
			}

			public String getNAME() {
				return NAME;
			}

			public void setNAME(String nAME) {
				NAME = nAME;
			}

		}

	}

}
