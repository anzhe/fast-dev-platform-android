package fast.dev.platform.android.bean.business;

import java.util.ArrayList;

import fast.dev.platform.android.bean.ConsultBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class GetFansDetailBusinessBean extends BaseBusinessBean {

	private String ATTEN_NUMBER;
	private String REL_NAME;
	private String PHOTO;
	private int type;
	private int pagesize;
	private int totalrecord;
	private int currentpage;
	private int totalpage;
	private ArrayList<ConsultBean> lstdata;

	public String getATTEN_NUMBER() {
		return ATTEN_NUMBER;
	}

	public void setATTEN_NUMBER(String aTTEN_NUMBER) {
		ATTEN_NUMBER = aTTEN_NUMBER;
	}

	public String getREL_NAME() {
		return REL_NAME;
	}

	public void setREL_NAME(String rEL_NAME) {
		REL_NAME = rEL_NAME;
	}

	public String getPHOTO() {
		return PHOTO;
	}

	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public ArrayList<ConsultBean> getLstdata() {
		return lstdata;
	}

	public void setLstdata(ArrayList<ConsultBean> lstdata) {
		this.lstdata = lstdata;
	}

}
