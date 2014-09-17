package com.puji.salesshowroom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.puji.util.DisplayUtils;

public class DetailActivity extends Activity {

	private DisplayUtils mDisplayUtils;
	private LinearLayout mGraphViewLayout;
	private LineGraphView mGraphView;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		mDisplayUtils = new DisplayUtils(this);
		initView();
		initTable();
	}

	private void initView() {

		LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);
		RelativeLayout.LayoutParams layoutParams = (LayoutParams) bottomLayout
				.getLayoutParams();
		int height = layoutParams.height = mDisplayUtils.getDisplayHeight() * 2 / 7;
		bottomLayout.setLayoutParams(layoutParams);

		LinearLayout bottomLayout2 = (LinearLayout) findViewById(R.id.bottom_layout2);
		RelativeLayout.LayoutParams layoutParams2 = (LayoutParams) bottomLayout2
				.getLayoutParams();
		int width = layoutParams2.width = mDisplayUtils.getDisplayWidth() / 4;
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
		topLayoutParams.width = (int) (width + TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 80, getResources()
						.getDisplayMetrics()));
		topLayout.setLayoutParams(topLayoutParams);

		mGraphViewLayout = (LinearLayout) bottomLayout
				.findViewById(R.id.graph_layout);

		mListView = (ListView) findViewById(R.id.performance_list_view);
		mListView.setAdapter(new MyAdapter());

	}

	private void initTable() {

		mGraphView = new LineGraphView(this, "本月销售变化");
		String[] horizontalLabels = new String[31];
		for (int i = 0; i < 31; i++) {
			if ((i + 1) % 5 == 0) {
				horizontalLabels[i] = i + 1 + "日";
			} else {
				horizontalLabels[i] = "";
			}
		}
		mGraphView.setHorizontalLabels(horizontalLabels);
		mGraphView.getGraphViewStyle().setGridColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setTextSize(12);
		mGraphView.getGraphViewStyle().setVerticalLabelsWidth(30);
		mGraphView.getGraphViewStyle().setNumHorizontalLabels(31);
		mGraphView.setBackgroundColor(Color.parseColor("#66436EEE"));
		mGraphView.setDrawBackground(true);
		mGraphView.getGraphViewStyle().setLegendWidth(1);

		GraphViewData[] data = new GraphViewData[31];
		data[0] = new GraphViewData(0, 0);
		data[1] = new GraphViewData(1, 40);
		data[2] = new GraphViewData(2, 60);
		data[3] = new GraphViewData(3, 60);
		data[4] = new GraphViewData(4, 62);
		for (int i = 5; i < 31; i++) {
			data[i] = new GraphViewData(i, i * 2);
		}
		mGraphView.addSeries(new GraphViewSeries(data));
		mGraphViewLayout.addView(mGraphView);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getLayoutInflater().inflate(R.layout.performance_list_item,
					parent, false);
		}

	}
}
