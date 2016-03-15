package fast.dev.platform.android.bean;

public class ThemeBean {

	/**
	 * 主键
	 */
	private long ID;

	/**
	 * 主题名称
	 */
	private String NAME;

	/**
	 * 主题简述
	 */
	private String THEME_DESC;

	/**
	 * 主题图标
	 */
	private String ICON;

	/**
	 * 案件类型
	 */
	private int CASE_TYPE;

	/**
	 * 案件类型名称
	 */
	private String CASE_TYPE_NAME;

	/**
	 * 主题类型: 1咨询，2委托，3文书
	 */
	private int THEME_TYPE;

	/**
	 * 是否展示首页
	 */
	private int IS_MAIN;

	/**
	 * 状态: 0禁用，1启用，2删除
	 */
	private int STATUS;

	/**
	 * 创建日期
	 */
	private long CREATE_DATE;

	/**
	 * 修改日期
	 */
	private long UPDATE_DATE;

	/**
	 * 备注
	 */
	private String NOTES;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getTHEME_DESC() {
		return THEME_DESC;
	}

	public void setTHEME_DESC(String tHEME_DESC) {
		THEME_DESC = tHEME_DESC;
	}

	public String getICON() {
		return ICON;
	}

	public void setICON(String iCON) {
		ICON = iCON;
	}

	public int getCASE_TYPE() {
		return CASE_TYPE;
	}

	public void setCASE_TYPE(int cASE_TYPE) {
		CASE_TYPE = cASE_TYPE;
	}

	public String getCASE_TYPE_NAME() {
		return CASE_TYPE_NAME;
	}

	public void setCASE_TYPE_NAME(String cASE_TYPE_NAME) {
		CASE_TYPE_NAME = cASE_TYPE_NAME;
	}

	public int getTHEME_TYPE() {
		return THEME_TYPE;
	}

	public void setTHEME_TYPE(int tHEME_TYPE) {
		THEME_TYPE = tHEME_TYPE;
	}

	public int getIS_MAIN() {
		return IS_MAIN;
	}

	public void setIS_MAIN(int iS_MAIN) {
		IS_MAIN = iS_MAIN;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
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

}
