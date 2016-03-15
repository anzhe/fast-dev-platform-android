package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.LoginBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class ThirdPartyLoginBussniseBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private int type;
		private LoginBean account;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public LoginBean getAccount() {
			return account;
		}

		public void setAccount(LoginBean account) {
			this.account = account;
		}

	}

}
