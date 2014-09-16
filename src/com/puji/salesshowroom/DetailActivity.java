package com.puji.salesshowroom;

import com.puji.util.DisplayUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DetailActivity extends Activity {

	private DisplayUtils mDisplayUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		mDisplayUtils = new DisplayUtils(this);
		initView();
	}

	private void initView() {

		LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);
		RelativeLayout.LayoutParams layoutParams = (LayoutParams) bottomLayout
				.getLayoutParams();
		int height = layoutParams.height = mDisplayUtils.getDisplayHeight() * 2 / 7;
		bottomLayout.setLayoutParams(layoutParams);

		RelativeLayout bottomLayout2 = (RelativeLayout) findViewById(R.id.bottom_layout2);
		RelativeLayout.LayoutParams layoutParams2 = (LayoutParams) bottomLayout2
				.getLayoutParams();
		layoutParams2.width = mDisplayUtils.getDisplayWidth() / 4;
		layoutParams2.height = (int) (height + TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 80, getResources()
						.getDisplayMetrics()));
		bottomLayout2.setLayoutParams(layoutParams2);

		RelativeLayout topLayout = (RelativeLayout) findViewById(R.id.top_layout);
		RelativeLayout.LayoutParams topLayoutParams = (LayoutParams) topLayout
				.getLayoutParams();
		topLayoutParams.height = (int) (mDisplayUtils.getDisplayHeight()
				- height - TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 80, getResources()
						.getDisplayMetrics()));
		topLayout.setLayoutParams(topLayoutParams);

	}
}
