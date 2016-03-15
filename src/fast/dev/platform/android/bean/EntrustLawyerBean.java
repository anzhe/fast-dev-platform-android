package fast.dev.platform.android.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

public class EntrustLawyerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ID;
	private String LAWER_ID;
	private long ENTRUST_ID;
	private long CREATE_DATE;
	private long UPDATE_DATE;
	private double LONGITUDE;
	private double LATITUDE;
	private int STATUS;
	private String DISTANCE;
	private String REL_NAME;
	private String PHOTO;
	private String PROVINCE;
	private String CITY;
	private String COUNTY;
	private String GOOD_FIELD;
	private String LAY_FIRM;
	private String PROFILE;
	private String LICENCE_NUM;
	private String WORK_TIME;
	private String ADDRESS;
	private int LAWER_LEVEL;
	private String MY_PHONE;
	private String NUM;
	private String EMAIL;
	private String LAWYER_ASSESS;// 评价数|评价星级|专业水平评价|职业素养评价|响应速度评价
	private double BID_PRICE;
	private int IS_PAID;// 是否已经打款给律师：0不需要打款，1未打款，2已打款
	private double ACTUAL_PAID;// 用户已付款

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getENTRUST_ID() {
		return ENTRUST_ID;
	}

	public void setENTRUST_ID(long eNTRUST_ID) {
		ENTRUST_ID = eNTRUST_ID;
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

	public double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(double lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public String getDISTANCE() {
		return DISTANCE;
	}

	public void setDISTANCE(String dISTANCE) {
		DISTANCE = dISTANCE;
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

	public String getCOUNTY() {
		return COUNTY;
	}

	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}

	public String getGOOD_FIELD() {
		if (GOOD_FIELD != null && !TextUtils.isEmpty(GOOD_FIELD)) {
			String[] fields = GOOD_FIELD.split(",");
			if (fields.length >= 3) {
				List<String> tempList = new ArrayList<String>();
				for (String field : fields) {
					if (!tempList.contains(field)) {
						tempList.add(field);
						if (tempList.size() == 3) {
							break;
						}
					}
				}
				StringBuffer result = new StringBuffer();
				for (int i = 0; i < tempList.size(); i++) {
					result.append(tempList.get(i));
					if (i != tempList.size() - 1) {
						result.append(",");
					}
				}
				GOOD_FIELD = result.toString();
			}
		}
		return GOOD_FIELD;
	}

	public void setGOOD_FIELD(String gOOD_FIELD) {
		GOOD_FIELD = gOOD_FIELD;
	}

	public String getLAY_FIRM() {
		return LAY_FIRM;
	}

	public void setLAY_FIRM(String lAY_FIRM) {
		LAY_FIRM = lAY_FIRM;
	}

	public String getPROFILE() {
		return PROFILE;
	}

	public void setPROFILE(String pROFILE) {
		PROFILE = pROFILE;
	}

	public String getLICENCE_NUM() {
		return LICENCE_NUM;
	}

	public void setLICENCE_NUM(String lICENCE_NUM) {
		LICENCE_NUM = lICENCE_NUM;
	}

	public String getWORK_TIME() {
		return WORK_TIME;
	}

	public void setWORK_TIME(String wORK_TIME) {
		WORK_TIME = wORK_TIME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public int getLAWER_LEVEL() {
		return LAWER_LEVEL;
	}

	public void setLAWER_LEVEL(int lAWER_LEVEL) {
		LAWER_LEVEL = lAWER_LEVEL;
	}

	public String getMY_PHONE() {
		return MY_PHONE;
	}

	public void setMY_PHONE(String mY_PHONE) {
		MY_PHONE = mY_PHONE;
	}

	public String getNUM() {
		return NUM;
	}

	public void setNUM(String nUM) {
		NUM = nUM;
	}

	public String getLAWER_ID() {
		return LAWER_ID;
	}

	public void setLAWER_ID(String lAWER_ID) {
		LAWER_ID = lAWER_ID;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getLAWYER_ASSESS() {
		return LAWYER_ASSESS;
	}

	public void setLAWYER_ASSESS(String lAWYER_ASSESS) {
		LAWYER_ASSESS = lAWYER_ASSESS;
	}

	public double getBID_PRICE() {
		return BID_PRICE;
	}

	public void setBID_PRICE(double bID_PRICE) {
		BID_PRICE = bID_PRICE;
	}

	public int getIS_PAID() {
		return IS_PAID;
	}

	public void setIS_PAID(int iS_PAID) {
		IS_PAID = iS_PAID;
	}

}
