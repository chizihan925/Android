package com.hq.view;

import com.hq.filemanagertestmain.StorageState;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.view.View;

public class CircleView extends View {

	private float mCircleX = 360;
	private float mCircleY = 180;
	private float mCircleCenter = 130;

	private StorageState mStorageState = new StorageState();
	private long mTotalStorage;
	private long mUsedStorage;
	private String mInternalPercentage;
	private long mExternalTotalStorage;
	private long mExternalUsedStorage;
	@SuppressWarnings("unused")
	private String mExternalPercentage;

	Context m_context;

	public CircleView(Context context) {
		super(context);
		m_context = context;
		initialize();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint paint1 = new Paint();
		paint1.setAntiAlias(true);
		paint1.setColor(Color.WHITE);
		paint1.setStyle(Style.STROKE);
		paint1.setStrokeWidth(2);

		canvas.drawRGB(255, 255, 255);

		canvas.drawCircle(mCircleX, mCircleY, mCircleCenter, paint1);
		canvas.drawCircle(mCircleX, mCircleY, mCircleCenter - 20, paint1);
		paint1.setColor(Color.GRAY);
		for (double q = 5 * Math.PI / 4; q > (-Math.PI) / 4; q = q - 3
				* Math.PI / 200) {
			float Xmax = getCircleXmax((float) (q));
			float Ymax = getCircleYmax((float) (q));
			float Xmin = getCircleXmin((float) (q));
			float Ymin = getCircleYmin((float) (q));
			canvas.drawLine(Xmin, Ymin, Xmax, Ymax, paint1);
		}
		paint1.setColor(Color.GREEN);
		for (double q = 5 * Math.PI / 4; q > (Math.PI) * 5 / 4 - 5 * Math.PI
				/ 9 * mExternalUsedStorage / mExternalTotalStorage; q = q - 3
				* Math.PI / 200) {
			if (q < Math.PI / 6) {
				paint1.setColor(Color.RED);
			}
			float Xmax = getCircleXmax((float) (q));
			float Ymax = getCircleYmax((float) (q));
			float Xmin = getCircleXmin((float) (q));
			float Ymin = getCircleYmin((float) (q));
			canvas.drawLine(Xmin, Ymin, Xmax, Ymax, paint1);
		}
		paint1.setTextAlign(Align.CENTER);
		paint1.setTextSize(80);
		paint1.setColor(Color.BLACK);
		paint1.setStyle(Style.FILL);
		paint1.setStrokeWidth(1);
		canvas.drawText(mInternalPercentage, mCircleX, mCircleY, paint1);

		paint1.setTextAlign(Align.RIGHT);
		paint1.setTextSize(30);
		canvas.drawText("%", mCircleX + 70, mCircleY - 30, paint1);

		paint1.setTextAlign(Align.CENTER);
		paint1.setTextSize(26);
		canvas.drawText("内部存储", mCircleX, mCircleY + 50, paint1);

		paint1.setTextAlign(Align.CENTER);
		paint1.setTextSize(25);
		canvas.drawText(mStorageState.formatFileSize(mUsedStorage) + " / "
				+ mStorageState.formatFileSize(mTotalStorage), mCircleX,
				mCircleY + 150, paint1);
	}

	public float getCircleXmax(float x) {
		return (float) (mCircleX + mCircleCenter * Math.cos(x));
	}

	public float getCircleYmax(float x) {
		return (float) (mCircleY + mCircleCenter * Math.sin(x + Math.PI));
	}

	public float getCircleXmin(float x) {
		return (float) (mCircleX + (mCircleCenter - 20) * Math.cos(x));
	}

	public float getCircleYmin(float x) {
		return (float) (mCircleY + (mCircleCenter - 20) * Math.sin(x + Math.PI));
	}

	private void initialize() {
		mTotalStorage = mStorageState.getTotalInternalMemorySize();
		long usedStorage = mStorageState.getTotalInternalMemorySize()
				- mStorageState.getAvailableInternalMemorySize();
		mUsedStorage = usedStorage;
		if(mTotalStorage != 0){
			mInternalPercentage = String
					.valueOf(mUsedStorage * 100 / mTotalStorage);
		}
		
		mExternalTotalStorage = mStorageState.getTotalExternalMemorySize();
		long externalUsedStorage = mStorageState.getTotalExternalMemorySize()
				- mStorageState.getAvailableExternalMemorySize();
		mExternalUsedStorage = externalUsedStorage;
		if(mExternalTotalStorage != 0){
			mExternalPercentage = String.valueOf(mExternalUsedStorage * 100
					/ mExternalTotalStorage);
		}
		
	}
}
