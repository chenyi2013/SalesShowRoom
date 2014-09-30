package com.puji.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * 
 * @author Kevin Chen
 * @date 2014/9/28
 * @email chenyi_de_email@163.com
 * @QQ 252019161
 */

public class LinearGraphView extends View {

	/**
	 * 图形标题
	 */
	private String mGraphTitle;

	/**
	 * 图形标题颜色
	 */
	private int mGraphTitleColor = Color.BLACK;

	/**
	 * 图形标题字体大小
	 */
	private int mGraphTitleTextSize = convertSpToPxValue(18);

	/**
	 * 水平标签颜色
	 */
	private int mHorizontalLableColor = Color.BLACK;

	/**
	 * 水平标签字体大小
	 */
	private int mHorzontalLabelTextSize = convertSpToPxValue(12);

	/**
	 * 垂直标签颜色
	 */
	private int mVertialLabelColor = Color.BLACK;

	/**
	 * 垂直标签字体大小
	 */
	private int mVertialLabelTextSize = convertSpToPxValue(12);

	/**
	 * 水平刻度数
	 */
	private int mHorizontalLabelCount = 7;

	/**
	 * 垂直刻度数
	 */
	private int mVertialLabelCount = 6;

	/**
	 * 网格颜色
	 */
	private int mGridColor = Color.BLACK;

	/**
	 * 网格厚度
	 */
	private int mGridWeight = getPxValue(1);

	/**
	 * 座标点颜色
	 */
	private int mPointColor = Color.RED;

	/**
	 * 座标点处小圆的半径
	 */
	private int mPointRadius = getPxValue(3);

	/**
	 * 是否绘制座标点处的小圆
	 */
	private boolean isDrawPoint = true;

	/**
	 * 是否填充所画图形的背景
	 */
	private boolean isDrawBackground = true;

	/**
	 * 所画图形的背景颜色
	 */
	private int mGraphBackgroundColor = Color.RED;

	/**
	 * 所要绘制图形的数据源
	 */
	private GraphViewData[] mData;

	/**
	 * 图形标题与图形之间的间隔距离
	 */
	private int mSpaceingOfTitleWithGraph = getPxValue(10);

	/**
	 * 垂直标签与图形之间的间隔距离
	 */
	private int mSpaceingOfVerticalLabelWithGraph = getPxValue(10);

	/**
	 * 水平标签与图形之间的间隔距离
	 */
	private int mSpaceingOfHorizontalLabelWithGraph = getPxValue(5);

	/**
	 * 绘制网格的笔刷
	 */
	private Paint mGridPaint = new Paint();

	/**
	 * 绘制文字的笔刷
	 */
	private Paint mTextPaint = new Paint();

	/**
	 * 水平标签文字的对齐方式
	 */
	private Align mHorizontalLabelAlign = Align.LEFT;

	/**
	 * 垂直标签的对齐方式
	 */
	private Align mVerticalLabelAlign = Align.LEFT;

	/**
	 * 图形标题的对齐方式
	 */
	private Align mGraphTitleAlign = Align.CENTER;

	/**
	 * 水平标签
	 */
	private String[] mHorizontalLables;

	/**
	 * 线性图的曲线厚度
	 */
	private int mLineWeight = getPxValue(1);

	/**
	 * 线性图的曲线颜色
	 */
	private int mLineColor = 0xff000000;

	/**
	 * 得到水平标签字符串数组
	 * 
	 * @return
	 */
	public String[] getHorizontalLables() {
		return mHorizontalLables;
	}

	/**
	 * 设置水平标签字符串数组
	 * 
	 * @param mHorizontalLables
	 */
	public void setHorizontalLables(String[] mHorizontalLables) {
		this.mHorizontalLables = mHorizontalLables;
		invalidate();
	}

	/**
	 * 得到图形标题
	 * 
	 * @return
	 */
	public String getGraphTitle() {
		return mGraphTitle;
	}

	/**
	 * 设置图形标题
	 * 
	 * @param mGraphTitle
	 */
	public void setGraphTitle(String mGraphTitle) {
		this.mGraphTitle = mGraphTitle;
		invalidate();
	}

	/**
	 * 得到图形标题颜色
	 * 
	 * @return
	 */
	public int getGraphTitleColor() {
		return mGraphTitleColor;
	}

	/**
	 * 设置图形标题颜色
	 * 
	 * @param mGraphTitleColor
	 */
	public void setGraphTitleColor(int mGraphTitleColor) {
		this.mGraphTitleColor = mGraphTitleColor;
		invalidate();
	}

	/**
	 * 得到图形标题字体大小
	 * 
	 * @return
	 */
	public int getGraphTitleTextSize() {
		return mGraphTitleTextSize;
	}

	/**
	 * 设置图形标题字体大小
	 * 
	 * @param mGraphTitleTextSize
	 */
	public void setGraphTitleTextSize(int mGraphTitleTextSize) {
		this.mGraphTitleTextSize = convertSpToPxValue(mGraphTitleTextSize);
		invalidate();
	}

	/**
	 * 得到水平标签字体颜色
	 * 
	 * @return
	 */
	public int getHorizontalLableColor() {
		return mHorizontalLableColor;
	}

	/**
	 * 设置水平标签字体颜色
	 * 
	 * @param mHorzontalLableColor
	 */
	public void setHorizontalLableColor(int mHorzontalLableColor) {
		this.mHorizontalLableColor = mHorzontalLableColor;
		invalidate();
	}

	/**
	 * 得到水平标签字体大小
	 * 
	 * @return
	 */
	public int getHorzontalLabelTextSize() {
		return mHorzontalLabelTextSize;
	}

	/**
	 * 设置水平标签字体大小
	 * 
	 * @param mHorzontalLabelTextSize
	 */
	public void setHorzontalLabelTextSize(int mHorzontalLabelTextSize) {
		this.mHorzontalLabelTextSize = convertSpToPxValue(mHorzontalLabelTextSize);
		invalidate();
	}

	/**
	 * 得到垂直标签字体颜色
	 * 
	 * @return
	 */
	public int getVertialLabelColor() {
		return mVertialLabelColor;
	}

	/**
	 * 设置垂直标签字体颜色
	 * 
	 * @param mVertialLabelColor
	 */
	public void setVertialLabelColor(int mVertialLabelColor) {
		this.mVertialLabelColor = mVertialLabelColor;
		invalidate();
	}

	/**
	 * 得到垂直标签字体大小
	 * 
	 * @return
	 */
	public int getVertialLabelTextSize() {
		return mVertialLabelTextSize;
	}

	/**
	 * 设置垂直标签字体大小
	 * 
	 * @param mVertialLabelTextSize
	 */
	public void setVertialLabelTextSize(int mVertialLabelTextSize) {
		this.mVertialLabelTextSize = convertSpToPxValue(mVertialLabelTextSize);
		invalidate();
	}

	/**
	 * 得到水平标签个数
	 * 
	 * @return
	 */
	public int getHorizontalLabelCount() {
		return mHorizontalLabelCount;
	}

	/**
	 * 设置水平标签个数
	 * 
	 * @param mHorizontalLabelCount
	 */
	public void setHorizontalLabelCount(int mHorizontalLabelCount) {
		this.mHorizontalLabelCount = mHorizontalLabelCount;
		invalidate();
	}

	/**
	 * 得到垂直标签个数
	 * 
	 * @return
	 */
	public int getVertialLabelCount() {
		return mVertialLabelCount;
	}

	/**
	 * 设置垂直标签个数
	 * 
	 * @param mVertialLabelCount
	 */
	public void setVertialLabelCount(int mVertialLabelCount) {
		this.mVertialLabelCount = mVertialLabelCount;
	}

	/**
	 * 得到所画曲线图的线的厚度
	 * 
	 * @return
	 */
	public int getLineWeight() {
		return mLineWeight;
	}

	/**
	 * 设置所画曲线图的线的厚度
	 * 
	 * @param mLineWeight
	 */
	public void setLineWeight(int mLineWeight) {
		this.mLineWeight = mLineWeight;
		invalidate();
	}

	/**
	 * 得到所画线性图的线的颜色
	 * 
	 * @return
	 */
	public int getLineColor() {
		return mLineColor;
	}

	/**
	 * 设置所画线性图的线的颜色
	 * 
	 * @param mLineColor
	 */
	public void setLineColor(int mLineColor) {
		this.mLineColor = mLineColor;
		invalidate();
	}

	/**
	 * 得到网格颜色
	 * 
	 * @return
	 */
	public int getGridColor() {
		return mGridColor;
	}

	/**
	 * 设置网络颜色
	 * 
	 * @param mGridColor
	 */
	public void setGridColor(int mGridColor) {
		this.mGridColor = mGridColor;
		invalidate();
	}

	/**
	 * 得到网络厚度
	 * 
	 * @return
	 */
	public int getGridWeight() {
		return mGridWeight;
	}

	/**
	 * 设置网络厚度
	 * 
	 * @param mGridWeight
	 */
	public void setGridWeight(int mGridWeight) {
		this.mGridWeight = getPxValue(mGridWeight);
		invalidate();
	}

	/**
	 * 得到座标点小圆的填充颜色
	 * 
	 * @return
	 */
	public int getPointColor() {
		return mPointColor;
	}

	/**
	 * 设置座标点小圆的填充颜色
	 * 
	 * @param mPointColor
	 */
	public void setPointColor(int mPointColor) {
		this.mPointColor = mPointColor;
		invalidate();
	}

	/**
	 * 得到座标点处小圆的半径
	 * 
	 * @return
	 */
	public int getPointRadius() {
		return mPointRadius;
	}

	/**
	 * 设置座标点处小圆的半径
	 * 
	 * @param mPointRadius
	 */
	public void setPointRadius(int mPointRadius) {
		this.mPointRadius = getPxValue(mPointRadius);
		invalidate();
	}

	/**
	 * 得到是否绘制座标点处的小圆，true代表绘制，false代表不绘制
	 * 
	 * @return
	 */
	public boolean isDrawPoint() {
		return isDrawPoint;
	}

	/**
	 * 设置是否绘制座标点处的小圆，true代表绘制，false代表不绘制
	 * 
	 * @param isDrawPoint
	 */
	public void setDrawPoint(boolean isDrawPoint) {
		this.isDrawPoint = isDrawPoint;
		invalidate();
	}

	/**
	 * 得到是否绘制图形背景，true代表绘制，false代表不绘制
	 * 
	 * @return
	 */
	public boolean isDrawBackground() {
		return isDrawBackground;
	}

	/**
	 * 设置是否绘制图形背景，true代表绘制，false代表不绘制
	 * 
	 * @param isDrawBackground
	 */
	public void setDrawBackground(boolean isDrawBackground) {
		this.isDrawBackground = isDrawBackground;
		invalidate();
	}

	/**
	 * 得到图形背景颜色
	 * 
	 * @return
	 */
	public int getGraphBackgroundColor() {
		return mGraphBackgroundColor;
	}

	/**
	 * 设置图形背景颜色
	 * 
	 * @param mGraphBackgroundColor
	 */
	public void setGraphBackgroundColor(int mGraphBackgroundColor) {
		this.mGraphBackgroundColor = mGraphBackgroundColor;
		invalidate();
	}

	/**
	 * 得到所要绘制图形的数据源
	 * 
	 * @return
	 */
	public GraphViewData[] getData() {
		return mData;
	}

	/**
	 * 设置所要绘制图形的数据源
	 * 
	 * @param mData
	 */
	public void setData(GraphViewData[] mData) {
		this.mData = mData;
		invalidate();
	}

	/**
	 * 得到图形与图形标题之间的间隔距离
	 * 
	 * @return
	 */
	public int getSpaceingOfTitleWithGraph() {
		return mSpaceingOfTitleWithGraph;
	}

	/**
	 * 设置图形与图形标题之间的间隔距离
	 * 
	 * @param mSpaceingOfTitleWithGraph
	 */
	public void setSpaceingOfTitleWithGraph(int mSpaceingOfTitleWithGraph) {
		this.mSpaceingOfTitleWithGraph = mSpaceingOfTitleWithGraph;
		invalidate();
	}

	/**
	 * 得到图形与垂直标签的间隔距离
	 * 
	 * @return
	 */
	public int getSpaceingOfVerticalLabelWithGraph() {
		return mSpaceingOfVerticalLabelWithGraph;
	}

	/**
	 * 设置图形与垂直标签的间隔距离
	 * 
	 * @param mSpaceingOfVerticalLabelWithGraph
	 */
	public void setSpaceingOfVerticalLabelWithGraph(
			int mSpaceingOfVerticalLabelWithGraph) {
		this.mSpaceingOfVerticalLabelWithGraph = mSpaceingOfVerticalLabelWithGraph;
		invalidate();
	}

	/**
	 * 得到图形与水平标签的间隔距离
	 * 
	 * @return
	 */
	public int getSpaceingOfHorizontalLabelWithGraph() {
		return mSpaceingOfHorizontalLabelWithGraph;
	}

	/**
	 * 设置图形与水平标签的间隔距离
	 * 
	 * @param mSpaceingOfHorizontalLabelWithGraph
	 */
	public void setSpaceingOfHorizontalLabelWithGraph(
			int mSpaceingOfHorizontalLabelWithGraph) {
		this.mSpaceingOfHorizontalLabelWithGraph = mSpaceingOfHorizontalLabelWithGraph;
		invalidate();
	}

	/**
	 * 得到水平标签文字的对齐方式
	 * 
	 * @return
	 */
	public Align getHorizontalLabelAlign() {
		return mHorizontalLabelAlign;
	}

	/**
	 * 设置水平标签文字的对齐方式
	 * 
	 * @param mHorizontalLabelAlign
	 */
	public void setHorizontalLabelAlign(Align mHorizontalLabelAlign) {
		this.mHorizontalLabelAlign = mHorizontalLabelAlign;
		invalidate();
	}

	/**
	 * 得到垂直标签文字的对齐方式
	 * 
	 * @return
	 */
	public Align getVerticalLabelAlign() {
		return mVerticalLabelAlign;
	}

	/**
	 * 设置垂直标签文字的对齐方式
	 * 
	 * @param mVerticalLabelAlign
	 */
	public void setVerticalLabelAlign(Align mVerticalLabelAlign) {
		this.mVerticalLabelAlign = mVerticalLabelAlign;
		invalidate();
	}

	/**
	 * 得到图形文字标题的对齐方式
	 * 
	 * @return
	 */
	public Align getGraphTitleAlign() {
		return mGraphTitleAlign;
	}

	/**
	 * 设置图形文字标题的对齐方式
	 * 
	 * @param mGraphTitleAlign
	 */
	public void setGraphTitleAlign(Align mGraphTitleAlign) {
		this.mGraphTitleAlign = mGraphTitleAlign;
		invalidate();
	}

	public LinearGraphView(Context context) {
		super(context);

	}

	public LinearGraphView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public LinearGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (mData == null) {
			return;
		}

		// 得到给出的数据源中在Y方向的最大值
		double maxY = getMaxYValue();
		// 得到座标轴在Y方向两个刻度之间所表示的数值大小

		double perY = maxY / (mVertialLabelCount-1);

		int startX = getHorizontalLabelWidth(perY, mVertialLabelTextSize)
				+ mSpaceingOfVerticalLabelWithGraph + getPaddingLeft();
		int startY = getFontHeight(mGraphTitleTextSize)
				+ mSpaceingOfTitleWithGraph + getPaddingTop();

		int validWidth = getWidth() - startX - getPaddingRight()
				- mSpaceingOfVerticalLabelWithGraph;
		int validHeight = getHeight() - startY
				- mSpaceingOfHorizontalLabelWithGraph
				- getFontHeight(mHorzontalLabelTextSize) - getPaddingBottom();

		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(mHorzontalLabelTextSize);
		mTextPaint.setColor(mHorizontalLableColor);

		mGridPaint.setAntiAlias(true);
		mGridPaint.setColor(mGridColor);
		mGridPaint.setStyle(Paint.Style.STROKE);
		mGridPaint.setStrokeWidth(mGridWeight);

		// 绘制图形标题
		if (mGraphTitle != null) {
			mTextPaint.setTextAlign(mGraphTitleAlign);
			mTextPaint.setTextSize(mGraphTitleTextSize);
			mTextPaint.setColor(mGraphTitleColor);
			canvas.drawText(mGraphTitle, startX + validWidth / 2,
					getPaddingTop() + getFontHeight(mGraphTitleTextSize),
					mTextPaint);
		}

		// 开始画水平座标线
		for (int i = 0; i < mHorizontalLabelCount - 1; i++) {

			canvas.drawLine(startX + i * validWidth
					/ (mHorizontalLabelCount - 1), startY, startX + i
					* validWidth / (mHorizontalLabelCount - 1), startY
					+ validHeight, mGridPaint);

		}
		canvas.drawLine(startX + validWidth, startY, startX + validWidth,
				startY + validHeight, mGridPaint);

		// 开始画水平标签
		mTextPaint.setTextAlign(mHorizontalLabelAlign);
		mTextPaint.setTextSize(mHorzontalLabelTextSize);
		mTextPaint.setColor(mHorizontalLableColor);
		if (mHorizontalLables != null) {
			for (int i = 0; i < mHorizontalLables.length; i++) {
				canvas.drawText(mHorizontalLables[i], startX + i * validWidth
						/ (mHorizontalLabelCount - 1), startY + validHeight
						+ mSpaceingOfHorizontalLabelWithGraph
						+ getFontHeight(mHorzontalLabelTextSize), mTextPaint);
			}
		} else {
			for (int i = 0; i < mHorizontalLabelCount; i++) {
				canvas.drawText("" + i, startX + i * validWidth
						/ (mHorizontalLabelCount - 1), startY + validHeight
						+ mSpaceingOfHorizontalLabelWithGraph
						+ getFontHeight(mHorzontalLabelTextSize), mTextPaint);
			}
		}

		// 开始画垂直座标线和垂直标签
		mTextPaint.setTextAlign(mVerticalLabelAlign);
		mTextPaint.setTextSize(mVertialLabelTextSize);
		mTextPaint.setColor(mVertialLabelColor);

		for (int i = 0; i <= mVertialLabelCount - 1; i++) {

			canvas.drawLine(startX, startY + i * validHeight
					/ (mVertialLabelCount - 1), startX + validWidth, startY + i
					* validHeight / (mVertialLabelCount - 1), mGridPaint);
			canvas.drawText(""
					+ formatYValue(perY * (mVertialLabelCount - 1 - i)),
					getPaddingLeft(), startY + i * validHeight
							/ (mVertialLabelCount - 1), mTextPaint);

		}

		// 根据给出的数据源绘制图形背景
		if (isDrawBackground) {
			mGridPaint.setColor(mGraphBackgroundColor);
			mGridPaint.setStyle(Paint.Style.FILL);
			Path path = new Path();
			path.moveTo(startX, startY + validHeight);

			for (int i = 1; i < mData.length; i++) {

				path.lineTo(startX + (i - 1) * validWidth
						/ (mHorizontalLabelCount - 1), (float) (startY
						+ validHeight - validHeight * (mData[i - 1].y / maxY)));

			}

			path.lineTo(startX + (mData.length - 1) * validWidth
					/ (mHorizontalLabelCount - 1), (float) (startY
					+ validHeight - validHeight
					* (mData[mData.length - 1].y / maxY)));
			path.lineTo(startX + (mData.length - 1) * validWidth
					/ (mHorizontalLabelCount - 1), startY + validHeight);
			canvas.drawPath(path, mGridPaint);
		}

		// 根据给出的数据源绘制图形
		mGridPaint.setColor(mLineColor);
		mGridPaint.setStrokeWidth(mLineWeight);
		for (int i = 1; i < mData.length; i++) {

			canvas.drawLine(startX + (i - 1) * validWidth
					/ (mHorizontalLabelCount - 1), (float) (startY
					+ validHeight - validHeight * (mData[i - 1].y / maxY)),
					startX + i * validWidth / (mHorizontalLabelCount - 1),
					(float) (startY + validHeight - validHeight
							* (mData[i].y / maxY)), mGridPaint);

		}

		// 绘制座标点处的小圆

		if (isDrawPoint) {
			mGridPaint.setColor(mPointColor);
			mGridPaint.setStyle(Paint.Style.FILL);
			for (int i = 0; i < mData.length; i++) {

				canvas.drawCircle(startX + (i) * validWidth
						/ (mHorizontalLabelCount - 1), (float) (startY
						+ validHeight - validHeight * (mData[i].y / maxY)),
						mPointRadius, mGridPaint);

			}

		}

		mGridPaint.reset();
		mTextPaint.reset();
		canvas.save();
	}

	/**
	 * 将double的数据格式化成带有4位有效小数的数据并以字符串形式返回
	 * 
	 * @param d
	 * @return
	 */
	private String formatYValue(double d) {
		return (long) (Math.round(d * 10000)) / 10000.0 + "";
	}

	/**
	 * 得到数据源中在Y方向的最大数据
	 * 
	 * @return
	 */
	private double getMaxYValue() {

		double max = mData[0].y;

		for (int i = 0; i < mData.length; i++) {

			max = max > mData[i].y ? max : mData[i].y;
		}

		return max;
	}

	/**
	 * 得到垂直标签所占用的最大宽度
	 * 
	 * @param perY
	 * @param fontSize
	 * @return
	 */
	private int getHorizontalLabelWidth(double perY, int fontSize) {
		int length = 0;
		StringBuffer str = new StringBuffer();
		String ss = null;
		for (int i = 0; i < mData.length - 1; i++) {

			ss = formatYValue(perY * (mData.length - i));
			if (ss.length() > length) {
				str.delete(0, str.length());
				str.append(ss);
			}

		}

		return getFontWidth(fontSize, str.toString());
	}

	/**
	 * 得到字体高度
	 * 
	 * @param fontSize
	 * @return
	 */
	private int getFontHeight(float fontSize) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		Rect rect = new Rect();
		paint.getTextBounds("0000", 0, "0000".length(), rect);
		return rect.height();

	}

	/**
	 * 得到文字的宽度
	 * 
	 * @return
	 */
	private int getFontWidth(float fontSize, String str) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		return (int) paint.measureText(str);
	}

	/**
	 * 将以dp为单位表示的值转化成以px为单位的值
	 * 
	 * @param dpValue
	 * @return
	 */
	private int getPxValue(int dpValue) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpValue, metrics);
	}

	/**
	 * 将以sp为单位表示的值转化成以px为单位的值
	 * 
	 * @param spValue
	 * @return
	 */
	private int convertSpToPxValue(int spValue) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spValue, metrics);
	}

	public static class GraphViewData {

		double x;
		double y;

		public GraphViewData(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

	}

}
