package fast.dev.platform.android.bean;

import java.util.ArrayList;

public class ReviewListBean {

	private ArrayList<ReviewBean> lstdata;
	private int pagesize;
	private int totalrecord;
	private int currentpage;
	private int totalpage;

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

	public ArrayList<ReviewBean> getLstdata() {
		return lstdata;
	}

	public void setLstdata(ArrayList<ReviewBean> lstdata) {
		this.lstdata = lstdata;
	}
	
}
