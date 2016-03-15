package fast.dev.platform.android.constant;

public class CommonData {

	/**
	 * 律师申请加盟的时候，需要加纳一定的保证金，才可加盟，保证金会作为律师的余额展示
	 */
	public static final String DICT_JOIN_PAY = "DICT_JOIN_PAY";
	
	/**
	 * 律师每次接单的时候，需要扣除一定的金额给平台作为平台一单的抽成。
	 */
	public static final String DICT_BUCKLE_AMOUNT = "DICT_BUCKLE_AMOUNT";
	
	/**
	 * 用户使用红包时，根据总体消费的百分比限制用户使用红包的金额。
	 */
	public static final String DICT_PACKET_LIMIT = "DICT_PACKET_LIMIT";
	
	/**
	 * 图片上传地址
	 */
	public static final String DICT_IMAGE_UPLORD = "DICT_IMAGE_UPLORD";
	
	/**
	 * 用户注册就送红包的金额
	 */
	public static final String DICT_GIVE_PACKET = "DICT_GIVE_PACKET";
	
	/**
	 * 律师冻结的保证金
	 */
	public static final String DICT_CAUTION_MONEY = "DICT_CAUTION_MONEY";
	
	/**
	 * 普通用户注册协议
	 */
	public static final String DICT_U_REGISTERAGREEMENT = "DICT_U_REGISTERAGREEMENT";
	
	/**
	 * 律师用户注册协议
	 */
	public static final String DICT_L_REGISTERAGREEMENT = "DICT_L_REGISTERAGREEMENT";
	
	/**
	 * 律师同行协议
	 */
	public static final String DICT_USERAGREEMENT = "DICT_USERAGREEMENT";
	
	/**
	 * 用户邀请成功，根据邀请码送的红包
	 */
	public static final String DICT_INVITED_PACKET = "DICT_INVITED_PACKET";
	
	/**
	 * 文书定制的费用
	 */
	public static final String DICT_DOC_CUSTOMIZE = "DICT_DOC_CUSTOMIZE";
	
	/**
	 * 支付宝回调地址
	 */
	public static final String DICT_ALIPAY_URL = "DICT_ALIPAY_URL";
	
	/**
	 * 微支付回调地址
	 */
	public static final String DICT_WXPAY_URL = "DICT_WXPAY_URL";
	
	/** 网络参数：访问返回的状态 */
	public final static String HTTP_STATUS = "status";
	/** 网络参数：访问返回的信息 */
	public final static String HTTP_RESULT = "result";
	/** 网络参数：Socket超时时间(20s) */
	public final static int HTTP_TIMEOUT_SOCKET = 30 * 1000;
	/** 网络参数：连接超时时间(30s) */
	public final static int HTTP_TIMEOUT_CONNECT = 30 * 1000;
	public final static int UPDATE_MAIN = 0;
	public final static int UPDATE = 1;
	public final static int NOFINDERRORLENGTH = 770;
	public final static String SECRET = "vstime1203";
	public final static String PROVINCEID = "1";
	public final static int STRING_CONTENT = 1;
	public final static int INT_CONTENT = 0;

	// 访问网络成功的访问码
	public final static String REQUEST_SUCCESS = "0000";
	public final static String REQUEST_CODE_ERROR = "E1002";

	// 对话框选择标志
	public final static int SELECT_ITEM_FIRST = 1;

	public final static int SELECT_ITEM_SECOND = 2;

	/**
	 * 普通律师
	 */
	public final static int LAWYER_LEVEL_COMMON = 0;
	/**
	 * 加盟律师
	 */
	public final static int LAWYER_LEVEL_JOINED = 1;
	
	/**
	 * 企业服务URL
	 */
	public final static String ENTERPRISE_SERVICE_URL = "http://www.ls12348.cn/wap/service/index.html";
	
	/**
	 * 官方律师团团队成员URL
	 */
	public final static String OFFICIAL_LEGAL_TEAM_URL = "http://www.ls12348.cn/wap/lawyers/index.html";
	
	/**
	 * 分享URL
	 */
	public final static String SHARE_URL = "http://www.ls12348.cn/wap/share/index.html?code=";
	
	// 正式环境
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://www.ls12348.cn/law";
//	public final static String REMOTE_REQUEST_URL_HTTPS = "https://www.ls12348.cn:443/law";
	
	// 本地地址
//	public static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.109:8080/lawInterface";
//	public static String REMOTE_REQUEST_URL_HTTP = "http://192.16.137.1:8080/lawInterface";
	//public static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.105:8080/lawInterface";

	// 开发地址
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.104:8080/law";
//	public final static String REMOTE_REQUEST_URL_HTTPS = "https://192.168.0.104:8443/law";

	// 线上测试地址
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://www.ls12348.cn:8015/law";
//	public final static String REMOTE_REQUEST_URL_HTTPS = "https://www.ls12348.cn:8016/law";
	
	// 测试地址
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.221:8030/law";
	public final static String REMOTE_REQUEST_URL_HTTP = "http://192.168.0.205:8029/law";
	public final static String REMOTE_REQUEST_URL_HTTPS = "https://192.168.0.205:8031/law";
//	public final static String REMOTE_REQUEST_URL_HTTP = "http://www.fjzqyd.com:8821/law";
//	public final static String REMOTE_REQUEST_URL_HTTPS = "https://www.fjzqyd.com:8823/law";
	
	/**
	 * 律师类别-官方律师团
	 */
	public static final int LAWYER_LEVEL_OFFICIAL_LEGAL_TEAM = 2;
	
	/**
	 * 文件服务器上传地址
	 */
	public static String FILE_SERVER_UPLOAD_URL;
	/**
	 * 文件服务器获取图片地址
	 */
	public static String FILE_SERVER_GET_IMAGE_URL;
	
	/**
	 * 缓存目录
	 */
	public static final String cache_directory = "/lawapp/cache/";

	/**
	 * 友盟
	 */
	public static final String UMENG_APP_KEY = "55ded7ebe0f55ae922002794";
	
	/**
	 * Bugly
	 */
	public static final String BUGLY_APP_ID = "900014642";
	
	/**
	 * 云测Testin
	 */
	public static final String TESTIN_APP_KEY = "a6ce100ebd4b393045f4849420bb4a6c";
	
	/**
	 * 信鸽推送
	 */
	public static final long XG_V2_ACCESS_ID = 2100170100;// 测试环境
	public static final String XG_V2_ACCESS_KEY = "ASP4P47U42SK";// 测试环境
//	public static final long XG_V2_ACCESS_ID = 2100144115;// 正式环境
//	public static final String XG_V2_ACCESS_KEY = "AC5S82I3VE7Y";// 正式环境
	
	/**
	 * QQ
	 */
//	public static final String QQ_APP_ID = "1104761949";
//	public static final String QQ_APP_KEY = "qNW3Dm5eoxGAcc8a";
	public static final String QQ_APP_ID = "100424468";
	public static final String QQ_APP_KEY = "c7394704798a158208a74ab60104f0ba";
	
	/**
	 * 微信
	 */
	public static final String WX_APP_ID = "wx4523ea5110a03553";// AppID
	public static final String WX_API_SECRET = "ee850840e13a9217a6fa88df3565e075";// AppSecret
	public static final String WX_API_KEY = "Fujianjunyoudashujujishuyxgs6666";// API密钥，在商户平台设置
	public static final String WX_MCH_ID = "1269082101";// 商户号
//	public static final String WX_CALLBACK_URL = "http://123.56.141.13/law/if/wxpay/notify";// 服务器异步通知页面路径
	
	/**
	 * 支付宝
	 */
	public static final String ALIPAY_PARTNER = "2088021337122531";// 商户PID
	public static final String ALIPAY_SELLER = "fjjunyou@qq.com";// 商户收款账号
//	public static final String ALIPAY_CALLBACK_URL = "http://123.56.141.13/law/if/aliapi/async";// 服务器异步通知页面路径
	// 商户私钥，pkcs8格式
	public static final String ALIPAY_RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMOSOSzLvzOXvX6qLf5jTBHPSXorGtSQk2Gqa/cUpcmJKtXlirI3D1rb4kVIixFTDiOYNF3vhQt51ng1ghDiCtnI9YnAeYecqTfke3IxPxHgSThBO0VDyVHPJ7Ss0ot8KoYv92CRDU6IH+P46gvp6nt2PgZ7pqXyia40nkppDubNAgMBAAECgYBs2KYfq2pIEazyoU6l5agBxYeUigcNxUVxtwMN6hz3VPNVRsLxuzk9jWGaw+w6gSo+X0jN+y/mGjJmhwlT0irM33B5xePVmd0B2kcaI7nRwZmr8pDAEu7nJIxN+9Pmb347tWqDhllghiL3H9AOMtFjnykxA61iVY0I2FbjbQXqFQJBAPJum7mP56TYUNlas2FMDJg5W+v8eNiD/FdsF3ozxSQ1/GUcZjHAElk4Cnku2aylA/dUZQ7rtZ+gDLCrxhfbyBcCQQDOhDnmyN22QkgNHxmgPqty3WTOYTvcb/iC7mc7RJBnUjEYUSuJsbS1WxIR1Y7D46H7X4Mc21mjE03UxjmHUvK7AkEAt6M3TKGIG2NMIsjEtj/wDmLskV5mf8PCosobIpMSkzJdpI2Vj7vpajG8JT1q348D6RbBkkLUCj0nP7BuVmZCMwJBAMPbLzaqxWp3DfIzzo/lH6C+Sz5XhPgY0S0LgKf67KCwmJMhWBMsj993UXnMeataFWNbP/N8LFS2mBf1LtYiWsUCQHvALSzWV+YoKrTZnrQWzfo+1c5+PY8FpnT4P7TWXQuBCndYfpg2cAlUA/2HXUOznw5OtxTzV7sdT1qqQBKCU/Q=";
	// 支付宝公钥
	public static final String ALIPAY_RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDkjksy78zl71+qi3+Y0wRz0l6KxrUkJNhqmv3FKXJiSrV5YqyNw9a2+JFSIsRUw4jmDRd74ULedZ4NYIQ4grZyPWJwHmHnKk35HtyMT8R4Ek4QTtFQ8lRzye0rNKLfCqGL/dgkQ1OiB/j+OoL6ep7dj4Ge6al8omuNJ5KaQ7mzQIDAQAB";
	
	/**
	 * 阿里百川即时通信（正式环境）
	 */
//	public static final String OPEN_IM_APP_KEY = "23235687";
//	public static final String OPEN_IM_CS = "律师同行lstx";// 客服账号
//	public static final String OPEN_IM_CS_NICKNAME = "律师同行客服中心";// 客服账号昵称
//	public static final int OPEN_IM_GROUP_ID = 0;// 客服分组ID
	
	// 百川客服开发测试（非线网）
	public static final String OPEN_IM_APP_KEY = "23265735";
	public static final String OPEN_IM_CS = "季末豆子";// 客服账号
	public static final String OPEN_IM_CS_NICKNAME = "律师同行客服中心";// 客服账号昵称
	public static final int OPEN_IM_GROUP_ID = 158696409;// 客服分组ID
	
	/**
	 * HTTPS
	 */
	public static final String HTTPS_CERT_FILE_NAME = "yayun_test.p12";// 测试环境
	public static final String HTTPS_CERT_FILE_PASSWORD = "ce205key";// 测试环境
//	public static final String HTTPS_CERT_FILE_NAME = "client.p12";// 测试环境
//	public static final String HTTPS_CERT_FILE_PASSWORD = "123456";// 测试环境
//	public static final String HTTPS_CERT_FILE_NAME = "yayun.p12";// 正式环境
//	public static final String HTTPS_CERT_FILE_PASSWORD = "junu_key";// 正式环境
	
	/**
	 * 是否使用HTTPS
	 */
//	public static boolean is_https_submitConsult = false;// 提交咨询
//	public static boolean is_https_payWrit = false;// 文书产品付款
//	public static boolean is_https_recharge = false;// 充值
//	public static boolean is_https_entrustPay = false;// 线上委托付款
//	public static boolean is_https_toZhifubao = false;// 提现到支付宝
//	public static boolean is_https_toBasicAccount = false;// 律师提现到对公账户
//	public static boolean is_https_toBalance = false;// 律师盈利转到充值账户
//	public static boolean is_https_updateUserInfo = false;// 更新用户信息
	
	public static boolean is_https_submitConsult = true;// 提交咨询
	public static boolean is_https_payWrit = true;// 文书产品付款
	public static boolean is_https_recharge = true;// 充值
	public static boolean is_https_entrustPay = true;// 线上委托付款
	public static boolean is_https_toZhifubao = true;// 提现到支付宝
	public static boolean is_https_toBasicAccount = true;// 律师提现到对公账户
	public static boolean is_https_toBalance = true;// 律师盈利转到充值账户
	public static boolean is_https_updateUserInfo = true;// 更新用户信息
	
}
