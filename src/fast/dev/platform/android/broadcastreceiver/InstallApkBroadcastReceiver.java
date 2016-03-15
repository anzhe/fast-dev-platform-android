package fast.dev.platform.android.broadcastreceiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class InstallApkBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		long EXTRA_DOWNLOAD_ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
		SharedPreferences cache_sp = context.getSharedPreferences("cache_sp", Context.MODE_PRIVATE);
		long download_id = cache_sp.getLong("download_id", 0);
		if (EXTRA_DOWNLOAD_ID == download_id) {
			DownloadManager mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
			Intent install = new Intent(Intent.ACTION_VIEW);
			Uri uri = mDownloadManager.getUriForDownloadedFile(EXTRA_DOWNLOAD_ID);
			install.setDataAndType(uri, "application/vnd.android.package-archive");
			install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(install);
			cache_sp.edit().clear().apply();
		}
	}

}
