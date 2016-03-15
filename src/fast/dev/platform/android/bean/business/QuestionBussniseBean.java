package fast.dev.platform.android.bean.business;

import java.util.ArrayList;
import java.util.List;

import fast.dev.platform.android.bean.OptionBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class QuestionBussniseBean extends BaseBusinessBean {

	private List<Data> data;
	
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public class Data {

		private long ID;
		private String TOPIC;
		private long CASE_TYPE;
		private int QUE_TYPE;
		private long CREATE_DATE;
		private long UPDATE_DATE;
		private String NOTES;
		private ArrayList<OptionBean> OPTIONLIST;

		public long getID() {
			return ID;
		}

		public void setID(long iD) {
			ID = iD;
		}

		public String getTOPIC() {
			return TOPIC;
		}

		public void setTOPIC(String tOPIC) {
			TOPIC = tOPIC;
		}

		public long getCASE_TYPE() {
			return CASE_TYPE;
		}

		public void setCASE_TYPE(long cASE_TYPE) {
			CASE_TYPE = cASE_TYPE;
		}

		public int getQUE_TYPE() {
			return QUE_TYPE;
		}

		public void setQUE_TYPE(int qUE_TYPE) {
			QUE_TYPE = qUE_TYPE;
		}

		public long getCREATE_DATE() {
			return CREATE_DATE;
		}

		public void setCREATE_DATE(long cREATE_DATE) {
			CREATE_DATE = cREATE_DATE;
		}

		public long getUPDATE_DATE() {
			return UPDATE_DATE;
		}

		public void setUPDATE_DATE(long uPDATE_DATE) {
			UPDATE_DATE = uPDATE_DATE;
		}

		public String getNOTES() {
			return NOTES;
		}

		public void setNOTES(String nOTES) {
			NOTES = nOTES;
		}

		public ArrayList<OptionBean> getOPTIONLIST() {
			return OPTIONLIST;
		}

		public void setOPTIONLIST(ArrayList<OptionBean> oPTIONLIST) {
			OPTIONLIST = oPTIONLIST;
		}

	}
	
}
