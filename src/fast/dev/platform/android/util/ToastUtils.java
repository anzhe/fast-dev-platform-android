package fast.dev.platform.android.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	public static void showLong(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	
	public static void showShort(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	public static void showLong(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	
	public static void showShort(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

}
