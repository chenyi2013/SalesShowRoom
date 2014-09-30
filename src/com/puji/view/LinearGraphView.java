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
	 * ͼ�α���
	 */
	private String mGraphTitle;

	/**
	 * ͼ�α�����ɫ
	 */
	private int mGraphTitleColor = Color.BLACK;

	/**
	 * ͼ�α��������С
	 */
	private int mGraphTitleTextSize = convertSpToPxValue(18);

	/**
	 * ˮƽ��ǩ��ɫ
	 */
	private int mHorizontalLableColor = Color.BLACK;

	/**
	 * ˮƽ��ǩ�����С
	 */
	private int mHorzontalLabelTextSize = convertSpToPxValue(12);

	/**
	 * ��ֱ��ǩ��ɫ
	 */
	private int mVertialLabelColor = Color.BLACK;

	/**
	 * ��ֱ��ǩ�����С
	 */
	private int mVertialLabelTextSize = convertSpToPxValue(12);

	/**
	 * ˮƽ�̶���
	 */
	private int mHorizontalLabelCount = 7;

	/**
	 * ��ֱ�̶���
	 */
	private int mVertialLabelCount = 6;

	/**
	 * ������ɫ
	 */
	private int mGridColor = Color.BLACK;

	/**
	 * ������
	 */
	private int mGridWeight = getPxValue(1);

	/**
	 * �������ɫ
	 */
	private int mPointColor = Color.RED;

	/**
	 * ����㴦СԲ�İ뾶
	 */
	private int mPointRadius = getPxValue(3);

	/**
	 * �Ƿ��������㴦��СԲ
	 */
	private boolean isDrawPoint = true;

	/**
	 * �Ƿ��������ͼ�εı���
	 */
	private boolean isDrawBackground = true;

	/**
	 * ����ͼ�εı�����ɫ
	 */
	private int mGraphBackgroundColor = Color.RED;

	/**
	 * ��Ҫ����ͼ�ε�����Դ
	 */
	private GraphViewData[] mData;

	/**
	 * ͼ�α�����ͼ��֮��ļ������
	 */
	private int mSpaceingOfTitleWithGraph = getPxValue(10);

	/**
	 * ��ֱ��ǩ��ͼ��֮��ļ������
	 */
	private int mSpaceingOfVerticalLabelWithGraph = getPxValue(10);

	/**
	 * ˮƽ��ǩ��ͼ��֮��ļ������
	 */
	private int mSpaceingOfHorizontalLabelWithGraph = getPxValue(5);

	/**
	 * ��������ı�ˢ
	 */
	private Paint mGridPaint = new Paint();

	/**
	 * �������ֵı�ˢ
	 */
	private Paint mTextPaint = new Paint();

	/**
	 * ˮƽ��ǩ���ֵĶ��뷽ʽ
	 */
	private Align mHorizontalLabelAlign = Align.LEFT;

	/**
	 * ��ֱ��ǩ�Ķ��뷽ʽ
	 */
	private Align mVerticalLabelAlign = Align.LEFT;

	/**
	 * ͼ�α���Ķ��뷽ʽ
	 */
	private Align mGraphTitleAlign = Align.CENTER;

	/**
	 * ˮƽ��ǩ
	 */
	private String[] mHorizontalLables;

	/**
	 * ����ͼ�����ߺ��
	 */
	private int mLineWeight = getPxValue(1);

	/**
	 * ����ͼ��������ɫ
	 */
	private int mLineColor = 0xff000000;

	/**
	 * �õ�ˮƽ��ǩ�ַ�������
	 * 
	 * @return
	 */
	public String[] getHorizontalLables() {
		return mHorizontalLables;
	}

	/**
	 * ����ˮƽ��ǩ�ַ�������
	 * 
	 * @param mHorizontalLables
	 */
	public void setHorizontalLables(String[] mHorizontalLables) {
		this.mHorizontalLables = mHorizontalLables;
		invalidate();
	}

	/**
	 * �õ�ͼ�α���
	 * 
	 * @return
	 */
	public String getGraphTitle() {
		return mGraphTitle;
	}

	/**
	 * ����ͼ�α���
	 * 
	 * @param mGraphTitle
	 */
	public void setGraphTitle(String mGraphTitle) {
		this.mGraphTitle = mGraphTitle;
		invalidate();
	}

	/**
	 * �õ�ͼ�α�����ɫ
	 * 
	 * @return
	 */
	public int getGraphTitleColor() {
		return mGraphTitleColor;
	}

	/**
	 * ����ͼ�α�����ɫ
	 * 
	 * @param mGraphTitleColor
	 */
	public void setGraphTitleColor(int mGraphTitleColor) {
		this.mGraphTitleColor = mGraphTitleColor;
		invalidate();
	}

	/**
	 * �õ�ͼ�α��������С
	 * 
	 * @return
	 */
	public int getGraphTitleTextSize() {
		return mGraphTitleTextSize;
	}

	/**
	 * ����ͼ�α��������С
	 * 
	 * @param mGraphTitleTextSize
	 */
	public void setGraphTitleTextSize(int mGraphTitleTextSize) {
		this.mGraphTitleTextSize = convertSpToPxValue(mGraphTitleTextSize);
		invalidate();
	}

	/**
	 * �õ�ˮƽ��ǩ������ɫ
	 * 
	 * @return
	 */
	public int getHorizontalLableColor() {
		return mHorizontalLableColor;
	}

	/**
	 * ����ˮƽ��ǩ������ɫ
	 * 
	 * @param mHorzontalLableColor
	 */
	public void setHorizontalLableColor(int mHorzontalLableColor) {
		this.mHorizontalLableColor = mHorzontalLableColor;
		invalidate();
	}

	/**
	 * �õ�ˮƽ��ǩ�����С
	 * 
	 * @return
	 */
	public int getHorzontalLabelTextSize() {
		return mHorzontalLabelTextSize;
	}

	/**
	 * ����ˮƽ��ǩ�����С
	 * 
	 * @param mHorzontalLabelTextSize
	 */
	public void setHorzontalLabelTextSize(int mHorzontalLabelTextSize) {
		this.mHorzontalLabelTextSize = convertSpToPxValue(mHorzontalLabelTextSize);
		invalidate();
	}

	/**
	 * �õ���ֱ��ǩ������ɫ
	 * 
	 * @return
	 */
	public int getVertialLabelColor() {
		return mVertialLabelColor;
	}

	/**
	 * ���ô�ֱ��ǩ������ɫ
	 * 
	 * @param mVertialLabelColor
	 */
	public void setVertialLabelColor(int mVertialLabelColor) {
		this.mVertialLabelColor = mVertialLabelColor;
		invalidate();
	}

	/**
	 * �õ���ֱ��ǩ�����С
	 * 
	 * @return
	 */
	public int getVertialLabelTextSize() {
		return mVertialLabelTextSize;
	}

	/**
	 * ���ô�ֱ��ǩ�����С
	 * 
	 * @param mVertialLabelTextSize
	 */
	public void setVertialLabelTextSize(int mVertialLabelTextSize) {
		this.mVertialLabelTextSize = convertSpToPxValue(mVertialLabelTextSize);
		invalidate();
	}

	/**
	 * �õ�ˮƽ��ǩ����
	 * 
	 * @return
	 */
	public int getHorizontalLabelCount() {
		return mHorizontalLabelCount;
	}

	/**
	 * ����ˮƽ��ǩ����
	 * 
	 * @param mHorizontalLabelCount
	 */
	public void setHorizontalLabelCount(int mHorizontalLabelCount) {
		this.mHorizontalLabelCount = mHorizontalLabelCount;
		invalidate();
	}

	/**
	 * �õ���ֱ��ǩ����
	 * 
	 * @return
	 */
	public int getVertialLabelCount() {
		return mVertialLabelCount;
	}

	/**
	 * ���ô�ֱ��ǩ����
	 * 
	 * @param mVertialLabelCount
	 */
	public void setVertialLabelCount(int mVertialLabelCount) {
		this.mVertialLabelCount = mVertialLabelCount;
	}

	/**
	 * �õ���������ͼ���ߵĺ��
	 * 
	 * @return
	 */
	public int getLineWeight() {
		return mLineWeight;
	}

	/**
	 * ������������ͼ���ߵĺ��
	 * 
	 * @param mLineWeight
	 */
	public void setLineWeight(int mLineWeight) {
		this.mLineWeight = mLineWeight;
		invalidate();
	}

	/**
	 * �õ���������ͼ���ߵ���ɫ
	 * 
	 * @return
	 */
	public int getLineColor() {
		return mLineColor;
	}

	/**
	 * ������������ͼ���ߵ���ɫ
	 * 
	 * @param mLineColor
	 */
	public void setLineColor(int mLineColor) {
		this.mLineColor = mLineColor;
		invalidate();
	}

	/**
	 * �õ�������ɫ
	 * 
	 * @return
	 */
	public int getGridColor() {
		return mGridColor;
	}

	/**
	 * ����������ɫ
	 * 
	 * @param mGridColor
	 */
	public void setGridColor(int mGridColor) {
		this.mGridColor = mGridColor;
		invalidate();
	}

	/**
	 * �õ�������
	 * 
	 * @return
	 */
	public int getGridWeight() {
		return mGridWeight;
	}

	/**
	 * ����������
	 * 
	 * @param mGridWeight
	 */
	public void setGridWeight(int mGridWeight) {
		this.mGridWeight = getPxValue(mGridWeight);
		invalidate();
	}

	/**
	 * �õ������СԲ�������ɫ
	 * 
	 * @return
	 */
	public int getPointColor() {
		return mPointColor;
	}

	/**
	 * ���������СԲ�������ɫ
	 * 
	 * @param mPointColor
	 */
	public void setPointColor(int mPointColor) {
		this.mPointColor = mPointColor;
		invalidate();
	}

	/**
	 * �õ�����㴦СԲ�İ뾶
	 * 
	 * @return
	 */
	public int getPointRadius() {
		return mPointRadius;
	}

	/**
	 * ��������㴦СԲ�İ뾶
	 * 
	 * @param mPointRadius
	 */
	public void setPointRadius(int mPointRadius) {
		this.mPointRadius = getPxValue(mPointRadius);
		invalidate();
	}

	/**
	 * �õ��Ƿ��������㴦��СԲ��true������ƣ�false��������
	 * 
	 * @return
	 */
	public boolean isDrawPoint() {
		return isDrawPoint;
	}

	/**
	 * �����Ƿ��������㴦��СԲ��true������ƣ�false��������
	 * 
	 * @param isDrawPoint
	 */
	public void setDrawPoint(boolean isDrawPoint) {
		this.isDrawPoint = isDrawPoint;
		invalidate();
	}

	/**
	 * �õ��Ƿ����ͼ�α�����true������ƣ�false��������
	 * 
	 * @return
	 */
	public boolean isDrawBackground() {
		return isDrawBackground;
	}

	/**
	 * �����Ƿ����ͼ�α�����true������ƣ�false��������
	 * 
	 * @param isDrawBackground
	 */
	public void setDrawBackground(boolean isDrawBackground) {
		this.isDrawBackground = isDrawBackground;
		invalidate();
	}

	/**
	 * �õ�ͼ�α�����ɫ
	 * 
	 * @return
	 */
	public int getGraphBackgroundColor() {
		return mGraphBackgroundColor;
	}

	/**
	 * ����ͼ�α�����ɫ
	 * 
	 * @param mGraphBackgroundColor
	 */
	public void setGraphBackgroundColor(int mGraphBackgroundColor) {
		this.mGraphBackgroundColor = mGraphBackgroundColor;
		invalidate();
	}

	/**
	 * �õ���Ҫ����ͼ�ε�����Դ
	 * 
	 * @return
	 */
	public GraphViewData[] getData() {
		return mData;
	}

	/**
	 * ������Ҫ����ͼ�ε�����Դ
	 * 
	 * @param mData
	 */
	public void setData(GraphViewData[] mData) {
		this.mData = mData;
		invalidate();
	}

	/**
	 * �õ�ͼ����ͼ�α���֮��ļ������
	 * 
	 * @return
	 */
	public int getSpaceingOfTitleWithGraph() {
		return mSpaceingOfTitleWithGraph;
	}

	/**
	 * ����ͼ����ͼ�α���֮��ļ������
	 * 
	 * @param mSpaceingOfTitleWithGraph
	 */
	public void setSpaceingOfTitleWithGraph(int mSpaceingOfTitleWithGraph) {
		this.mSpaceingOfTitleWithGraph = mSpaceingOfTitleWithGraph;
		invalidate();
	}

	/**
	 * �õ�ͼ���봹ֱ��ǩ�ļ������
	 * 
	 * @return
	 */
	public int getSpaceingOfVerticalLabelWithGraph() {
		return mSpaceingOfVerticalLabelWithGraph;
	}

	/**
	 * ����ͼ���봹ֱ��ǩ�ļ������
	 * 
	 * @param mSpaceingOfVerticalLabelWithGraph
	 */
	public void setSpaceingOfVerticalLabelWithGraph(
			int mSpaceingOfVerticalLabelWithGraph) {
		this.mSpaceingOfVerticalLabelWithGraph = mSpaceingOfVerticalLabelWithGraph;
		invalidate();
	}

	/**
	 * �õ�ͼ����ˮƽ��ǩ�ļ������
	 * 
	 * @return
	 */
	public int getSpaceingOfHorizontalLabelWithGraph() {
		return mSpaceingOfHorizontalLabelWithGraph;
	}

	/**
	 * ����ͼ����ˮƽ��ǩ�ļ������
	 * 
	 * @param mSpaceingOfHorizontalLabelWithGraph
	 */
	public void setSpaceingOfHorizontalLabelWithGraph(
			int mSpaceingOfHorizontalLabelWithGraph) {
		this.mSpaceingOfHorizontalLabelWithGraph = mSpaceingOfHorizontalLabelWithGraph;
		invalidate();
	}

	/**
	 * �õ�ˮƽ��ǩ���ֵĶ��뷽ʽ
	 * 
	 * @return
	 */
	public Align getHorizontalLabelAlign() {
		return mHorizontalLabelAlign;
	}

	/**
	 * ����ˮƽ��ǩ���ֵĶ��뷽ʽ
	 * 
	 * @param mHorizontalLabelAlign
	 */
	public void setHorizontalLabelAlign(Align mHorizontalLabelAlign) {
		this.mHorizontalLabelAlign = mHorizontalLabelAlign;
		invalidate();
	}

	/**
	 * �õ���ֱ��ǩ���ֵĶ��뷽ʽ
	 * 
	 * @return
	 */
	public Align getVerticalLabelAlign() {
		return mVerticalLabelAlign;
	}

	/**
	 * ���ô�ֱ��ǩ���ֵĶ��뷽ʽ
	 * 
	 * @param mVerticalLabelAlign
	 */
	public void setVerticalLabelAlign(Align mVerticalLabelAlign) {
		this.mVerticalLabelAlign = mVerticalLabelAlign;
		invalidate();
	}

	/**
	 * �õ�ͼ�����ֱ���Ķ��뷽ʽ
	 * 
	 * @return
	 */
	public Align getGraphTitleAlign() {
		return mGraphTitleAlign;
	}

	/**
	 * ����ͼ�����ֱ���Ķ��뷽ʽ
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

		// �õ�����������Դ����Y��������ֵ
		double maxY = getMaxYValue();
		// �õ���������Y���������̶�֮������ʾ����ֵ��С

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

		// ����ͼ�α���
		if (mGraphTitle != null) {
			mTextPaint.setTextAlign(mGraphTitleAlign);
			mTextPaint.setTextSize(mGraphTitleTextSize);
			mTextPaint.setColor(mGraphTitleColor);
			canvas.drawText(mGraphTitle, startX + validWidth / 2,
					getPaddingTop() + getFontHeight(mGraphTitleTextSize),
					mTextPaint);
		}

		// ��ʼ��ˮƽ������
		for (int i = 0; i < mHorizontalLabelCount - 1; i++) {

			canvas.drawLine(startX + i * validWidth
					/ (mHorizontalLabelCount - 1), startY, startX + i
					* validWidth / (mHorizontalLabelCount - 1), startY
					+ validHeight, mGridPaint);

		}
		canvas.drawLine(startX + validWidth, startY, startX + validWidth,
				startY + validHeight, mGridPaint);

		// ��ʼ��ˮƽ��ǩ
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

		// ��ʼ����ֱ�����ߺʹ�ֱ��ǩ
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

		// ���ݸ���������Դ����ͼ�α���
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

		// ���ݸ���������Դ����ͼ��
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

		// ��������㴦��СԲ

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
	 * ��double�����ݸ�ʽ���ɴ���4λ��ЧС�������ݲ����ַ�����ʽ����
	 * 
	 * @param d
	 * @return
	 */
	private String formatYValue(double d) {
		return (long) (Math.round(d * 10000)) / 10000.0 + "";
	}

	/**
	 * �õ�����Դ����Y������������
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
	 * �õ���ֱ��ǩ��ռ�õ������
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
	 * �õ�����߶�
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
	 * �õ����ֵĿ��
	 * 
	 * @return
	 */
	private int getFontWidth(float fontSize, String str) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		return (int) paint.measureText(str);
	}

	/**
	 * ����dpΪ��λ��ʾ��ֵת������pxΪ��λ��ֵ
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
	 * ����spΪ��λ��ʾ��ֵת������pxΪ��λ��ֵ
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
