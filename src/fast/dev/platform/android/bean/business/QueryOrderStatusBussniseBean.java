package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class QueryOrderStatusBussniseBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		/**
		 * SUCCESS—支付成功 
		 * REFUND—转入退款 
		 * NOTPAY—未支付 
		 * CLOSED—已关闭 
		 * REVOKED—已撤销（刷卡支付）
		 * USERPAYING--用户支付中 
		 * PAYERROR--支付失败(其他原因，如银行返回失败) 
		 * FAIL-没有这个订单或者查询失败
		 */
		private String TRADE_STATE;

		public String getTRADE_STATE() {
			return TRADE_STATE;
		}

		public void setTRADE_STATE(String tRADE_STATE) {
			TRADE_STATE = tRADE_STATE;
		}

	}

}
