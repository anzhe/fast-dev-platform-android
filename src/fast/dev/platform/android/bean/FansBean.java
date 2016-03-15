package fast.dev.platform.android.bean;

public class FansBean {

	/**
	 * 主键
	 */
	private long ID;
	/**
	 * 用户ID
	 */
	private long USER_ID = -1;
	/**
	 * 律师ID
	 */
	private long LAWER_ID = -1;
	/**
	 * 关注用户ID
	 */
	private long ATTEN_USER_ID = -1;
	/**
	 * 关注律师ID
	 */
	private long ATTEN_LAWER_ID = -1;
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
	/**
	 * 用户姓名
	 */
	private String USERNAME;
	/**
	 * 用户头像
	 */
	private String USERPHOTO;
	/**
	 * 律师姓名
	 */
	private String LAWYERNAME;
	/**
	 * 律师头像
	 */
	private String LAWYERPHOTO;
	/**
	 * 关注用户姓名
	 */
	private String ATTENUSERNAME;
	/**
	 * 关注用户头像
	 */
	private String ATTENUSERPHOTO;
	/**
	 * 关注律师姓名
	 */
	private String ATTENLAWYERNAME;
	/**
	 * 关注律师头像
	 */
	private String ATTENLAWYERPHOTO;
	/**
	 * 文字说明
	 */
	private String CONSULTTITLE;

	private int status = 1;// 关注状态，0为不关注，1为关注中，默认为1
	
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

	public long getLAWER_ID() {
		return LAWER_ID;
	}

	public void setLAWER_ID(long lAWER_ID) {
		LAWER_ID = lAWER_ID;
	}

	public long getATTEN_USER_ID() {
		return ATTEN_USER_ID;
	}

	public void setATTEN_USER_ID(long aTTEN_USER_ID) {
		ATTEN_USER_ID = aTTEN_USER_ID;
	}

	public long getATTEN_LAWER_ID() {
		return ATTEN_LAWER_ID;
	}

	public void setATTEN_LAWER_ID(long aTTEN_LAWER_ID) {
		ATTEN_LAWER_ID = aTTEN_LAWER_ID;
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

	public String getUSERPHOTO() {
		return USERPHOTO;
	}

	public void setUSERPHOTO(String uSERPHOTO) {
		USERPHOTO = uSERPHOTO;
	}

	public String getLAWYERNAME() {
		return LAWYERNAME;
	}

	public void setLAWYERNAME(String lAWYERNAME) {
		LAWYERNAME = lAWYERNAME;
	}

	public String getLAWYERPHOTO() {
		return LAWYERPHOTO;
	}

	public void setLAWYERPHOTO(String lAWYERPHOTO) {
		LAWYERPHOTO = lAWYERPHOTO;
	}

	public String getATTENUSERNAME() {
		return ATTENUSERNAME;
	}

	public void setATTENUSERNAME(String aTTENUSERNAME) {
		ATTENUSERNAME = aTTENUSERNAME;
	}

	public String getATTENUSERPHOTO() {
		return ATTENUSERPHOTO;
	}

	public void setATTENUSERPHOTO(String aTTENUSERPHOTO) {
		ATTENUSERPHOTO = aTTENUSERPHOTO;
	}

	public String getATTENLAWYERNAME() {
		return ATTENLAWYERNAME;
	}

	public void setATTENLAWYERNAME(String aTTENLAWYERNAME) {
		ATTENLAWYERNAME = aTTENLAWYERNAME;
	}

	public String getATTENLAWYERPHOTO() {
		return ATTENLAWYERPHOTO;
	}

	public void setATTENLAWYERPHOTO(String aTTENLAWYERPHOTO) {
		ATTENLAWYERPHOTO = aTTENLAWYERPHOTO;
	}

	public String getCONSULTTITLE() {
		return CONSULTTITLE;
	}

	public void setCONSULTTITLE(String cONSULTTITLE) {
		CONSULTTITLE = cONSULTTITLE;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
