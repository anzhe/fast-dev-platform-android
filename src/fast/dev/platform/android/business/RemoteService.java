package fast.dev.platform.android.business;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fast.dev.platform.android.bean.ConsultBean;
import fast.dev.platform.android.bean.business.AcceptEntrustBusinessBean;
import fast.dev.platform.android.bean.business.AcceptReviewBusinessBean;
import fast.dev.platform.android.bean.business.AddConsultBusinessBean;
import fast.dev.platform.android.bean.business.AddEntrustBusinessBean;
import fast.dev.platform.android.bean.business.AddLegalAidBusinessBean;
import fast.dev.platform.android.bean.business.AddWritBusinessBean;
import fast.dev.platform.android.bean.business.BuyWritBussniseBean;
import fast.dev.platform.android.bean.business.CalculateBusinessBean;
import fast.dev.platform.android.bean.business.CancelEntrustBusinessBean;
import fast.dev.platform.android.bean.business.ConsultBusinessBean;
import fast.dev.platform.android.bean.business.ConsultDetailBusinessBean;
import fast.dev.platform.android.bean.business.DeleteConsultBusinessBean;
import fast.dev.platform.android.bean.business.DeleteEntrustBusinessBean;
import fast.dev.platform.android.bean.business.DeleteLegalAidBusinessBean;
import fast.dev.platform.android.bean.business.DeleteMessageBusinessBean;
import fast.dev.platform.android.bean.business.DeleteWritCustomBusinessBean;
import fast.dev.platform.android.bean.business.DeleteWritOrderBusinessBean;
import fast.dev.platform.android.bean.business.DictBussniseBean;
import fast.dev.platform.android.bean.business.EntrustBusinessBean;
import fast.dev.platform.android.bean.business.EntrustDetailBusinessBean;
import fast.dev.platform.android.bean.business.EntrustPayBussniseBean;
import fast.dev.platform.android.bean.business.EntrustPayHistoryBussniseBean;
import fast.dev.platform.android.bean.business.FansBusinessBean;
import fast.dev.platform.android.bean.business.FeedbackBusinessBean;
import fast.dev.platform.android.bean.business.FocusBusinessBean;
import fast.dev.platform.android.bean.business.GetAlipayAccountBusinessBean;
import fast.dev.platform.android.bean.business.GetAppLatestVersionInfoBusinessBean;
import fast.dev.platform.android.bean.business.GetCaptchaBusinessBean;
import fast.dev.platform.android.bean.business.GetFansDetailBusinessBean;
import fast.dev.platform.android.bean.business.GetLawyerInfoBusinessBean;
import fast.dev.platform.android.bean.business.HandleAppointEntrustBusinessBean;
import fast.dev.platform.android.bean.business.LawBriefBussniseBean;
import fast.dev.platform.android.bean.business.LawyerBusinessBean;
import fast.dev.platform.android.bean.business.LegalAidBusinessBean;
import fast.dev.platform.android.bean.business.LegalAidDetailBusinessBean;
import fast.dev.platform.android.bean.business.LoginBussniseBean;
import fast.dev.platform.android.bean.business.MessageBusinessBean;
import fast.dev.platform.android.bean.business.PayWritBussniseBean;
import fast.dev.platform.android.bean.business.QueryMoneyBussniseBean;
import fast.dev.platform.android.bean.business.QueryOrderStatusBussniseBean;
import fast.dev.platform.android.bean.business.QuestionBussniseBean;
import fast.dev.platform.android.bean.business.RechargeBussniseBean;
import fast.dev.platform.android.bean.business.RegisterBusinessBean;
import fast.dev.platform.android.bean.business.ResendEmailBussniseBean;
import fast.dev.platform.android.bean.business.ReviewBusinessBean;
import fast.dev.platform.android.bean.business.SendReviewBusinessBean;
import fast.dev.platform.android.bean.business.SubmitInformBusinessBean;
import fast.dev.platform.android.bean.business.SubmitTempOrderBusinessBean;
import fast.dev.platform.android.bean.business.SystemDictBussniseBean;
import fast.dev.platform.android.bean.business.ThemeBusinessBean;
import fast.dev.platform.android.bean.business.ThirdPartyLoginBussniseBean;
import fast.dev.platform.android.bean.business.ToBalanceBusinessBean;
import fast.dev.platform.android.bean.business.ToBasicAccountBusinessBean;
import fast.dev.platform.android.bean.business.ToZhifubaoBusinessBean;
import fast.dev.platform.android.bean.business.TradeStatementBusinessBean;
import fast.dev.platform.android.bean.business.UpdateEntrustBusinessBean;
import fast.dev.platform.android.bean.business.UpdateUserInfoBusinessBean;
import fast.dev.platform.android.bean.business.WithdrawDepositDetailBusinessBean;
import fast.dev.platform.android.bean.business.WritCustomBusinessBean;
import fast.dev.platform.android.bean.business.WritCustomDetailBusinessBean;
import fast.dev.platform.android.bean.business.WritOrderBusinessBean;
import fast.dev.platform.android.bean.business.WritOrderDetailBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.HttpUtils;

public class RemoteService {

	private static Gson gson = new Gson();

	/**
	 * 获取咨询列表
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static ConsultBusinessBean getConsultList(HashMap<String, String> params) {
		ConsultBusinessBean consultBusinessBean = new ConsultBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/consulting/list", params);
			consultBusinessBean = gson.fromJson(result_str, ConsultBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consultBusinessBean;
	}
	
	/**
	 * 获取法律援助列表
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static LegalAidBusinessBean getLegalAidList(HashMap<String, String> params) {
		LegalAidBusinessBean legalAidBusinessBean = new LegalAidBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/help/list", params);
			legalAidBusinessBean = gson.fromJson(result_str, LegalAidBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return legalAidBusinessBean;
	}
	
	/**
	 * 获取文书定制列表
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static WritCustomBusinessBean getWritCustomList(HashMap<String, String> params) {
		WritCustomBusinessBean writCustomBusinessBean = new WritCustomBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/docCustomize/list", params);
			writCustomBusinessBean = gson.fromJson(result_str, WritCustomBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writCustomBusinessBean;
	}

	/**
	 * 获取案件类型或地区字典
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static DictBussniseBean getDictList(HashMap<String, String> params) {
		DictBussniseBean dictBussniseBean = new DictBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/dict/list", params);
			dictBussniseBean = gson.fromJson(result_str, DictBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dictBussniseBean;
	}
	
	/**
	 * 获取系统字典
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static SystemDictBussniseBean getSystemDictList(HashMap<String, String> params) {
		SystemDictBussniseBean systemDictBussniseBean = new SystemDictBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/dict/list", params);
			systemDictBussniseBean = gson.fromJson(result_str, SystemDictBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemDictBussniseBean;
	}

	public static QuestionBussniseBean getQuestionList(HashMap<String, String> params) {
		QuestionBussniseBean questionBussniseBean = new QuestionBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/question/list", params);
			questionBussniseBean = gson.fromJson(result_str, QuestionBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionBussniseBean;
	}

	public static LawBriefBussniseBean getLawBrief(HashMap<String, String> params) {
		LawBriefBussniseBean lawBriefBussniseBean = new LawBriefBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/theme/product", params);
			lawBriefBussniseBean = gson.fromJson(result_str, LawBriefBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lawBriefBussniseBean;
	}

	public static LoginBussniseBean login(HashMap<String, String> params, String role) {
		LoginBussniseBean loginBussniseBean = new LoginBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/login", params);
			loginBussniseBean = gson.fromJson(result_str, LoginBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginBussniseBean;
	}

	public static ConsultDetailBusinessBean getConsultDetail(HashMap<String, String> params) {
		ConsultDetailBusinessBean consultDetailBusinessBean = new ConsultDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/consulting/detail", params);
			consultDetailBusinessBean = gson.fromJson(result_str, ConsultDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consultDetailBusinessBean;
	}
	
	/**
	 * 获取法律援助详情
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static LegalAidDetailBusinessBean getLegalAidDetail(HashMap<String, String> params) {
		LegalAidDetailBusinessBean legalAidDetailBusinessBean = new LegalAidDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/help/detail", params);
			legalAidDetailBusinessBean = gson.fromJson(result_str, LegalAidDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return legalAidDetailBusinessBean;
	}

	/**
	 * 获取文书定制详情
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static WritCustomDetailBusinessBean getWritCustomDetail(HashMap<String, String> params) {
		WritCustomDetailBusinessBean writCustomDetailBusinessBean = new WritCustomDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/docCustomize/detail", params);
			writCustomDetailBusinessBean = gson.fromJson(result_str, WritCustomDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writCustomDetailBusinessBean;
	}
	
	/**
	 * 提交咨询
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static AddConsultBusinessBean submitConsult(HashMap<String, String> params) {
		AddConsultBusinessBean addConsultBusinessBean = new AddConsultBusinessBean();
		try {
			String result_str = "";
			if (CommonData.is_https_submitConsult) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/consulting/save", params, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/consulting/save", params);
			}
			addConsultBusinessBean = gson.fromJson(result_str, AddConsultBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addConsultBusinessBean;
	}

	public static AddWritBusinessBean submitWrit(HashMap<String, String> params) {
		AddWritBusinessBean addWritBusinessBean = new AddWritBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/docCustomize/save", params);
			addWritBusinessBean = gson.fromJson(result_str, AddWritBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addWritBusinessBean;
	}
	
	/**
	 * 申请法律援助
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static AddLegalAidBusinessBean submitLegalAid(HashMap<String, String> params) {
		AddLegalAidBusinessBean addLegalAidBusinessBean = new AddLegalAidBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/help/save", params);
			addLegalAidBusinessBean = gson.fromJson(result_str, AddLegalAidBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addLegalAidBusinessBean;
	}
	
	public static CalculateBusinessBean submitCalculate(HashMap<String, String> params) {
		CalculateBusinessBean calculateBusinessBean = new CalculateBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/expense", params);
			calculateBusinessBean = gson.fromJson(result_str, CalculateBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calculateBusinessBean;
	}

	public static ReviewBusinessBean getReview(HashMap<String, String> params) {
		ReviewBusinessBean reviewBusinessBean = new ReviewBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/reply/list", params);
			reviewBusinessBean = gson.fromJson(result_str, ReviewBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewBusinessBean;
	}

	public static SendReviewBusinessBean sendReview(HashMap<String, String> params) {
		SendReviewBusinessBean sendReviewBusinessBean = new SendReviewBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/reply/save", params);
			sendReviewBusinessBean = gson.fromJson(result_str, SendReviewBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendReviewBusinessBean;
	}

	public static AcceptReviewBusinessBean acceptReview(HashMap<String, String> params) {
		AcceptReviewBusinessBean acceptReviewBusinessBean = new AcceptReviewBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/consulting/update", params);
			acceptReviewBusinessBean = gson.fromJson(result_str, AcceptReviewBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acceptReviewBusinessBean;
	}

	public static AddEntrustBusinessBean submitEntrust(HashMap<String, String> params) {
		AddEntrustBusinessBean submitEntrustBusinessBean = new AddEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrust/save", params);
			submitEntrustBusinessBean = gson.fromJson(result_str, AddEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitEntrustBusinessBean;
	}
	
	/**
	 * 更新委托
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static UpdateEntrustBusinessBean updateEntrust(HashMap<String, String> params) {
		UpdateEntrustBusinessBean updateEntrustBusinessBean = new UpdateEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrust/updateEntrust", params);
			updateEntrustBusinessBean = gson.fromJson(result_str, UpdateEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateEntrustBusinessBean;
	}

	public static EntrustBusinessBean getEntrustList(HashMap<String, String> params) {
		EntrustBusinessBean entrustBusinessBean = new EntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrust/list", params);
			entrustBusinessBean = gson.fromJson(result_str, EntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrustBusinessBean;
	}

	/**
	 * 律师接受委托
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static AcceptEntrustBusinessBean acceptEntrust(HashMap<String, String> params) {
		AcceptEntrustBusinessBean acceptEntrustBusinessBean = new AcceptEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustaccept/save", params);
			acceptEntrustBusinessBean = gson.fromJson(result_str, AcceptEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acceptEntrustBusinessBean;
	}

	/**
	 * 律师处理指定委托
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static HandleAppointEntrustBusinessBean handleAppointEntrust(HashMap<String, String> params) {
		HandleAppointEntrustBusinessBean handleAppointEntrustBusinessBean = new HandleAppointEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustaccept/update", params);
			handleAppointEntrustBusinessBean = gson.fromJson(result_str, HandleAppointEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handleAppointEntrustBusinessBean;
	}
	
	public static EntrustDetailBusinessBean getEntrustDetail(HashMap<String, String> params) {
		EntrustDetailBusinessBean entrustDetailBusinessBean = new EntrustDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrust/detail", params);
			entrustDetailBusinessBean = gson.fromJson(result_str, EntrustDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrustDetailBusinessBean;
	}

	public static EntrustBusinessBean changeEntrust(HashMap<String, String> params) {
		EntrustBusinessBean changeBean = new EntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrust/update", params);
			changeBean = gson.fromJson(result_str, EntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return changeBean;
	}

	public static LawyerBusinessBean getLawyerList(HashMap<String, String> params) {
		LawyerBusinessBean lawyerBusinessBean = new LawyerBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustaccept/list", params);
			lawyerBusinessBean = gson.fromJson(result_str, LawyerBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lawyerBusinessBean;
	}

	public static LawyerBusinessBean getFindLawyerList(HashMap<String, String> params) {
		LawyerBusinessBean lawyerBusinessBean = new LawyerBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/list", params);
			lawyerBusinessBean = gson.fromJson(result_str, LawyerBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lawyerBusinessBean;
	}

	/**
	 * 获取主题列表
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static ThemeBusinessBean getThemeList(HashMap<String, String> queryParams) {
		ThemeBusinessBean themeBusinessBean = new ThemeBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/theme/list", queryParams);
			themeBusinessBean = gson.fromJson(result_str, ThemeBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return themeBusinessBean;
	}

	/**
	 * 获取文书订单列表
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static WritOrderBusinessBean writOrderList(HashMap<String, String> params) {
		WritOrderBusinessBean writOrderBusinessBean = new WritOrderBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/order/list", params);
			writOrderBusinessBean = gson.fromJson(result_str, WritOrderBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writOrderBusinessBean;
	}
	
	/**
	 * 获取文书订单详情
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static WritOrderDetailBusinessBean getWritOrderDetail(HashMap<String, String> params) {
		WritOrderDetailBusinessBean writOrderDetailBusinessBean = new WritOrderDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/order/detail", params);
			writOrderDetailBusinessBean = gson.fromJson(result_str, WritOrderDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writOrderDetailBusinessBean;
	}
	
	public static BuyWritBussniseBean buyWrit(HashMap<String, String> buyParams) {
		BuyWritBussniseBean buyWritBussniseBean = new BuyWritBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/order/save", buyParams);
			buyWritBussniseBean = gson.fromJson(result_str, BuyWritBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buyWritBussniseBean;
	}
	
	/**
	 * 文书产品付款
	 * 
	 * @param buyParams
	 * 
	 * @return
	 */
	public static PayWritBussniseBean payWrit(HashMap<String, String> buyParams) {
		PayWritBussniseBean payWritBussniseBean = new PayWritBussniseBean();
		try {
			String result_str = "";
			if (CommonData.is_https_payWrit) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/pay/save", buyParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/pay/save", buyParams);
			}
			payWritBussniseBean = gson.fromJson(result_str, PayWritBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payWritBussniseBean;
	}

	/**
	 * 充值
	 * 
	 * @param rechargeParams
	 * 
	 * @return
	 */
	public static RechargeBussniseBean recharge(HashMap<String, String> rechargeParams) {
		RechargeBussniseBean rechargeBussniseBean = new RechargeBussniseBean();
		try {
			String result_str = "";
			if (CommonData.is_https_recharge) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/transflow/save", rechargeParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/transflow/save", rechargeParams);
			}
			rechargeBussniseBean = gson.fromJson(result_str, RechargeBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rechargeBussniseBean;
	}
	
	/**
	 * 根据订单号查询订单状态
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static QueryOrderStatusBussniseBean queryOrderStatus(HashMap<String, String> queryParams) {
		QueryOrderStatusBussniseBean queryOrderStatusBussniseBean = new QueryOrderStatusBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/wxpay/query", queryParams);
			queryOrderStatusBussniseBean = gson.fromJson(result_str, QueryOrderStatusBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryOrderStatusBussniseBean;
	}
	
	/**
	 * 线上委托付款
	 * 
	 * @param payParams
	 * 
	 * @return
	 */
	public static EntrustPayBussniseBean entrustPay(HashMap<String, String> payParams) {
		EntrustPayBussniseBean entrustPayBussniseBean = new EntrustPayBussniseBean();
		try {
			String result_str = "";
			if (CommonData.is_https_entrustPay) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/entrustpaid/save", payParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustpaid/save", payParams);
			}
			entrustPayBussniseBean = gson.fromJson(result_str, EntrustPayBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrustPayBussniseBean;
	}
	
	/**
	 * 线上委托付款记录
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static EntrustPayHistoryBussniseBean entrustPayHistory(HashMap<String, String> queryParams) {
		EntrustPayHistoryBussniseBean entrustPayHistoryBussniseBean = new EntrustPayHistoryBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustpaid/getpaid", queryParams);
			entrustPayHistoryBussniseBean = gson.fromJson(result_str, EntrustPayHistoryBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrustPayHistoryBussniseBean;
	}
	
	/**
	 * 获取消息列表
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static MessageBusinessBean getMessages(HashMap<String, String> queryParams) {
		MessageBusinessBean messageBusinessBean = new MessageBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/message/list", queryParams);
			messageBusinessBean = gson.fromJson(result_str, MessageBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageBusinessBean;
	}

	/**
	 * 获取账户交易记录
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static TradeStatementBusinessBean getTradeStatement(HashMap<String, String> queryParams) {
		TradeStatementBusinessBean tradeStatementBusinessBean = new TradeStatementBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/transflow/list", queryParams);
			tradeStatementBusinessBean = gson.fromJson(result_str, TradeStatementBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradeStatementBusinessBean;
	}
	
	/**
	 * 提现获取支付宝账号
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static GetAlipayAccountBusinessBean getAlipayAccount(HashMap<String, String> queryParams) {
		GetAlipayAccountBusinessBean getAlipayAccountBusinessBean = new GetAlipayAccountBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/withdraw/getAliaccount", queryParams);
			getAlipayAccountBusinessBean = gson.fromJson(result_str, GetAlipayAccountBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAlipayAccountBusinessBean;
	}
	
	/**
	 * 提现到支付宝
	 * 
	 * @param submitParams
	 * 
	 * @return
	 */
	public static ToZhifubaoBusinessBean toZhifubao(HashMap<String, String> submitParams) {
		ToZhifubaoBusinessBean toZhifubaoBusinessBean = new ToZhifubaoBusinessBean();
		try {
			String result_str = "";
			if (CommonData.is_https_toZhifubao) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/withdraw/saveAlipay", submitParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/withdraw/saveAlipay", submitParams);
			}
			toZhifubaoBusinessBean = gson.fromJson(result_str, ToZhifubaoBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toZhifubaoBusinessBean;
	}
	
	/**
	 * 律师提现到对公账户
	 * 
	 * @param submitParams
	 * 
	 * @return
	 */
	public static ToBasicAccountBusinessBean toBasicAccount(HashMap<String, String> submitParams) {
		ToBasicAccountBusinessBean toBasicAccountBusinessBean = new ToBasicAccountBusinessBean();
		try {
			String result_str = "";
			if (CommonData.is_https_toBasicAccount) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/withdraw/saveTobusiness", submitParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/withdraw/saveTobusiness", submitParams);
			}
			toBasicAccountBusinessBean = gson.fromJson(result_str, ToBasicAccountBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toBasicAccountBusinessBean;
	}
	
	/**
	 * 律师盈利转到充值账户
	 * 
	 * @param submitParams
	 * 
	 * @return
	 */
	public static ToBalanceBusinessBean toBalance(HashMap<String, String> submitParams) {
		ToBalanceBusinessBean toBalanceBusinessBean = new ToBalanceBusinessBean();
		try {
			String result_str = "";
			if (CommonData.is_https_toBalance) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/withdraw/saveWithdraw", submitParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/withdraw/saveWithdraw", submitParams);
			}
			toBalanceBusinessBean = gson.fromJson(result_str, ToBalanceBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toBalanceBusinessBean;
	}
	
	/**
	 * 查询提现详情
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static WithdrawDepositDetailBusinessBean getWithdrawDepositDetail(HashMap<String, String> queryParams) {
		WithdrawDepositDetailBusinessBean withdrawDepositDetailBusinessBean = new WithdrawDepositDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/withdraw/withdrawDetail", queryParams);
			withdrawDepositDetailBusinessBean = gson.fromJson(result_str, WithdrawDepositDetailBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return withdrawDepositDetailBusinessBean;
	}
	
	public static ResendEmailBussniseBean resendEmail(HashMap<String, String> sendParams) {
		ResendEmailBussniseBean resendEmailBussniseBean = new ResendEmailBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/pay/reSendEmail", sendParams);
			resendEmailBussniseBean = gson.fromJson(result_str, ResendEmailBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resendEmailBussniseBean;
	}

	public static RegisterBusinessBean register(HashMap<String, String> registerParams, String role) {
		RegisterBusinessBean registerBusinessBean = new RegisterBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/register", registerParams);
			registerBusinessBean = gson.fromJson(result_str, RegisterBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registerBusinessBean;
	}

	/**
	 * 删除文书订单
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteWritOrderBusinessBean deleteWritOrder(HashMap<String, String> deleteParams) {
		DeleteWritOrderBusinessBean deleteWritOrderBusinessBean = new DeleteWritOrderBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/order/update", deleteParams);
			deleteWritOrderBusinessBean = gson.fromJson(result_str, DeleteWritOrderBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteWritOrderBusinessBean;
	}

	/**
	 * 删除委托
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteEntrustBusinessBean deleteEntrust(HashMap<String, String> deleteParams) {
		DeleteEntrustBusinessBean deleteEntrustBusinessBean = new DeleteEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustaccept/delete", deleteParams);
			deleteEntrustBusinessBean = gson.fromJson(result_str, DeleteEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteEntrustBusinessBean;
	}
	
	/**
	 * 删除咨询
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteConsultBusinessBean deleteConsult(HashMap<String, String> deleteParams) {
		DeleteConsultBusinessBean deleteConsultBusinessBean = new DeleteConsultBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/consulting/update", deleteParams);
			deleteConsultBusinessBean = gson.fromJson(result_str, DeleteConsultBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteConsultBusinessBean;
	}
	
	/**
	 * 删除法律援助
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteLegalAidBusinessBean deleteLegalAid(HashMap<String, String> deleteParams) {
		DeleteLegalAidBusinessBean deleteLegalAidBusinessBean = new DeleteLegalAidBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/help/update", deleteParams);
			deleteLegalAidBusinessBean = gson.fromJson(result_str, DeleteLegalAidBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteLegalAidBusinessBean;
	}

	/**
	 * 删除文书定制
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteWritCustomBusinessBean deleteWritCustom(HashMap<String, String> deleteParams) {
		DeleteWritCustomBusinessBean deleteWritCustomBusinessBean = new DeleteWritCustomBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/docCustomize/update", deleteParams);
			deleteWritCustomBusinessBean = gson.fromJson(result_str, DeleteWritCustomBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteWritCustomBusinessBean;
	}
	
	/**
	 * 律师取消委托
	 * 
	 * @param cancelParams
	 * 
	 * @return
	 */
	public static CancelEntrustBusinessBean cancelEntrust(HashMap<String, String> cancelParams) {
		CancelEntrustBusinessBean cancelEntrustBusinessBean = new CancelEntrustBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/entrustaccept/save", cancelParams);
			cancelEntrustBusinessBean = gson.fromJson(result_str, CancelEntrustBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelEntrustBusinessBean;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param updateParams
	 * @param role 
	 * 
	 * @return
	 */
	public static UpdateUserInfoBusinessBean updateUserInfo(HashMap<String, String> updateParams, String role) {
		UpdateUserInfoBusinessBean updateUserInfoBusinessBean = new UpdateUserInfoBusinessBean();
		try {
			String result_str = "";
			if (CommonData.is_https_updateUserInfo) {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTPS + "/if/" + role + "/update", updateParams, true);
			} else {
				result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/update", updateParams);
			}
			updateUserInfoBusinessBean = gson.fromJson(result_str, UpdateUserInfoBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateUserInfoBusinessBean;
	}
	
	/**
	 * 获取律师信息
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static GetLawyerInfoBusinessBean getLawyerInfo(HashMap<String, String> queryParams) {
		GetLawyerInfoBusinessBean getLawyerInfoBusinessBean = new GetLawyerInfoBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/detail", queryParams);
			getLawyerInfoBusinessBean = gson.fromJson(result_str, GetLawyerInfoBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getLawyerInfoBusinessBean;
	}

	/**
	 * 提交举报
	 * 
	 * @param submitParams
	 * 
	 * @return
	 */
	public static SubmitInformBusinessBean submitInform(HashMap<String, String> submitParams) {
		SubmitInformBusinessBean submitInformBusinessBean = new SubmitInformBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/report/save", submitParams);
			submitInformBusinessBean = gson.fromJson(result_str, SubmitInformBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitInformBusinessBean;
	}
	
	/**
	 * 提交临时订单数据
	 * 
	 * @param submitParams
	 * 
	 * @return
	 */
	public static SubmitTempOrderBusinessBean submitTempOrder(HashMap<String, String> submitParams) {
		SubmitTempOrderBusinessBean submitTempOrderBusinessBean = new SubmitTempOrderBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/orderTemp/save", submitParams);
			submitTempOrderBusinessBean = gson.fromJson(result_str, SubmitTempOrderBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitTempOrderBusinessBean;
	}
	
	public static GetCaptchaBusinessBean getCaptcha(HashMap<String, String> requestParams) {
		GetCaptchaBusinessBean getCaptchaBusinessBean = new GetCaptchaBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sms", requestParams);
			getCaptchaBusinessBean = gson.fromJson(result_str, GetCaptchaBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCaptchaBusinessBean;
	}

	/**
	 * 用户反馈
	 * 
	 * @param feedbackParams
	 * 
	 * @return
	 */
	public static FeedbackBusinessBean feedback(HashMap<String, String> feedbackParams) {
		FeedbackBusinessBean feedbackBusinessBean = new FeedbackBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/feedback/save", feedbackParams);
			feedbackBusinessBean = gson.fromJson(result_str, FeedbackBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackBusinessBean;
	}

	public static GetAppLatestVersionInfoBusinessBean getAppLatestVersionInfo(HashMap<String, String> queryParams) {
		GetAppLatestVersionInfoBusinessBean getAppLatestVersionInfoBusinessBean = new GetAppLatestVersionInfoBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/version/detail", queryParams);
			getAppLatestVersionInfoBusinessBean = gson.fromJson(result_str, GetAppLatestVersionInfoBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAppLatestVersionInfoBusinessBean;
	}

	/**
	 * 删除个人信息
	 * 
	 * @param deleteParams
	 * 
	 * @return
	 */
	public static DeleteMessageBusinessBean deleteMessage(HashMap<String, String> deleteParams) {
		DeleteMessageBusinessBean deleteMessageBusinessBean = new DeleteMessageBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/message/delete", deleteParams);
			deleteMessageBusinessBean = gson.fromJson(result_str, DeleteMessageBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteMessageBusinessBean;
	}

	/**
	 * 获取粉丝列表
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static FansBusinessBean getFansList(HashMap<String, String> queryParams) {
		FansBusinessBean fansBusinessBean = new FansBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/userattention/list", queryParams);
			fansBusinessBean = gson.fromJson(result_str, FansBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fansBusinessBean;
	}

	/**
	 * 同步用户或律师经纬度
	 * 
	 * @param params
	 * 
	 * @return
	 */
	public static void syncLocation(HashMap<String, String> params) {
		try {
			HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/gislog/save", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取粉丝详情
	 * 
	 * @param queryParams
	 * 
	 * @return
	 */
	public static GetFansDetailBusinessBean getFansDetail(HashMap<String, String> queryParams) {
		GetFansDetailBusinessBean getFansDetailBusinessBean = new GetFansDetailBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/userattention/detail", queryParams);
			JSONObject result_obj = new JSONObject(result_str);
			getFansDetailBusinessBean.setResultcode(result_obj.getString("resultcode"));
			getFansDetailBusinessBean.setResultdesc(result_obj.getString("resultdesc"));
			JSONObject data = result_obj.getJSONObject("data");
			getFansDetailBusinessBean.setATTEN_NUMBER(data.getString("ATTEN_NUMBER"));
			getFansDetailBusinessBean.setREL_NAME(data.getString("REL_NAME"));
			getFansDetailBusinessBean.setPHOTO(data.getString("PHOTO"));
			getFansDetailBusinessBean.setType(data.getInt("TYPE"));
			JSONObject list = data.getJSONObject("list");
			getFansDetailBusinessBean.setPagesize(list.getInt("pagesize"));
			getFansDetailBusinessBean.setCurrentpage(list.getInt("currentpage"));
			getFansDetailBusinessBean.setTotalpage(list.getInt("totalpage"));
			getFansDetailBusinessBean.setTotalrecord(list.getInt("totalrecord"));
			JSONArray lstdata = list.getJSONArray("lstdata");
			ArrayList<ConsultBean> consultBeans = gson.fromJson(lstdata.toString(), new TypeToken<ArrayList<ConsultBean>>(){}.getType());
			getFansDetailBusinessBean.setLstdata(consultBeans);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getFansDetailBusinessBean;
	}

	/**
	 * 关注
	 * 
	 * @param focusParams
	 * 
	 * @return
	 */
	public static FocusBusinessBean focus(HashMap<String, String> focusParams) {
		FocusBusinessBean focusBusinessBean = new FocusBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/userattention/save", focusParams);
			focusBusinessBean = gson.fromJson(result_str, FocusBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return focusBusinessBean;
	}
	
	/**
	 * 取消关注
	 * 
	 * @param unfocusParams
	 * 
	 * @return
	 */
	public static FocusBusinessBean unfocus(HashMap<String, String> unfocusParams) {
		FocusBusinessBean focusBusinessBean = new FocusBusinessBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/userattention/delete", unfocusParams);
			focusBusinessBean = gson.fromJson(result_str, FocusBusinessBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return focusBusinessBean;
	}

	public static ThirdPartyLoginBussniseBean thirdPartyLoginBussnise(HashMap<String, String> thirdPartyLoginParams) {
		ThirdPartyLoginBussniseBean thirdPartyLoginBussniseBean = new ThirdPartyLoginBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/thirdparty/login", thirdPartyLoginParams);
			thirdPartyLoginBussniseBean = gson.fromJson(result_str, ThirdPartyLoginBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thirdPartyLoginBussniseBean;
	}

	/**
	 * 查询用户余额
	 * 
	 * @param queryParams
	 * @param role
	 * 
	 * @return
	 */
	public static QueryMoneyBussniseBean queryMoney(HashMap<String, String> queryParams, String role) {
		QueryMoneyBussniseBean queryMoneyBussniseBean = new QueryMoneyBussniseBean();
		try {
			String result_str = HttpUtils.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/getmoney", queryParams);
			queryMoneyBussniseBean = gson.fromJson(result_str, QueryMoneyBussniseBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryMoneyBussniseBean;
	}
	
}
