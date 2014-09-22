package com.puji.salesshowroom;

import it.sephiroth.android.library.widget.AbsHListView;
import it.sephiroth.android.library.widget.AbsHListView.OnScrollListener;
import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.AdapterView.OnItemClickListener;
import it.sephiroth.android.library.widget.AdapterView.OnItemSelectedListener;
import it.sephiroth.android.library.widget.HListView;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.puji.bean.House;
import com.puji.bean.PieChart;
import com.puji.config.Config;
import com.puji.util.DisplayUtils;
import com.puji.util.FormatDataUtil;
import com.puji.util.JsonUtils;
import com.puji.view.CustomCircleView;

/**
 * 
 * @author Kevin
 * 
 */
public class MainActivity extends Activity implements OnItemSelectedListener,
		OnScrollListener, OnItemClickListener {

	/**
	 * 标识item是一个header
	 */
	private static final int HEADER_ITEM = 0;

	/**
	 * 标识item
	 */
	private static final int CONTENT_ITEM = 1;

	/**
	 * 轮播到下一个城市的时间间隔
	 */
	private static final int DURATION = 15000;

	/**
	 * 标识发送的消息类别
	 */
	private static final int SUCCESS = 1;
	private static final int STOP = 2;

	public static final String DETAIL_INFO = "detail_info";

	/**
	 * 当前被轮播到的header项所对应的位置
	 */
	private int mSelection = 0;

	/**
	 * 轮播时用到的计数器
	 */
	int count = 0;

	private CustomAdapter mAdapter;

	/**
	 * 水平方向的ListView
	 */
	private HListView mHListView;
	private CustomCircleView pieChartView;
	private ArrayList<House> mData;
	private HashMap<String, PieChart> pieHashMap;
	private HashMap<String, ArrayList<Integer>> monthTableData;
	private HashMap<String, ArrayList<Integer>> yearTableData;

	private int layoutHeight = 0;
	private int mainLayoutHeight = 0;

	/**
	 * 线性图
	 */
	private LineGraphView mMonthGraphView;
	private LineGraphView mYearGraphView;
	private DisplayUtils mDisplayUtils;
	private LinearLayout mMonthTableLayout;
	private LinearLayout mYearTableLayout;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			SectionIndexer indexer = (SectionIndexer) mAdapter;

			switch (msg.what) {

			case SUCCESS:

				if (count > indexer.getSectionForPosition(mData.size() - 1)) {
					count = 0;
				}

				int position = indexer.getPositionForSection(count++);

				if (position != 0) {
					mHListView
							.smoothScrollToPositionFromLeft(position, 0, 3000);
				} else {
					mHListView.smoothScrollToPositionFromLeft(position, 0, 0);
				}

				mSelection = position;
				updataGraphViewsData();
				mAdapter.notifyDataSetChanged();

				mHandler.sendEmptyMessageDelayed(SUCCESS, DURATION);

				break;

			case STOP:
				break;

			default:
				break;
			}

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDisplayUtils = new DisplayUtils(this);

		RequestQueue mQueue = Volley.newRequestQueue(this);
		StringRequest request = new StringRequest(Config.CITY_SALES_URL,
				new Listener<String>() {

					@Override
					public void onResponse(String json) {
						FormatDataUtil dataUtil = new FormatDataUtil(
								JsonUtils.getHouses(json));
						mData = dataUtil.getHouses();
						pieHashMap = dataUtil.getPieChartData();
						monthTableData = dataUtil.getMonthTableData();
						yearTableData = dataUtil.getYearTableData();
						mHListView.setOnItemClickListener(MainActivity.this);
						mHListView.setAdapter(mAdapter = new CustomAdapter());

						mHandler.sendEmptyMessageDelayed(SUCCESS, 0);
					}
				}, null);
		mQueue.add(request);
		mQueue.start();

		initView();
	}

	/**
	 * 初始化视图
	 */
	@SuppressLint("InflateParams")
	public void initView() {

		pieChartView = (CustomCircleView) findViewById(R.id.pie_chart);

		mHListView = (HListView) findViewById(R.id.horizontal_list_view);
		mHListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mHListView.setSelected(true);
		mHListView.setOnItemSelectedListener(this);
		mHListView.setOnScrollListener(this);

		layoutHeight = mDisplayUtils.measureViewHeight(getLayoutInflater()
				.inflate(R.layout.list_view_item, null).findViewById(
						R.id.layout));
		mainLayoutHeight = (mDisplayUtils.getDisplayHeight() - mDisplayUtils
				.measureViewHeight(findViewById(R.id.top_layout))) * 2 / 3;

		initMonthGraphView();
		initYearGraphView();

	}

	private void initMonthGraphView() {
		mMonthTableLayout = (LinearLayout) findViewById(R.id.table1);
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

	private void initYearGraphView() {
		mYearTableLayout = (LinearLayout) findViewById(R.id.table2);
		String[] horizontalLabels = new String[7];
		for (int i = 0; i < 7; i++) {

			horizontalLabels[i] = i * 2 + "月";

		}
		mYearGraphView = new LineGraphView(this, "本年销售变化");
		mYearGraphView.setHorizontalLabels(horizontalLabels);
		mYearGraphView.getGraphViewStyle().setGridColor(Color.WHITE);
		mYearGraphView.getGraphViewStyle()
				.setHorizontalLabelsColor(Color.WHITE);
		mYearGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		mYearGraphView.getGraphViewStyle().setTextSize(12);
		mYearGraphView.setBackgroundColor(Color.parseColor("#66436EEE"));
		mYearGraphView.setDrawBackground(true);
		mYearTableLayout.addView(mYearGraphView);
	}

	private void updataGraphViewsData() {

		String cityName = mData.get(mSelection).getCityName();

		pieChartView.setTotalCount(pieHashMap.get(cityName).getTotalNum());
		pieChartView.setSelledCount(pieHashMap.get(cityName).getSalesNum());
		pieChartView.setYesterdayCount(pieHashMap.get(cityName)
				.getYesterdaySale());

		if (monthTableData.get(cityName) != null) {
			setTableData(mMonthGraphView, monthTableData.get(cityName));
		}

		if (yearTableData.get(cityName) != null) {
			setTableData(mYearGraphView, yearTableData.get(cityName));
		}

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

	class ItemViewHolder {

		TextView percentTv;
		TextView buildingNameTv;
		TextView openTimeTv;
		TextView totalCountTv;
		TextView selledCountTv;
		TextView yesterdayCountTv;
		LinearLayout layout;

	}

	class HeaderViewHolder {

		TextView cityNameCHTv;
		TextView cityNameENTv;
		ImageView cityIcon;

	}

	class CustomAdapter extends BaseAdapter implements SectionIndexer {

		@Override
		public int getCount() {

			return mData.size();
		}

		@Override
		public Object getItem(int position) {

			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public int getItemViewType(int position) {

			if (mData.get(position).isHeader()) {
				return HEADER_ITEM;
			} else {
				return CONTENT_ITEM;
			}

		}

		@Override
		public int getViewTypeCount() {

			return 2;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			HeaderViewHolder headerViewHolder = null;
			ItemViewHolder itemViewHolder = null;

			if (convertView == null && getItemViewType(position) == HEADER_ITEM) {

				convertView = getLayoutInflater().inflate(
						R.layout.list_view_title_item, parent, false);

				headerViewHolder = new HeaderViewHolder();
				headerViewHolder.cityNameCHTv = (TextView) convertView
						.findViewById(R.id.city_name_ch);
				headerViewHolder.cityNameENTv = (TextView) convertView
						.findViewById(R.id.city_name_en);
				headerViewHolder.cityIcon = (ImageView) convertView
						.findViewById(R.id.city_icon);

				convertView.setTag(headerViewHolder);

			} else if (convertView == null
					&& getItemViewType(position) == CONTENT_ITEM) {

				convertView = getLayoutInflater().inflate(
						R.layout.list_view_item, parent, false);

				itemViewHolder = new ItemViewHolder();
				itemViewHolder.buildingNameTv = (TextView) convertView
						.findViewById(R.id.building_name);
				itemViewHolder.openTimeTv = (TextView) convertView
						.findViewById(R.id.open_time);
				itemViewHolder.percentTv = (TextView) convertView
						.findViewById(R.id.selled_percent);
				itemViewHolder.totalCountTv = (TextView) convertView
						.findViewById(R.id.total_count);
				itemViewHolder.selledCountTv = (TextView) convertView
						.findViewById(R.id.selled_count);
				itemViewHolder.yesterdayCountTv = (TextView) convertView
						.findViewById(R.id.yesterday_count);
				itemViewHolder.layout = (LinearLayout) convertView
						.findViewById(R.id.layout);

				convertView.setTag(itemViewHolder);
			}

			if (getItemViewType(position) == HEADER_ITEM) {

				headerViewHolder = (HeaderViewHolder) convertView.getTag();
				headerViewHolder.cityNameCHTv.setText(mData.get(position)
						.getCityName());
				headerViewHolder.cityNameENTv.setText(mData.get(position)
						.getPinYin());
				if (mSelection == position) {
					headerViewHolder.cityIcon
							.setImageResource(R.drawable.ico04);
				} else {
					headerViewHolder.cityIcon
							.setImageResource(R.drawable.ico05);
				}

			} else if (getItemViewType(position) == CONTENT_ITEM) {

				House building = mData.get(position);

				int height = layoutHeight;
				height = (int) (mainLayoutHeight * building.getRatio() / 100.0);

				height = height > layoutHeight ? height : layoutHeight;

				itemViewHolder = (ItemViewHolder) convertView.getTag();

				LinearLayout.LayoutParams layoutParams = (LayoutParams) itemViewHolder.layout
						.getLayoutParams();
				layoutParams.height = height;

				itemViewHolder.layout.setLayoutParams(layoutParams);

				itemViewHolder.buildingNameTv.setText(building.getName());
				itemViewHolder.openTimeTv.setText(building.getKpDate());

				itemViewHolder.percentTv.setText(building.getRatio() + "%");
				if (building.getIsNew() == 1) {
					Drawable drawable = getResources().getDrawable(
							R.drawable.ico06);
					itemViewHolder.percentTv.setCompoundDrawables(null, null,
							drawable, null);
				} else {
					itemViewHolder.percentTv.setCompoundDrawables(null, null,
							null, null);
				}

				itemViewHolder.selledCountTv.setText(building.getSalesNum()
						+ "");
				itemViewHolder.totalCountTv
						.setText(building.getTotalNum() + "");
				itemViewHolder.yesterdayCountTv.setText(building.getTodaySale()
						+ "");

			}

			return convertView;
		}

		@Override
		public Object[] getSections() {
			return null;
		}

		@Override
		public int getPositionForSection(int sectionIndex) {

			int index = 0;

			for (int i = 0; i < mData.size(); i++) {

				if (mData.get(i).isHeader()) {

					index++;
				}

				if (index - 1 == sectionIndex) {
					return i;
				}
			}
			return mData.size() - 1;
		}

		@Override
		public int getSectionForPosition(int position) {

			int index = 0;
			for (int i = 0; i < mData.size(); i++) {

				if (mData.get(i).isHeader()) {

					index++;
				}

				if (position == i) {
					return index - 1;
				}

			}

			return 0;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		House building = mData.get(position);
		if (building.isHeader()) {

		} else {
			Intent intent = new Intent(MainActivity.this, DetailActivity.class);
			intent.putExtra(DETAIL_INFO, building.getHousesID());
			startActivity(intent);
		}

	}

	@Override
	public void onScrollStateChanged(AbsHListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsHListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

	}

}
