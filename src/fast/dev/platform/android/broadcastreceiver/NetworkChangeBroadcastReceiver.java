package fast.dev.platform.android.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeBroadcastReceiver extends BroadcastReceiver {

	private Class<?> clazz;
	
	public NetworkChangeBroadcastReceiver(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent networkIntent = new Intent(context, clazz);
		networkIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		context.startActivity(networkIntent);
	}

}
