package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.VersionBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class GetAppLatestVersionInfoBusinessBean extends BaseBusinessBean {
	
	private VersionBean data;

	public VersionBean getData() {
		return data;
	}

	public void setData(VersionBean data) {
		this.data = data;
	}

}
