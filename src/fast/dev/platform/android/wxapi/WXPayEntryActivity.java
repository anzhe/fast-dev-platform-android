package fast.dev.platform.android.wxapi;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import fast.dev.platform.android.R;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.ToastUtils;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;
	
	public static PayCallback payCallback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, CommonData.WX_APP_ID);

		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {

	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			String msg = "";
			switch (resp.errCode) {
			case 0:
				msg = "支付成功";
				if (payCallback != null) {
					payCallback.paySuccess();
				}
				break;
			case -1:
				msg = "支付失败";
				break;
			case -2:
				msg = "您取消了支付";
				break;
			default:
				msg = "支付失败";
				break;
			}
			ToastUtils.showShort(WXPayEntryActivity.this, msg);
			WXPayEntryActivity.this.finish();
		}
	}

	public interface PayCallback {
		
		public void paySuccess();
		
	}
	
}
