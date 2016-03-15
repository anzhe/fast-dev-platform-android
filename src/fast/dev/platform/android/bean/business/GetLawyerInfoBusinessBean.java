package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.LawyerBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class GetLawyerInfoBusinessBean extends BaseBusinessBean {

	private LawyerBean data;

	public LawyerBean getData() {
		return data;
	}

	public void setData(LawyerBean data) {
		this.data = data;
	}

}
