package cn.testinone.helper;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;

//跟网络相关的工具类
public class NetUtils
{
	private NetUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断网络是否连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context)
	{

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity)
		{

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected())
			{
				if (info.getState() == NetworkInfo.State.CONNECTED)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifi(Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null)
			return false;
		return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity)
	{
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}

	public interface GetMACCallback
	{
		void onComplete(String result);
	}

	/*
	获取mac地址，通过callback返回值
	 */
	public static void getMac(final Activity ac, final GetMACCallback callback) {
		final WifiManager wm = (WifiManager) ac .getSystemService(Service.WIFI_SERVICE);

		// 如果本次开机后打开过WIFI，则能够直接获取到mac信息。立刻返回数据。
		WifiInfo info = wm.getConnectionInfo();
		if (info != null && info.getMacAddress() != null) {
			if (callback != null) {
				callback.onComplete(info.getMacAddress());
			}
			return;
		}

		// 尝试打开WIFI，并获取mac地址
		if (!wm.isWifiEnabled()) {
			wm.setWifiEnabled(true);
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				int tryCount = 0;
				final int MAX_COUNT = 10;

				while (tryCount < MAX_COUNT) {
					final WifiInfo info = wm.getConnectionInfo();
					if (info != null && info.getMacAddress() != null) {
						if (callback != null) {
							ac.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									callback.onComplete(info.getMacAddress());
								}
							});
						}
						return;
					}

					SystemClock.sleep(300);
					tryCount++;
				}

				// 未获取到mac地址
				if (callback != null) {
					callback.onComplete(null);
				}
			}
		}).start();
	}
}
