package fast.dev.platform.android.bean;

import java.io.Serializable;

public class WithdrawDepositDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ID;// 主键
	private long USER_ID;// 用户ID、律师ID
	private int USER_TYPE;// 用户类型：0为律师，1为客户
	private int WITHDRAW_TYPE;// 提现类型：0提现到支付宝，1为对公转出，2盈利转到余额
	private double AMOUNT;// 金额
	private double ACTUAL_AMOUNT;// 实际到账金额
	private long CREATE_DATE;// 创建时间
	private long UPDATE_DATE;// 修改时间
	private String NOTES;// 备注
	private long HANDLING_TIME;// 处理时间
	private long HANDLE_ID;// 处理客服ID
	private int STATUS;// 状态：0待处理，1已处理，2已拒绝，4已删除
	private Alipay alipay;// 当WITHDRAW_TYPE=0时，存在alipay
	private ToBusiness tobusiness;// 当WITHDRAW_TYPE=1时，存在tobusiness

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(long uSER_ID) {
		USER_ID = uSER_ID;
	}

	public int getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(int uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public int getWITHDRAW_TYPE() {
		return WITHDRAW_TYPE;
	}

	public void setWITHDRAW_TYPE(int wITHDRAW_TYPE) {
		WITHDRAW_TYPE = wITHDRAW_TYPE;
	}

	public double getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(double aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public double getACTUAL_AMOUNT() {
		return ACTUAL_AMOUNT;
	}

	public void setACTUAL_AMOUNT(double aCTUAL_AMOUNT) {
		ACTUAL_AMOUNT = aCTUAL_AMOUNT;
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

	public long getHANDLING_TIME() {
		return HANDLING_TIME;
	}

	public void setHANDLING_TIME(long hANDLING_TIME) {
		HANDLING_TIME = hANDLING_TIME;
	}

	public long getHANDLE_ID() {
		return HANDLE_ID;
	}

	public void setHANDLE_ID(long hANDLE_ID) {
		HANDLE_ID = hANDLE_ID;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public Alipay getAlipay() {
		return alipay;
	}

	public void setAlipay(Alipay alipay) {
		this.alipay = alipay;
	}

	public ToBusiness getTobusiness() {
		return tobusiness;
	}

	public void setTobusiness(ToBusiness tobusiness) {
		this.tobusiness = tobusiness;
	}

	public class Alipay implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private long ID;// 主键
		private long WITHDRAW_ID;// 提现单号主键ID
		private String ALI_NAME;// 支付宝姓名
		private String ALI_ACCOUNT;// 支付宝账号

		public long getID() {
			return ID;
		}

		public void setID(long iD) {
			ID = iD;
		}

		public long getWITHDRAW_ID() {
			return WITHDRAW_ID;
		}

		public void setWITHDRAW_ID(long wITHDRAW_ID) {
			WITHDRAW_ID = wITHDRAW_ID;
		}

		public String getALI_NAME() {
			return ALI_NAME;
		}

		public void setALI_NAME(String aLI_NAME) {
			ALI_NAME = aLI_NAME;
		}

		public String getALI_ACCOUNT() {
			return ALI_ACCOUNT;
		}

		public void setALI_ACCOUNT(String aLI_ACCOUNT) {
			ALI_ACCOUNT = aLI_ACCOUNT;
		}

	}

	public class ToBusiness implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private long ID;// 主键
		private long WITHDRAW_ID;// 提现单号主键ID
		private String COMPANY;// 公司
		private String TAXPAYER;// 纳税人号
		private String ADDRESS;// 地址
		private String TELEPHONE;// 联系电话
		private String LINKMAN;// 联系人
		private String BANK;// 开户银行
		private String BANK_ACCOUNT;// 银行账号

		public long getID() {
			return ID;
		}

		public void setID(long iD) {
			ID = iD;
		}

		public long getWITHDRAW_ID() {
			return WITHDRAW_ID;
		}

		public void setWITHDRAW_ID(long wITHDRAW_ID) {
			WITHDRAW_ID = wITHDRAW_ID;
		}

		public String getCOMPANY() {
			return COMPANY;
		}

		public void setCOMPANY(String cOMPANY) {
			COMPANY = cOMPANY;
		}

		public String getTAXPAYER() {
			return TAXPAYER;
		}

		public void setTAXPAYER(String tAXPAYER) {
			TAXPAYER = tAXPAYER;
		}

		public String getADDRESS() {
			return ADDRESS;
		}

		public void setADDRESS(String aDDRESS) {
			ADDRESS = aDDRESS;
		}

		public String getTELEPHONE() {
			return TELEPHONE;
		}

		public void setTELEPHONE(String tELEPHONE) {
			TELEPHONE = tELEPHONE;
		}

		public String getLINKMAN() {
			return LINKMAN;
		}

		public void setLINKMAN(String lINKMAN) {
			LINKMAN = lINKMAN;
		}

		public String getBANK() {
			return BANK;
		}

		public void setBANK(String bANK) {
			BANK = bANK;
		}

		public String getBANK_ACCOUNT() {
			return BANK_ACCOUNT;
		}

		public void setBANK_ACCOUNT(String bANK_ACCOUNT) {
			BANK_ACCOUNT = bANK_ACCOUNT;
		}

	}

}
