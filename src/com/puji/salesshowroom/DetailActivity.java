package com.puji.salesshowroom;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.puji.bean.HouseDetails;
import com.puji.bean.HouseDetailsInfo;
import com.puji.bean.SalesListItem;
import com.puji.config.Config;
import com.puji.util.DisplayUtils;
import com.puji.util.JsonUtils;
import com.puji.view.CustomCircleView;

public class DetailActivity extends Activity {

	private DisplayUtils mDisplayUtils;
	private ListView mListView;
	private RequestQueue mQueue;

	private TextView mCityNameTv;
	private TextView mOpenTimeTv;
	private TextView mAddressTv;
	private TextView mPhoneNumberTv;

	private TextView mTotalCountTv;
	private TextView mSelledCountTv;
	private TextView mYesterdayCountTv;

	private TextView mSelledInfoTv;
	private TextView mSelledAddressTv;
	private TextView mSelledHouseSizeTv;

	ArrayList<SalesListItem> mSalesList;
	private LinearLayout mMonthTableLayout;
	private LineGraphView mMonthGraphView;
	private CustomCircleView mPieChartView;

	private static final int SUCCESS = 1;
	private int count = 0;

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SUCCESS:

				if (count >= mSalesList.size()) {
					count = 0;
				}

				SalesListItem salesListItem = mSalesList.get(count);
				mSelledInfoTv.setText(salesListItem.getSalesman()
						+ salesListItem.getTime() + "天前 售出了");
				mSelledAddressTv
						.setText("房号：" + salesListItem.getHouseNumber());
				mSelledHouseSizeTv.setText("平米数：" + salesListItem.getAcreage()
						+ "平米");

				if (count == 0) {
					mListView.smoothScrollToPositionFromTop(count++, 0, 0);
				} else {
					mListView.smoothScrollToPositionFromTop(count++, 0, 1000);
				}

				mHandler.sendEmptyMessageDelayed(SUCCESS, 5000);

				break;

			default:
				break;
			}

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		mDisplayUtils = new DisplayUtils(this);
		initView();
		Intent intent = getIntent();
		if (intent != null
				&& intent.getStringExtra(MainActivity.DETAIL_INFO) != null) {

			String houseId = intent.getStringExtra(MainActivity.DETAIL_INFO);
			mQueue = Volley.newRequestQueue(this);
			mQueue.add(new StringRequest(
					Config.DETAIL_SALES_INFO_URL + houseId,
					new Listener<String>() {

						@Override
						public void onResponse(String json) {

							HouseDetails details = JsonUtils
									.getHouseDetails(json);
							HouseDetailsInfo detailsInfo = details.getData();
							mSalesList = details.getSalesList();

							mCityNameTv.setText(detailsInfo.getName());
							mOpenTimeTv.setText("开盘时间："
									+ detailsInfo.getKpDate());
							mAddressTv.setText(detailsInfo.getAddress());
							mPhoneNumberTv.setText(detailsInfo.getTel());

							mTotalCountTv.setText(detailsInfo.getTotalNum());
							mSelledCountTv.setText(detailsInfo.getSalesNum());
							mYesterdayCountTv.setText(detailsInfo.getCount());

							setTableData(mMonthGraphView,
									details.getMothCount());
							mPieChartView.setTotalCount(details.getBing()
									.getTotalNum());
							mPieChartView.setSelledCount(details.getBing()
									.getSalesNum());
							mPieChartView.setYesterdayCount(details.getBing()
									.getYesterdaySale());

							mListView.setAdapter(new MyAdapter());
							mHandler.sendEmptyMessageDelayed(SUCCESS, 0);

						}
					}, null));
			mQueue.start();

		}

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

		mCityNameTv = (TextView) findViewById(R.id.city_name_tv);
		mOpenTimeTv = (TextView) findViewById(R.id.open_time_tv);
		mAddressTv = (TextView) findViewById(R.id.address_tv);
		mPhoneNumberTv = (TextView) findViewById(R.id.phone_number_tv);

		mTotalCountTv = (TextView) findViewById(R.id.total_count_tv);
		mSelledCountTv = (TextView) findViewById(R.id.selled_count_tv);
		mYesterdayCountTv = (TextView) findViewById(R.id.yesterday_count_tv);

		mSelledInfoTv = (TextView) findViewById(R.id.selled_info_tv);
		mSelledHouseSizeTv = (TextView) findViewById(R.id.selled_house_size_tv);
		mSelledAddressTv = (TextView) findViewById(R.id.selled_address_tv);

		mListView = (ListView) findViewById(R.id.performance_list_view);
		mPieChartView = (CustomCircleView) findViewById(R.id.ping_graph_view);
		initMonthGraphView();
	}

	private void initMonthGraphView() {
		mMonthTableLayout = (LinearLayout) findViewById(R.id.graph_layout);
		mMonthGraphView = new LineGraphView(this, "本月销售变化");
		String[] horizontalLabels = new String[7];
		for (int i = 0; i < 7; i++) {

			horizontalLabels[i] = i * 5 + "日";

		}
		mMonthGraphView.setHorizontalLabels(horizontalLabels);
		mMonthGraphView.getGraphViewStyle().setGridColor(Color.WHITE);
		mMonthGraphView.getGraphViewStyle().setHorizontalLabelsColor(
				Color.WHITE);
		mMonthGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		mMonthGraphView.getGraphViewStyle().setTextSize(12);
		mMonthGraphView.setBackgroundColor(Color.parseColor("#66436EEE"));
		mMonthGraphView.setDrawBackground(true);
		mMonthTableLayout.addView(mMonthGraphView);

	}

	private void setTableData(GraphView graphView, ArrayList<Integer> tableData) {

		GraphViewData[] data = new GraphViewData[7];

		for (int i = 0; i < tableData.size(); i++) {
			data[i] = new GraphViewData(i, tableData.get(i) * 1.0);
		}

		for (int i = 0; i < 7 - tableData.size(); i++) {

			data[tableData.size() + i] = new GraphViewData(
					tableData.size() + i, 0);

		}

		graphView.removeAllSeries();
		graphView.addSeries(new GraphViewSeries(data));

	}

	class ViewHolder {
		TextView nameTv;
		TextView selledTimeTv;
		TextView selledSizeTv;
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mSalesList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mSalesList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			SalesListItem salesListItem = mSalesList.get(position);
			ViewHolder viewHolder = null;

			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = getLayoutInflater().inflate(
						R.layout.performance_list_item, parent, false);

				viewHolder.nameTv = (TextView) convertView
						.findViewById(R.id.name_tv);
				viewHolder.selledSizeTv = (TextView) convertView
						.findViewById(R.id.selled_size_tv);
				viewHolder.selledTimeTv = (TextView) convertView
						.findViewById(R.id.selled_time_tv);

				convertView.setTag(viewHolder);

			}

			viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.nameTv.setText(position + 1 + "  "
					+ salesListItem.getSalesman());
			viewHolder.selledSizeTv.setText(salesListItem.getAcreage());
			viewHolder.selledTimeTv.setText(salesListItem.getSalesTime());

			return convertView;
		}
	}
}
