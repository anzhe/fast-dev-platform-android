package fast.dev.platform.android.bean;

import fast.dev.platform.android.bean.business.EntrustLawyerDataBean;

public class EntrustDetailData {

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
	private String TELPHONE;
	private String EMAIL;
	private int STATUS;
	private String LAWER_ID;
	private int ASSESS;// 对选中律师的评价
	private int PROF_LEVEL;// 专业水平评价的星级
	private int PROF_QUALITY;// 职业素养评价的星级
	private int RESP_SPEED;// 响应速度评价的星级
	private String NEW_REPLY;
	private long CREATE_DATE;
	private long UPDATE_DATE;
	private String NOTES;
	private String NUM;
	private long ENTRUST_TIME;
	private String ENTRUST_TYPE;
	private String LAWER_NAME;
	private int WAY;// 交易方式：0为线下交易，1为线上交易
	private int IS_APPOINT;// 是否指定委托：0为否，1为是

	private EntrustLawyerDataBean entrustacceptlist;

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
		return TELPHONE;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public void setTELEPHONE(String tELEPHONE) {
		TELPHONE = tELEPHONE;
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

	public String getTELPHONE() {
		return TELPHONE;
	}

	public void setTELPHONE(String tELPHONE) {
		TELPHONE = tELPHONE;
	}

	public String getLAWER_ID() {
		return LAWER_ID;
	}

	public void setLAWER_ID(String lAWER_ID) {
		LAWER_ID = lAWER_ID;
	}

	public int getASSESS() {
		return ASSESS;
	}

	public void setASSESS(int aSSESS) {
		ASSESS = aSSESS;
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

	public long getENTRUST_TIME() {
		return ENTRUST_TIME;
	}

	public void setENTRUST_TIME(long eNTRUST_TIME) {
		ENTRUST_TIME = eNTRUST_TIME;
	}

	public EntrustLawyerDataBean getEntrustacceptlist() {
		return entrustacceptlist;
	}

	public void setEntrustacceptlist(EntrustLawyerDataBean entrustacceptlist) {
		this.entrustacceptlist = entrustacceptlist;
	}

	public String getCASE_TYPE_NAME() {
		return CASE_TYPE_NAME;
	}

	public void setCASE_TYPE_NAME(String cASE_TYPE_NAME) {
		CASE_TYPE_NAME = cASE_TYPE_NAME;
	}

	public int getWAY() {
		return WAY;
	}

	public void setWAY(int wAY) {
		WAY = wAY;
	}

	public String getENTRUST_TYPE() {
		return ENTRUST_TYPE;
	}

	public void setENTRUST_TYPE(String eNTRUST_TYPE) {
		ENTRUST_TYPE = eNTRUST_TYPE;
	}

	public String getLAWER_NAME() {
		return LAWER_NAME;
	}

	public void setLAWER_NAME(String lAWER_NAME) {
		LAWER_NAME = lAWER_NAME;
	}

	public int getIS_APPOINT() {
		return IS_APPOINT;
	}

	public void setIS_APPOINT(int iS_APPOINT) {
		IS_APPOINT = iS_APPOINT;
	}

}
