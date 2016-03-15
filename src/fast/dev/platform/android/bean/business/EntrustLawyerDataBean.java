package fast.dev.platform.android.bean.business;

import java.util.ArrayList;

import fast.dev.platform.android.bean.EntrustLawyerBean;

public class EntrustLawyerDataBean {

	private ArrayList<EntrustLawyerBean> lstdata;
	private int pagesize;
	private int totalrecord;
	private int currentpage;
	private int totalpage;

	public ArrayList<EntrustLawyerBean> getLstdata() {
		return lstdata;
	}

	public void setLstdata(ArrayList<EntrustLawyerBean> lstdata) {
		this.lstdata = lstdata;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

}
