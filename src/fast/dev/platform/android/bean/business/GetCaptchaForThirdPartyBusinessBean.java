package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class GetCaptchaForThirdPartyBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private int ifexist;// 0代表该手机号未注册，1代表用户，2代表律师
		private Account account;

		public int getIfexist() {
			return ifexist;
		}

		public void setIfexist(int ifexist) {
			this.ifexist = ifexist;
		}

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}

		public class Account {

			private long ID;
			private String CALL;
			private String PHOTO;
			private String REL_NAME;
			private String MY_PHONE;
			private String PASSWORD;
			private String THIRD_PARTY;
			private String EMAIL;
			private String PROVINCE;
			private String CITY;
			private String COUNTY;
			private String ADDRESS;
			private int STATUS;
			private int USER_LEVEL;
			private String AGENT_ID;
			private String AGENT_NAME;
			private double BALANCE;
			private double PACKET;
			private long CREATE_DATE;
			private long UPDATE_DATE;
			private String NOTES;
			private String USERNAME;
			private String INVITED_CODE;
			private String PAY_PSD;
			private double FROZEN_AMOUNT;
			private String MY_INVITED_CODE;
			private String ATTENTION;

			public long getID() {
				return ID;
			}

			public void setID(long iD) {
				ID = iD;
			}

			public String getCALL() {
				return CALL;
			}

			public void setCALL(String cALL) {
				CALL = cALL;
			}

			public String getPHOTO() {
				return PHOTO;
			}

			public void setPHOTO(String pHOTO) {
				PHOTO = pHOTO;
			}

			public String getREL_NAME() {
				return REL_NAME;
			}

			public void setREL_NAME(String rEL_NAME) {
				REL_NAME = rEL_NAME;
			}

			public String getMY_PHONE() {
				return MY_PHONE;
			}

			public void setMY_PHONE(String mY_PHONE) {
				MY_PHONE = mY_PHONE;
			}

			public String getPASSWORD() {
				return PASSWORD;
			}

			public void setPASSWORD(String pASSWORD) {
				PASSWORD = pASSWORD;
			}

			public String getTHIRD_PARTY() {
				return THIRD_PARTY;
			}

			public void setTHIRD_PARTY(String tHIRD_PARTY) {
				THIRD_PARTY = tHIRD_PARTY;
			}

			public String getEMAIL() {
				return EMAIL;
			}

			public void setEMAIL(String eMAIL) {
				EMAIL = eMAIL;
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

			public String getADDRESS() {
				return ADDRESS;
			}

			public void setADDRESS(String aDDRESS) {
				ADDRESS = aDDRESS;
			}

			public int getSTATUS() {
				return STATUS;
			}

			public void setSTATUS(int sTATUS) {
				STATUS = sTATUS;
			}

			public int getUSER_LEVEL() {
				return USER_LEVEL;
			}

			public void setUSER_LEVEL(int uSER_LEVEL) {
				USER_LEVEL = uSER_LEVEL;
			}

			public String getAGENT_ID() {
				return AGENT_ID;
			}

			public void setAGENT_ID(String aGENT_ID) {
				AGENT_ID = aGENT_ID;
			}

			public String getAGENT_NAME() {
				return AGENT_NAME;
			}

			public void setAGENT_NAME(String aGENT_NAME) {
				AGENT_NAME = aGENT_NAME;
			}

			public double getBALANCE() {
				return BALANCE;
			}

			public void setBALANCE(double bALANCE) {
				BALANCE = bALANCE;
			}

			public double getPACKET() {
				return PACKET;
			}

			public void setPACKET(double pACKET) {
				PACKET = pACKET;
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

			public String getUSERNAME() {
				return USERNAME;
			}

			public void setUSERNAME(String uSERNAME) {
				USERNAME = uSERNAME;
			}

			public String getINVITED_CODE() {
				return INVITED_CODE;
			}

			public void setINVITED_CODE(String iNVITED_CODE) {
				INVITED_CODE = iNVITED_CODE;
			}

			public String getPAY_PSD() {
				return PAY_PSD;
			}

			public void setPAY_PSD(String pAY_PSD) {
				PAY_PSD = pAY_PSD;
			}

			public double getFROZEN_AMOUNT() {
				return FROZEN_AMOUNT;
			}

			public void setFROZEN_AMOUNT(double fROZEN_AMOUNT) {
				FROZEN_AMOUNT = fROZEN_AMOUNT;
			}

			public String getMY_INVITED_CODE() {
				return MY_INVITED_CODE;
			}

			public void setMY_INVITED_CODE(String mY_INVITED_CODE) {
				MY_INVITED_CODE = mY_INVITED_CODE;
			}

			public String getATTENTION() {
				return ATTENTION;
			}

			public void setATTENTION(String aTTENTION) {
				ATTENTION = aTTENTION;
			}

		}

	}

}
