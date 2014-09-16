package com.puji.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DisplayUtils {

	Context mContext;

	public DisplayUtils(Context context) {
		mContext = context;
	}

	/**
	 * 得到设备的屏幕高度
	 * 
	 * @return
	 */
	public int getDisplayHeight() {

		DisplayMetrics metrics = new DisplayMetrics();

		WindowManager windowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);

		windowManager.getDefaultDisplay().getMetrics(metrics);

		return metrics.heightPixels;
	}

	/**
	 * 得到设备的屏幕宽度
	 * 
	 * @return
	 */
	public int getDisplayWidth() {

		DisplayMetrics metrics = new DisplayMetrics();

		WindowManager windowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);

		windowManager.getDefaultDisplay().getMetrics(metrics);

		return metrics.widthPixels;

	}

	/**
	 * 测量View的高度
	 * 
	 * @param view
	 * @return
	 */
	public int measureViewHeight(View view) {

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredHeight();

	}

}
