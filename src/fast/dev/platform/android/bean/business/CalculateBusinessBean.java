package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.LawMoneyBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class CalculateBusinessBean extends BaseBusinessBean {

	private LawMoneyBean data;

	public LawMoneyBean getData() {
		return data;
	}

	public void setData(LawMoneyBean data) {
		this.data = data;
	}

}
