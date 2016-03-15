package fast.dev.platform.android.bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String LEV;
	private String ID;
	private String CASE_ID;
	private String USER_ID;
	private String LAWER_ID;
	private String LAWER_NAME;
	private String BE_LAWER_ID;
	private String BE_USER_ID;
	private String REPLY_COMENT;
	private String IS_READ;
	private long CREATE_DATE;
	private long UPDATE_DATE;
	private String NOTES;
	private String NUM;
	private String COUNT;
	private String PHOTO;
	private String LAWER_PHOTO;

	public String getLEV() {
		return LEV;
	}

	public void setLEV(String lEV) {
		LEV = lEV;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCASE_ID() {
		return CASE_ID;
	}

	public void setCASE_ID(String cASE_ID) {
		CASE_ID = cASE_ID;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getLAWER_ID() {
		return LAWER_ID;
	}

	public void setLAWER_ID(String lAWER_ID) {
		LAWER_ID = lAWER_ID;
	}

	public String getLAWER_NAME() {
		return LAWER_NAME;
	}

	public void setLAWER_NAME(String lAWER_NAME) {
		LAWER_NAME = lAWER_NAME;
	}

	public String getBE_LAWER_ID() {
		return BE_LAWER_ID;
	}

	public void setBE_LAWER_ID(String bE_LAWER_ID) {
		BE_LAWER_ID = bE_LAWER_ID;
	}

	public String getBE_USER_ID() {
		return BE_USER_ID;
	}

	public void setBE_USER_ID(String bE_USER_ID) {
		BE_USER_ID = bE_USER_ID;
	}

	public String getREPLY_COMENT() {
		return REPLY_COMENT;
	}

	public void setREPLY_COMENT(String rEPLY_COMENT) {
		REPLY_COMENT = rEPLY_COMENT;
	}

	public String getIS_READ() {
		return IS_READ;
	}

	public void setIS_READ(String iS_READ) {
		IS_READ = iS_READ;
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

	public String getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}

	public String getPHOTO() {
		return PHOTO;
	}

	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}

	public String getLAWER_PHOTO() {
		return LAWER_PHOTO;
	}

	public void setLAWER_PHOTO(String lAWER_PHOTO) {
		LAWER_PHOTO = lAWER_PHOTO;
	}

}
