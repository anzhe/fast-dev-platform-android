package fast.dev.platform.android.bean.business;

import java.util.List;

import fast.dev.platform.android.bean.LawBriefBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class LawBriefBussniseBean extends BaseBusinessBean {
	
	private List<LawBriefBean> data;

	public List<LawBriefBean> getData() {
		return data;
	}

	public void setData(List<LawBriefBean> data) {
		this.data = data;
	}
	
}
