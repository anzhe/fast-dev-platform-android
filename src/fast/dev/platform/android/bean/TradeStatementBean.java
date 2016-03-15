package fast.dev.platform.android.bean;

import java.io.Serializable;

public class TradeStatementBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ID;
	private double AMOUNT;// 交易金额（+/-）数值
	private int ENTRY_EXIT_WAY;// 出入帐方式：0进帐，1出帐
	private int TRANS_WAY;// 交易渠道：0支付宝，1微支付，2其它，3对公提现，4平台帐户
	private String OTHER_ACCOUNT;// 对方账号
	private int OTHER_ACCOUT_TYPE;// 对方账号类型0平台用户，1平台
	private String ORDER_NUM;// 订单号
	private int ORDER_TYPE;// 订单类型：0充值，1普通文书，2定制文书，3案件委托，4咨询管理，5提现
	private String PURPOSE;// 用途
	private long TRANS_TIME;// 交易时间
	private String REMARKS;// 摘要
	private double BALANCE;// 这次交易账户类型余额
	private String OTHER_ACCOUNT_NAME;// 对方账户姓名
	private int STATUS;// 提现状态：0待受理，1已受理，2已拒绝，4已删除（-1为无）
	private int WITHDRAW_TYPE;// 提现类型：0提现到支付宝，1为对公转出，2盈利转到余额
	private String WITHDRAW_ACCOUNT;// 支付宝账户/对公提现公司

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public double getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(double aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public int getENTRY_EXIT_WAY() {
		return ENTRY_EXIT_WAY;
	}

	public void setENTRY_EXIT_WAY(int eNTRY_EXIT_WAY) {
		ENTRY_EXIT_WAY = eNTRY_EXIT_WAY;
	}

	public int getTRANS_WAY() {
		return TRANS_WAY;
	}

	public void setTRANS_WAY(int tRANS_WAY) {
		TRANS_WAY = tRANS_WAY;
	}

	public String getOTHER_ACCOUNT() {
		return OTHER_ACCOUNT;
	}

	public void setOTHER_ACCOUNT(String oTHER_ACCOUNT) {
		OTHER_ACCOUNT = oTHER_ACCOUNT;
	}

	public int getOTHER_ACCOUT_TYPE() {
		return OTHER_ACCOUT_TYPE;
	}

	public void setOTHER_ACCOUT_TYPE(int oTHER_ACCOUT_TYPE) {
		OTHER_ACCOUT_TYPE = oTHER_ACCOUT_TYPE;
	}

	public String getORDER_NUM() {
		return ORDER_NUM;
	}

	public void setORDER_NUM(String oRDER_NUM) {
		ORDER_NUM = oRDER_NUM;
	}

	public int getORDER_TYPE() {
		return ORDER_TYPE;
	}

	public void setORDER_TYPE(int oRDER_TYPE) {
		ORDER_TYPE = oRDER_TYPE;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}

	public long getTRANS_TIME() {
		return TRANS_TIME;
	}

	public void setTRANS_TIME(long tRANS_TIME) {
		TRANS_TIME = tRANS_TIME;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public double getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(double bALANCE) {
		BALANCE = bALANCE;
	}

	public String getOTHER_ACCOUNT_NAME() {
		return OTHER_ACCOUNT_NAME;
	}

	public void setOTHER_ACCOUNT_NAME(String oTHER_ACCOUNT_NAME) {
		OTHER_ACCOUNT_NAME = oTHER_ACCOUNT_NAME;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public int getWITHDRAW_TYPE() {
		return WITHDRAW_TYPE;
	}

	public void setWITHDRAW_TYPE(int wITHDRAW_TYPE) {
		WITHDRAW_TYPE = wITHDRAW_TYPE;
	}

	public String getWITHDRAW_ACCOUNT() {
		return WITHDRAW_ACCOUNT;
	}

	public void setWITHDRAW_ACCOUNT(String wITHDRAW_ACCOUNT) {
		WITHDRAW_ACCOUNT = wITHDRAW_ACCOUNT;
	}

}
