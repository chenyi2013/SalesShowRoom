package com.puji.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class CustomCircleView extends View {

	private int selledCount = 134;
	private int totalCount = 360;
	private int yesterdayCount = 35;

	private final int space = getPxValue(1);
	private final int horizontalSpace = getPxValue(10);

	private String totalTitle = "�ܷ�������";
	private String selledTitle = "����������";
	private String yesterDayTitle = "��������";

	Paint mPaint = new Paint();

	private int circularWidth = getPxValue(50);
	private int smallCircleRadius = getPxValue(3);
	private int indictorLineWeight = 1;//getPxValue(1);

	private int titleFontSize = convertSpToPxValue(14);
	private int countFontSize = convertSpToPxValue(12);

	private int yesterdayTitleFontSize = convertSpToPxValue(14);
	private int yesterdayCountFontSize = convertSpToPxValue(12);

	private int totalCountTitleColor = Color.GREEN;
	private int totalCountColor = Color.CYAN;

	private int selledCountTitleColor = Color.GREEN;
	private int selledCountColor = Color.CYAN;

	private int yesterdayCountTitleColor = Color.RED;
	private int yesterdayCountColor = Color.BLUE;

	private int totalRingColor = Color.parseColor("#8C8C8C");
	private int selledRingColor =  Color.parseColor("#FB6F24");
	private int yesterdayRingColor = Color.RED;

	private int lineColor = Color.WHITE;

	private final int fontMarginBottom = getPxValue(5);
	private boolean isSwitchColor = false;
	private boolean isStop = false;

	private final int SUCESS = 1;
	private final int STOP = 2;

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == SUCESS) {
				isSwitchColor = !isSwitchColor;
				invalidate();
				mHandler.sendEmptyMessageDelayed(SUCESS, 1000);
			} else if (msg.what == STOP) {
				mHandler.removeMessages(SUCESS);
				isStop = true;
			}
		};
	};

	public CustomCircleView(Context context) {
		super(context);
		init();
	}

	public CustomCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public CustomCircleView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mHandler.sendEmptyMessageDelayed(SUCESS, 1000);
	}

	public int getSelledCount() {
		return selledCount;
	}

	public void setSelledCount(int selledCount) {
		this.selledCount = selledCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getYesterdayCount() {
		return yesterdayCount;
	}

	public void setYesterdayCount(int yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}

	public String getTotalTitle() {
		return totalTitle;
	}

	public void setTotalTitle(String totalTitle) {
		this.totalTitle = totalTitle;
	}

	public String getSelledTitle() {
		return selledTitle;
	}

	public void setSelledTitle(String selledTitle) {
		this.selledTitle = selledTitle;
	}

	public String getYesterDayTitle() {
		return yesterDayTitle;
	}

	public void setYesterDayTitle(String yesterDayTitle) {
		this.yesterDayTitle = yesterDayTitle;
	}

	public int getCircularWidth() {
		return circularWidth;
	}

	public void setCircularWidth(int circularWidth) {
		this.circularWidth = getPxValue(circularWidth);
	}

	public int getSmallCircleRadius() {
		return smallCircleRadius;
	}

	public void setSmallCircleRadius(int smallCircleRadius) {
		this.smallCircleRadius = getPxValue(smallCircleRadius);
	}

	public int getIndictorLineWeight() {
		return indictorLineWeight;
	}

	public void setIndictorLineWeight(int indictorLineWeight) {
		this.indictorLineWeight = getPxValue(indictorLineWeight);
	}

	public int getTitleFontSize() {
		return titleFontSize;
	}

	public void setTitleFontSize(int titleFontSize) {
		this.titleFontSize = convertSpToPxValue(titleFontSize);
	}

	public int getCountFontSize() {
		return countFontSize;
	}

	public void setCountFontSize(int countFontSize) {
		this.countFontSize = convertSpToPxValue(countFontSize);
	}

	public int getYesterdayTitleFontSize() {
		return yesterdayTitleFontSize;
	}

	public void setYesterdayTitleFontSize(int yesterdayTitleFontSize) {
		this.yesterdayTitleFontSize = convertSpToPxValue(yesterdayTitleFontSize);
	}

	public int getYesterdayCountFontSize() {
		return yesterdayCountFontSize;
	}

	public void setYesterdayCountFontSize(int yesterdayCountFontSize) {
		this.yesterdayCountFontSize = convertSpToPxValue(yesterdayCountFontSize);
	}

	public int getTotalCountTitleColor() {
		return totalCountTitleColor;
	}

	public void setTotalCountTitleColor(int totalCountTitleColor) {
		this.totalCountTitleColor = totalCountTitleColor;
	}

	public int getTotalCountColor() {
		return totalCountColor;
	}

	public void setTotalCountColor(int totalCountColor) {
		this.totalCountColor = totalCountColor;
	}

	public int getSelledCountTitleColor() {
		return selledCountTitleColor;
	}

	public void setSelledCountTitleColor(int selledCountTitleColor) {
		this.selledCountTitleColor = selledCountTitleColor;
	}

	public int getSelledCountColor() {
		return selledCountColor;
	}

	public void setSelledCountColor(int selledCountColor) {
		this.selledCountColor = selledCountColor;
	}

	public int getYesterdayCountTitleColor() {
		return yesterdayCountTitleColor;
	}

	public void setYesterdayCountTitleColor(int yesterdayCountTitleColor) {
		this.yesterdayCountTitleColor = yesterdayCountTitleColor;
	}

	public int getYesterdayCountColor() {
		return yesterdayCountColor;
	}

	public void setYesterdayCountColor(int yesterdayCountColor) {
		this.yesterdayCountColor = yesterdayCountColor;
	}

	public int getTotalRingColor() {
		return totalRingColor;
	}

	public void setTotalRingColor(int totalRingColor) {
		this.totalRingColor = totalRingColor;
	}

	public int getSelledRingColor() {
		return selledRingColor;
	}

	public void setSelledRingColor(int selledRingColor) {
		this.selledRingColor = selledRingColor;
	}

	public int getLineColor() {
		return lineColor;
	}

	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}

	public int getYesterdayRingColor() {
		return yesterdayRingColor;
	}

	public void setYesterdayRingColor(int yesterdayRingColor) {
		this.yesterdayRingColor = yesterdayRingColor;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int fontHeight = getFontHeight(countFontSize) / 2
				+ getFontHeight(titleFontSize) / 2;
		int fontWidth = getFontWidth(countFontSize) + horizontalSpace;

		int validWidth = getWidth() - fontWidth * 2 - circularWidth
				- smallCircleRadius * 2;
		int validHeight = getHeight() - fontHeight * 2 - circularWidth
				- smallCircleRadius * 2;

		int startX = fontWidth + circularWidth / 2 + smallCircleRadius;
		int startY = fontHeight + circularWidth / 2 + smallCircleRadius;

		int dia = validWidth > validHeight ? validHeight : validWidth;
		int radius = dia / 2;

		startX = startX + (getWidth() - dia - startX * 2) / 2;
		startY = startY + (getHeight() - dia - startY * 2) / 2;

		int endX = startX + dia;
		int endY = startY + dia;

		int x = startX + radius;// Բ�Ĵ���X�᷽�������
		int y = startY + radius;// Բ�Ĵ���Y�᷽�������

		float selled = convertNumberToPI(selledCount, totalCount);
		float total = convertNumberToPI(selledCount - totalCount, totalCount);
		float yesterday = convertNumberToPI(yesterdayCount, totalCount);

		if (yesterday != 0) {
			mPaint.setStrokeWidth(2);
			mPaint.setColor(lineColor);
			canvas.drawLine(
					getXValuse(x, radius - circularWidth, yesterday / 2),
					getYValuse(y, radius - circularWidth, yesterday / 2),
					getXValuse(x, radius, yesterday / 2),
					getYValuse(y, radius, yesterday / 2), mPaint);
		} else if (!isStop) {
			mHandler.sendEmptyMessage(STOP);
		}

		mPaint.setAntiAlias(true);
		mPaint.setTextAlign(Align.CENTER);
		mPaint.setColor(yesterdayCountColor);
		mPaint.setDither(true);
		mPaint.setTextSize(yesterdayCountFontSize);
		canvas.drawText(yesterdayCount + "", x, y
				+ getFontHeight(yesterdayTitleFontSize) / 2 + fontMarginBottom,
				mPaint);

		mPaint.setColor(yesterdayCountTitleColor);
		mPaint.setTextSize(yesterdayTitleFontSize);
		mPaint.setStrokeWidth(2);
		canvas.drawText(yesterDayTitle, x, y, mPaint);

		mPaint.setTextAlign(Align.LEFT);
		mPaint.setColor(lineColor);
		if (getXValuse(x, radius, selled / 2) > x) {

			canvas.drawLine(getXValuse(x, radius, selled / 2),
					getYValuse(y, radius, selled / 2), getWidth(),
					getYValuse(y, radius, selled / 2), mPaint);

			canvas.drawCircle(getWidth() - smallCircleRadius, y + radius
					* (float) Math.sin(selled / 2), smallCircleRadius, mPaint);

			mPaint.setColor(selledCountColor);
			mPaint.setTextSize(countFontSize);
			canvas.drawText("" + selledCount, x + radius + circularWidth / 2
					+ space, y + radius * (float) Math.sin(selled / 2)
					- indictorLineWeight / 2 - fontMarginBottom, mPaint);

			mPaint.setColor(selledCountTitleColor);
			mPaint.setTextSize(titleFontSize);
			canvas.drawText(selledTitle,
					x + radius + circularWidth / 2 + space, y + radius
							* (float) Math.sin(selled / 2) - indictorLineWeight
							/ 2 - 2 * fontMarginBottom
							- getFontHeight(countFontSize) / 2, mPaint);

		} else {

			canvas.drawLine(getXValuse(x, radius, selled / 2),
					getYValuse(y, radius, selled / 2), 0,
					getYValuse(y, radius, selled / 2), mPaint);

			canvas.drawCircle(0 + smallCircleRadius,
					y + radius * (float) Math.sin(selled / 2),
					smallCircleRadius, mPaint);

			mPaint.setColor(selledCountColor);
			mPaint.setTextSize(countFontSize);
			canvas.drawText("" + selledCount, 0 + smallCircleRadius * 2, y
					+ radius * (float) Math.sin(selled / 2)
					- indictorLineWeight / 2 - fontMarginBottom, mPaint);

			mPaint.setColor(selledCountTitleColor);
			mPaint.setTextSize(titleFontSize);
			canvas.drawText(selledTitle, 0 + smallCircleRadius * 2, y + radius
					* (float) Math.sin(selled / 2) - indictorLineWeight / 2 - 2
					* fontMarginBottom - getFontHeight(countFontSize) / 2,
					mPaint);

		}

		mPaint.setColor(lineColor);
		if (getXValuse(x, radius, total / 2) > x) {

			canvas.drawLine(getXValuse(x, radius, total / 2),
					getYValuse(y, radius, total / 2), getWidth(),
					getYValuse(y, radius, total / 2), mPaint);

			canvas.drawCircle(getWidth() - smallCircleRadius, y + radius
					* (float) Math.sin(total / 2), smallCircleRadius, mPaint);

			mPaint.setColor(totalCountColor);
			mPaint.setTextSize(countFontSize);
			canvas.drawText("" + totalCount, x + radius + circularWidth / 2
					+ space, y + radius * (float) Math.sin(total / 2)
					- indictorLineWeight / 2 - fontMarginBottom, mPaint);

			mPaint.setColor(totalCountTitleColor);
			mPaint.setTextSize(titleFontSize);
			canvas.drawText(totalTitle, x + radius + circularWidth / 2 + space,
					y + radius * (float) Math.sin(total / 2)
							- indictorLineWeight / 2 - 2 * fontMarginBottom
							- getFontHeight(countFontSize) / 2, mPaint);

		} else {
			canvas.drawLine(getXValuse(x, radius, total / 2),
					getYValuse(y, radius, total / 2), 0,
					getYValuse(y, radius, total / 2), mPaint);

			canvas.drawCircle(0 + smallCircleRadius,
					y + radius * (float) Math.sin(total / 2),
					smallCircleRadius, mPaint);

			mPaint.setColor(totalCountColor);
			mPaint.setTextSize(countFontSize);
			canvas.drawText("" + totalCount, 0 + smallCircleRadius * 2, y
					+ radius * (float) Math.sin(total / 2) - indictorLineWeight
					/ 2 - fontMarginBottom, mPaint);

			mPaint.setColor(totalCountTitleColor);
			mPaint.setTextSize(titleFontSize);
			canvas.drawText(totalTitle, 0 + smallCircleRadius * 2, y + radius
					* (float) Math.sin(total / 2) - indictorLineWeight / 2 - 2
					* fontMarginBottom - getFontHeight(countFontSize) / 2,
					mPaint);
		}

		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(circularWidth);

		mPaint.setColor(totalRingColor);
		canvas.drawArc(new RectF(startX, startY, endX, endY),
				convertNumberToAngle(selledCount, totalCount), 360, false,
				mPaint);

		mPaint.setColor(selledRingColor);
		canvas.drawArc(new RectF(startX, startY, endX, endY), 0,
				convertNumberToAngle(selledCount, totalCount), false, mPaint);

		if (isSwitchColor) {
			mPaint.setColor(yesterdayRingColor);
		} else {
			mPaint.setColor(selledRingColor);
		}

		if (yesterday != 0) {
			canvas.drawArc(new RectF(startX, startY, endX, endY), 0,
					convertNumberToAngle(yesterdayCount, totalCount), false,
					mPaint);
		}

		mPaint.reset();
		canvas.save();
	}

	/**
	 * �õ�Բ����ָ��������X�᷽�������
	 * 
	 * @param x
	 * @param radius
	 * @param angle
	 * @return
	 */
	private float getXValuse(float x, float radius, float angle) {
		return x + radius * (float) Math.cos(angle);
	}

	/**
	 * �õ�Բ����ָ��������Y�᷽�������
	 * 
	 * @param y
	 * @param radius
	 * @param angle
	 * @return
	 */
	private float getYValuse(float y, float radius, float angle) {
		return y + radius * (float) Math.sin(angle);
	}

	/**
	 * ������ת����PIֵ��ʾ
	 * 
	 * @param angle
	 * @return
	 */
	private float convertNumberToPI(float part, float total) {

		return (float) (part / total * 2 * Math.PI);
	}

	/**
	 * ������ת���ɶ���ֵ��ʾ
	 * 
	 * @param part
	 * @param total
	 * @return
	 */
	private float convertNumberToAngle(float part, float total) {
		return part / total * 360;
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
		FontMetrics fm = paint.getFontMetrics();
		return (int) Math.ceil(fm.descent - fm.top) + 2;
	}

	/**
	 * �õ����ֵĿ��
	 * 
	 * @return
	 */
	private int getFontWidth(float fontSize) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		return (int) paint.measureText("00000000");
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

}
