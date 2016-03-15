package fast.dev.platform.android.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ID;
	private long MESSAGE_ID;
	private int READ_STATE;
	private String CONTENT;
	private String SENDER;
	private int MSG_TYPE;
	private long CREATE_DATE;
	private int TYPE;// 消息类型：0,个人消息;1,咨询详情;2,委托详情;3,文书详情;4,援助详情;5,加盟信息更新;6,文书定制
	private long TYPE_ID;// 消息类型相关的id

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getMESSAGE_ID() {
		return MESSAGE_ID;
	}

	public void setMESSAGE_ID(long mESSAGE_ID) {
		MESSAGE_ID = mESSAGE_ID;
	}

	public int getREAD_STATE() {
		return READ_STATE;
	}

	public void setREAD_STATE(int rEAD_STATE) {
		READ_STATE = rEAD_STATE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getSENDER() {
		return SENDER;
	}

	public void setSENDER(String sENDER) {
		SENDER = sENDER;
	}

	public int getMSG_TYPE() {
		return MSG_TYPE;
	}

	public void setMSG_TYPE(int mSG_TYPE) {
		MSG_TYPE = mSG_TYPE;
	}

	public long getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(long cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}

	public long getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(long tYPE_ID) {
		TYPE_ID = tYPE_ID;
	}

}
