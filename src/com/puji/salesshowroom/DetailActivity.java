package com.puji.salesshowroom;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.puji.bean.HouseDetails;
import com.puji.bean.HouseDetailsInfo;
import com.puji.bean.SalesListItem;
import com.puji.config.Config;
import com.puji.util.DisplayUtils;
import com.puji.util.JsonUtils;
import com.puji.view.CustomCircleView;
import com.puji.view.LinearGraphView;
import com.puji.view.LinearGraphView.GraphViewData;

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

	private ImageView mRankingImg;

	ArrayList<SalesListItem> mSalesList;
	private LinearGraphView mMonthGraphView;
	private CustomCircleView mPieChartView;
	private MyAdapter mAdapter;

	private int mRankings[] = { R.drawable.top1, R.drawable.top2,
			R.drawable.top3, R.drawable.top4, R.drawable.top5 };

	private static final int SUCCESS = 1;
	private int count = 0;
	private int selection = 0;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@SuppressLint("NewApi")
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SUCCESS:

				if (count >= mSalesList.size()) {
					count = 0;
				}

				selection = count;
				SalesListItem salesListItem = mSalesList.get(count);

				mSelledInfoTv.setText(Html.fromHtml(String.format(
						getString(R.string.man_at_time_selled_count),
						salesListItem.getSalesman(), salesListItem.getTime())));

				mSelledAddressTv.setText(String.format(
						getString(R.string.house_number),
						salesListItem.getHouseNumber()));

				mSelledHouseSizeTv.setText(String.format(
						getString(R.string.selled_house_size),
						salesListItem.getAcreage()));
				mRankingImg.setImageResource(mRankings[count % 5]);

				mAdapter.notifyDataSetChanged();

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
							mOpenTimeTv.setText(String.format(
									getString(R.string.open_time),
									detailsInfo.getKpDate()));
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

							mListView.setAdapter(mAdapter = new MyAdapter());
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

		mRankingImg = (ImageView) findViewById(R.id.ranking);

		mListView = (ListView) findViewById(R.id.performance_list_view);
		mPieChartView = (CustomCircleView) findViewById(R.id.ping_graph_view);
		mMonthGraphView = (LinearGraphView) findViewById(R.id.month_sale_talbe);
		initMonthGraphView();
	}

	private void initMonthGraphView() {

		String[] horizontalLabels = new String[7];
		for (int i = 0; i < 7; i++) {

			horizontalLabels[i] = i * 5 + getString(R.string.day);

		}
		mMonthGraphView
				.setGraphTitle(getString(R.string.this_month_sales_change));
		mMonthGraphView.setGraphTitleColor(Color.WHITE);
		mMonthGraphView.setHorizontalLabelCount(horizontalLabels.length);
		mMonthGraphView.setHorizontalLables(horizontalLabels);
		mMonthGraphView.setGraphBackgroundColor(Color.parseColor("#66436EEE"));
		mMonthGraphView.setGridColor(0xffffffff);
		mMonthGraphView.setHorizontalLabelAlign(Paint.Align.CENTER);
		mMonthGraphView.setHorizontalLableColor(Color.WHITE);
		mMonthGraphView.setLineColor(Color.parseColor("#436EEE"));
		mMonthGraphView.setVertialLabelColor(Color.WHITE);
		mMonthGraphView.setPointColor(Color.parseColor("#436EEE"));
		mMonthGraphView.setDrawBackground(true);

	}

	private void setTableData(LinearGraphView graphView,
			ArrayList<Integer> tableData) {

		GraphViewData[] data = new GraphViewData[tableData.size()];

		for (int i = 0; i < tableData.size(); i++) {
			data[i] = new GraphViewData(i, tableData.get(i) * 1.0);
		}

		graphView.setData(data);

	}

	class ViewHolder {
		TextView nameTv;
		TextView selledTimeTv;
		TextView selledSizeTv;
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return mSalesList.size();
		}

		@Override
		public Object getItem(int position) {

			return mSalesList.get(position);
		}

		@Override
		public long getItemId(int position) {

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

			if (selection == position) {
				viewHolder.nameTv.setTextColor(Color.parseColor("#fc7101"));
				viewHolder.selledSizeTv.setTextColor(Color
						.parseColor("#fc7101"));
				viewHolder.selledTimeTv.setTextColor(Color
						.parseColor("#fc7101"));
				convertView.setBackgroundColor(Color.parseColor("#3b3836"));
			} else {
				viewHolder.nameTv.setTextColor(Color.parseColor("#ffffff"));
				viewHolder.selledSizeTv.setTextColor(Color
						.parseColor("#ffffff"));
				viewHolder.selledTimeTv.setTextColor(Color
						.parseColor("#ffffff"));
				convertView.setBackgroundColor(Color.parseColor("#00ffffff"));
			}

			return convertView;
		}
	}
}
