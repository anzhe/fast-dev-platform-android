package fast.dev.platform.android.bean;

import java.io.Serializable;

import fast.dev.platform.android.bean.business.ReplylistBusinessBean;

public class ConsultDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String USER_ID;
	private String TITLE;
	private String CONTENT;
	private String PROVINCE;
	private String CITY;
	private String TIP;
	private String CASE_TYPE;
	private String CASE_TYPE_NAME;
//	private String AUTHOR;
	private String USERNAME;
	private String TELEPHONE;
	private String EMAIL;
	private int STATUS;
	private String LAWER_ID;
	private int PROF_LEVEL;// 专业水平评价的星级
	private int PROF_QUALITY;// 职业素养评价的星级
	private int RESP_SPEED;// 响应速度评价的星级
	private int ASSESS;// 评价星级
	private String NEW_REPLY;
	private long CREATE_DATE;
	private long UPDATE_DATE;
	private String NOTES;
	private String NUM;
	private String PHOTO;
	private ReplylistBusinessBean replylist;
 
	public void setPHOTO(String s){
		this.PHOTO=s;
	}
	public String getPHOTO(){
		return this.PHOTO;
	}
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getTIP() {
		return TIP;
	}

	public void setTIP(String tIP) {
		TIP = tIP;
	}

	public String getCASE_TYPE() {
		return CASE_TYPE;
	}

	public void setCASE_TYPE(String cASE_TYPE) {
		CASE_TYPE = cASE_TYPE;
	}

//	public String getAUTHOR() {
//		return AUTHOR;
//	}
//
//	public void setAUTHOR(String aUTHOR) {
//		AUTHOR = aUTHOR;
//	}

	public String getTELEPHONE() {
		return TELEPHONE;
	}

	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public void setTELEPHONE(String tELEPHONE) {
		TELEPHONE = tELEPHONE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public int getPROF_LEVEL() {
		return PROF_LEVEL;
	}
	public void setPROF_LEVEL(int pROF_LEVEL) {
		PROF_LEVEL = pROF_LEVEL;
	}
	public int getPROF_QUALITY() {
		return PROF_QUALITY;
	}
	public void setPROF_QUALITY(int pROF_QUALITY) {
		PROF_QUALITY = pROF_QUALITY;
	}
	public int getRESP_SPEED() {
		return RESP_SPEED;
	}
	public void setRESP_SPEED(int rESP_SPEED) {
		RESP_SPEED = rESP_SPEED;
	}
	public int getASSESS() {
		return ASSESS;
	}
	public void setASSESS(int aSSESS) {
		ASSESS = aSSESS;
	}
	public String getNEW_REPLY() {
		return NEW_REPLY;
	}

	public void setNEW_REPLY(String nEW_REPLY) {
		NEW_REPLY = nEW_REPLY;
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

	public String getNUM() {
		return NUM;
	}

	public void setNUM(String nUM) {
		NUM = nUM;
	}

	public ReplylistBusinessBean getReplylist() {
		return replylist;
	}

	public void setReplylist(ReplylistBusinessBean replylist) {
		this.replylist = replylist;
	}

	public String getLAWER_ID() {
		return LAWER_ID;
	}

	public void setLAWER_ID(String lAWER_ID) {
		LAWER_ID = lAWER_ID;
	}

	public String getCASE_TYPE_NAME() {
		return CASE_TYPE_NAME;
	}

	public void setCASE_TYPE_NAME(String cASE_TYPE_NAME) {
		CASE_TYPE_NAME = cASE_TYPE_NAME;
	}

}
