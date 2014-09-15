package com.puji.salesshowroom;

import it.sephiroth.android.library.widget.HListView;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.puji.bean.Building;

public class MainActivity extends Activity {

	private static final int HEADER_ITEM = 0;
	private static final int CONTENT_ITEM = 1;

	private static final int DURATION = 5000;
	private static final int SUCCESS = 1;
	private static final int STOP = 2;

	int count = 0;

	private CustomAdapter mAdapter;

	private HListView mHListView;
	private ArrayList<Building> mData;
	private int layoutHeight = 0;
	private int mainLayoutHeight = 0;

	private LineGraphView mGraphView;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			SectionIndexer indexer = (SectionIndexer) mAdapter;

			switch (msg.what) {

			case SUCCESS:

				if (count > indexer.getSectionForPosition(mData.size() - 1)) {
					count = 0;
				}

				mHListView.setSelection(indexer.getPositionForSection(count++));
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
		initData();
		initView();
	}

	/**
	 * 测量View的高度
	 * 
	 * @param view
	 * @return
	 */
	private int measureLayoutHeight(View view) {

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredHeight();

	}

	/**
	 * 初始化数据
	 */
	public void initData() {

		mData = new ArrayList<>();
		mData.add(new Building("北京", "beijing", 0, 0, 0, true));
		mData.add(new Building("蔷薇国际(二期)", "2014-04", 600, 450, 54, false));
		mData.add(new Building("紫藤庄园", "2014-06", 890, 123, 43, false));
		mData.add(new Building("玫瑰庄园", "2013-12", 877, 876, 54, false));
		mData.add(new Building("景湖城", "2013-11", 8776, 543, 134, false));
		mData.add(new Building("蔷薇国际(一期商业)", "2010-14", 987, 432, 24, false));
		mData.add(new Building("紫藤庄园(一期住宅)", "2014-01", 787, 343, 98, false));

		mData.add(new Building("贵州", "guiZhou", 0, 0, 0, true));
		mData.add(new Building("玫瑰庄园(一期商业)", "2014-02", 534, 432, 134, false));
		mData.add(new Building("蔷薇国际", "2013-07", 6534, 5432, 76, false));
		mData.add(new Building("景湖城", "2012-11", 743, 653, 54, false));

		mData.add(new Building("上海", "shanghai", 0, 0, 0, true));
		mData.add(new Building("景湖城(一期商业)", "2014-9", 789, 43, 3, false));
		mData.add(new Building("蔷薇国际", "2013-12", 54723, 54346, 4, false));
		mData.add(new Building("紫藤庄园", "2014-3", 54387, 5454, 32, false));

		mData.add(new Building("天津", "TianJing", 0, 0, 0, true));
		mData.add(new Building("蔷薇国际(二期)", "2014-04", 600, 450, 54, false));
		mData.add(new Building("紫藤庄园", "2014-06", 890, 123, 43, false));
		mData.add(new Building("玫瑰庄园", "2013-12", 877, 876, 54, false));
		mData.add(new Building("景湖城", "2013-11", 8776, 543, 134, false));
		mData.add(new Building("蔷薇国际(一期商业)", "2010-14", 987, 432, 24, false));
		mData.add(new Building("紫藤庄园(一期住宅)", "2014-01", 787, 343, 98, false));

		mData.add(new Building("广东", "GuangDong", 0, 0, 0, true));
		mData.add(new Building("玫瑰庄园(一期商业)", "2014-02", 534, 432, 134, false));
		mData.add(new Building("蔷薇国际", "2013-07", 6534, 5432, 76, false));
		mData.add(new Building("景湖城", "2012-11", 743, 653, 54, false));

		mData.add(new Building("四川", "siChuan", 0, 0, 0, true));
		mData.add(new Building("景湖城(一期商业)", "2014-9", 789, 43, 3, false));
		mData.add(new Building("蔷薇国际", "2013-12", 54723, 54346, 4, false));
		mData.add(new Building("紫藤庄园", "2014-3", 54387, 5454, 32, false));

	}

	/**
	 * 初始化视图
	 */
	public void initView() {
		mHListView = (HListView) findViewById(R.id.horizontal_list_view);
		mHListView.setAdapter(mAdapter = new CustomAdapter());
		mHandler.sendEmptyMessageDelayed(SUCCESS, DURATION);

		layoutHeight = measureLayoutHeight(getLayoutInflater().inflate(
				R.layout.list_view_item, null).findViewById(R.id.layout));
		mainLayoutHeight = (getWindowManager().getDefaultDisplay().getHeight() - measureLayoutHeight(findViewById(R.id.top_layout))) * 2 / 3;

		initTable1();
		initTable2();

	}

	/**
	 * 初始化表格1
	 */
	private void initTable1() {
		LinearLayout mLayout = (LinearLayout) findViewById(R.id.table1);
		mGraphView = new LineGraphView(this, "本年销售变化");
		mGraphView
				.setHorizontalLabels(new String[] { "1", "2", "3", "4", "5" });
		mGraphView.setVerticalLabels(new String[] { "5", "", "4", "", "3", "",
				"2", "", "1" });

		mGraphView.getGraphViewStyle().setGridColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setTextSize(28);
		mGraphView.getGraphViewStyle().setNumHorizontalLabels(5);
		mGraphView.getGraphViewStyle().setNumVerticalLabels(4);
		mGraphView.getGraphViewStyle().setVerticalLabelsWidth(30);
		mGraphView.setBackgroundColor(Color.parseColor("#66436EEE"));
		mGraphView.setDrawBackground(true);
		// mGraphView.setDrawDataPoints(true);

		GraphViewData[] data = new GraphViewData[5];
		data[0] = new GraphViewData(0, 2);
		data[1] = new GraphViewData(1, 5);
		data[2] = new GraphViewData(2, 3);
		data[3] = new GraphViewData(3, 1);
		data[4] = new GraphViewData(4, 4);
		mGraphView.addSeries(new GraphViewSeries(data));
		mLayout.addView(mGraphView);
	}

	/**
	 * 初始化表格2
	 */
	private void initTable2() {

		LinearLayout mLayout2 = (LinearLayout) findViewById(R.id.table2);

		mGraphView = new LineGraphView(this, "本月销售变化");
		mGraphView
				.setHorizontalLabels(new String[] { "1", "2", "3", "4", "5" });
		mGraphView.setVerticalLabels(new String[] { "5", "", "4", "", "3", "",
				"2", "", "1" });

		mGraphView.getGraphViewStyle().setGridColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		mGraphView.getGraphViewStyle().setTextSize(28);
		mGraphView.getGraphViewStyle().setNumHorizontalLabels(5);
		mGraphView.getGraphViewStyle().setNumVerticalLabels(4);
		mGraphView.getGraphViewStyle().setVerticalLabelsWidth(30);
		mGraphView.setBackgroundColor(Color.parseColor("#66436EEE"));
		mGraphView.setDrawBackground(true);
		mGraphView.getGraphViewStyle().setLegendSpacing(10);
		// mGraphView.setDrawDataPoints(true);

		GraphViewData[] data2 = new GraphViewData[5];
		data2[0] = new GraphViewData(0, 2);
		data2[1] = new GraphViewData(1, 5);
		data2[2] = new GraphViewData(2, 3);
		data2[3] = new GraphViewData(3, 1);
		data2[4] = new GraphViewData(4, 4);
		mGraphView.addSeries(new GraphViewSeries(data2));
		mLayout2.addView(mGraphView);

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

	}

	class CustomAdapter extends BaseAdapter implements SectionIndexer {

		@Override
		public int getCount() {

			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
						.getBuildlingName());
				headerViewHolder.cityNameENTv.setText(mData.get(position)
						.getOpenTime());

			} else if (getItemViewType(position) == CONTENT_ITEM) {

				Building building = mData.get(position);

				int height = layoutHeight;
				if (building.getTotalCount() != 0) {
					height = (int) (mainLayoutHeight * (building
							.getSelledCount() / (float) building
							.getTotalCount()));
				}

				height = height > layoutHeight ? height : layoutHeight;

				itemViewHolder = (ItemViewHolder) convertView.getTag();

				LinearLayout.LayoutParams layoutParams = (LayoutParams) itemViewHolder.layout
						.getLayoutParams();
				layoutParams.height = height;

				itemViewHolder.layout.setLayoutParams(layoutParams);

				itemViewHolder.buildingNameTv.setText(building
						.getBuildlingName());
				itemViewHolder.openTimeTv.setText(building.getOpenTime());

				if (building.getTotalCount() != 0) {

					float percent = (int) ((building.getSelledCount() / (building
							.getTotalCount() * 1.0)) * 100);
					itemViewHolder.percentTv.setText(percent + "%");
				}

				itemViewHolder.selledCountTv.setText(building.getSelledCount()
						+ "");
				itemViewHolder.totalCountTv.setText(building.getTotalCount()
						+ "");
				itemViewHolder.yesterdayCountTv.setText(building
						.getYesterdaySelledCount() + "");

			}

			return convertView;
		}

		@Override
		public Object[] getSections() {
			// TODO Auto-generated method stub
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

}
