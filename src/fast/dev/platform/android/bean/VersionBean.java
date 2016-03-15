package fast.dev.platform.android.bean;

import java.util.List;

public class VersionBean {

	private List<OverImage> overImageList;
	private AppMap appMap;
	private DicMap dictMap;

	public List<OverImage> getOverImageList() {
		return overImageList;
	}

	public void setOverImageList(List<OverImage> overImageList) {
		this.overImageList = overImageList;
	}

	public AppMap getAppMap() {
		return appMap;
	}

	public void setAppMap(AppMap appMap) {
		this.appMap = appMap;
	}

	public DicMap getDictMap() {
		return dictMap;
	}

	public void setDictMap(DicMap dictMap) {
		this.dictMap = dictMap;
	}

	public class AppMap {

		/**
		 * 是否强制升级（1是，0否）
		 */
		private int IF_FORCE;

		/**
		 * 发布时间
		 */
		private long RELEASE_DATE;

		/**
		 * 版本名称
		 */
		private String VERSION;

		/**
		 * 版本号
		 */
		private int VERSION_CODE = 1;

		/**
		 * 文件地址
		 */
		private String FILE_URL;

		/**
		 * 升级说明
		 */
		private String RELEASE_NOTE;

		public int getIF_FORCE() {
			return IF_FORCE;
		}

		public void setIF_FORCE(int iF_FORCE) {
			IF_FORCE = iF_FORCE;
		}

		public long getRELEASE_DATE() {
			return RELEASE_DATE;
		}

		public void setRELEASE_DATE(long rELEASE_DATE) {
			RELEASE_DATE = rELEASE_DATE;
		}

		public String getVERSION() {
			return VERSION;
		}

		public void setVERSION(String vERSION) {
			VERSION = vERSION;
		}

		public int getVERSION_CODE() {
			return VERSION_CODE;
		}

		public void setVERSION_CODE(int vERSION_CODE) {
			VERSION_CODE = vERSION_CODE;
		}

		public String getFILE_URL() {
			return FILE_URL;
		}

		public void setFILE_URL(String fILE_URL) {
			FILE_URL = fILE_URL;
		}

		public String getRELEASE_NOTE() {
			return RELEASE_NOTE;
		}

		public void setRELEASE_NOTE(String rELEASE_NOTE) {
			RELEASE_NOTE = rELEASE_NOTE;
		}

	}

	public class DicMap {

		/**
		 * 案件类型版本
		 */
		private int CASEVERSION = 1;

		/**
		 * 地区类型版本
		 */
		private int AREAVERSION = 1;

		public long getCASEVERSION() {
			return CASEVERSION;
		}

		public void setCASEVERSION(int cASEVERSION) {
			CASEVERSION = cASEVERSION;
		}

		public long getAREAVERSION() {
			return AREAVERSION;
		}

		public void setAREAVERSION(int aREAVERSION) {
			AREAVERSION = aREAVERSION;
		}

	}

	public class OverImage {

		private long ID;
		private String URL;
		private String NAME;
		private String DESCRIPTION;
		private long CREATE_DATE;
		private String IMAGE_URL;

		public long getID() {
			return ID;
		}

		public void setID(long iD) {
			ID = iD;
		}

		public String getURL() {
			return URL;
		}

		public void setURL(String uRL) {
			URL = uRL;
		}

		public String getNAME() {
			return NAME;
		}

		public void setNAME(String nAME) {
			NAME = nAME;
		}

		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		public void setDESCRIPTION(String dESCRIPTION) {
			DESCRIPTION = dESCRIPTION;
		}

		public long getCREATE_DATE() {
			return CREATE_DATE;
		}

		public void setCREATE_DATE(long cREATE_DATE) {
			CREATE_DATE = cREATE_DATE;
		}

		public String getIMAGE_URL() {
			return IMAGE_URL;
		}

		public void setIMAGE_URL(String iMAGE_URL) {
			IMAGE_URL = iMAGE_URL;
		}

	}

}
