package com.hq.filemanagertestmain;

import java.util.ArrayList;
import java.util.List;

import com.hq.source.ChatFragment;
import com.hq.source.FriendFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FileMainActivity extends FragmentActivity {

	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private FragmentAdapter mFragmentAdapter;

	private LinearLayout mClassify, mLocal;
	private View mBottomItem1, mBottomItem2, mBottomItem3, mBottomItem4;
	private ViewPager mPageVp;
	private TextView mTabChatTv, mTabFriendTv;
	private ImageView mTabLineIv;
	private ChatFragment mChatFg;
	private FriendFragment mFriendFg;
	private int currentIndex;
	private int screenWidth;

	private MyBroadCastReceiver mReceiver;
	private int itemCode;
	private int itemCodeOld;
	private int itemCodeLocal;
	private int itemCodeLocalOld;
	private int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_file_main);
		Log.d("chiguoqing", "1" + this.toString());
		findById();
		init();
		initTabLineWidth();
		mClassify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				lattice_click();

			}
		});
		mLocal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				local_click();

			}
		});
	}

	private void findById() {
		mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);
		mTabFriendTv = (TextView) this.findViewById(R.id.id_friend_tv);
		mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
		mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
		mBottomItem1 = (View) this.findViewById(R.id.bottom_item1);
		mBottomItem2 = (View) this.findViewById(R.id.bottom_item2);
		mBottomItem3 = (View) this.findViewById(R.id.bottom_item3);
		mBottomItem4 = (View) this.findViewById(R.id.bottom_item4);
		mClassify = (LinearLayout) this.findViewById(R.id.id_tab_chat_ll);
		mLocal = (LinearLayout) findViewById(R.id.id_tab_friend_ll);
		mReceiver = new MyBroadCastReceiver();
	}

	private void init() {

		mFriendFg = new FriendFragment();
		mChatFg = new ChatFragment();
		mFragmentList.add(mChatFg);
		mFragmentList.add(mFriendFg);

		itemCode = 0;
		itemCodeOld = 0;
		itemCodeLocal = 0;
		itemCodeLocalOld = 0;

		mFragmentAdapter = new FragmentAdapter(
				this.getSupportFragmentManager(), mFragmentList);
		mPageVp.setAdapter(mFragmentAdapter);
		mPageVp.setCurrentItem(0);

		mPageVp.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageScrollStateChanged(int state) {

			}

			public void onPageScrolled(int position, float offset,
					int offsetPixels) {
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
						.getLayoutParams();

				Log.e("offset:", offset + "");

				if (currentIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));

				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));

				}
				mTabLineIv.setLayoutParams(lp);
			}

			public void onPageSelected(int position) {
				mPosition = position;
				switch (position) {
				case 0:
					mTabChatTv.setTextColor(Color.WHITE);
					updateItemUI(itemCode);
					break;
				case 1:
					mTabFriendTv.setTextColor(Color.WHITE);
					updateItemUI(itemCodeLocal);
					break;
				}
				currentIndex = position;
			}
		});

	}

	private void initTabLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(dpMetrics);
		screenWidth = dpMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
				.getLayoutParams();
		lp.width = screenWidth / 2;
		mTabLineIv.setLayoutParams(lp);
	}

	private void lattice_click() {
		int currentItem = mPageVp.getCurrentItem();
		if (currentItem != 0) {
			mPageVp.setCurrentItem(0);
		}
	}

	private void local_click() {
		int currentItem = mPageVp.getCurrentItem();
		if (currentItem != 1) {
			mPageVp.setCurrentItem(1);
		}
	}

	class MyBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent intent) {
			int i = intent.getIntExtra("data", 4);
			setItemCode(i);
			updateItemUI(i);
			Log.d("chiguoqing", "Activity --- " + i);
		}

	}

	private void updateItemUI(int i) {
		switch (i) {
		case 2:
			mBottomItem1.setVisibility(View.GONE);
			mBottomItem2.setVisibility(View.VISIBLE);
			mBottomItem3.setVisibility(View.GONE);
			mBottomItem4.setVisibility(View.GONE);
			break;
		case 3:
			mBottomItem1.setVisibility(View.GONE);
			mBottomItem2.setVisibility(View.GONE);
			mBottomItem3.setVisibility(View.VISIBLE);
			mBottomItem4.setVisibility(View.GONE);
			break;
		case 4:
			mBottomItem1.setVisibility(View.GONE);
			mBottomItem2.setVisibility(View.GONE);
			mBottomItem3.setVisibility(View.GONE);
			mBottomItem4.setVisibility(View.VISIBLE);
			break;
		default:
			mBottomItem1.setVisibility(View.VISIBLE);
			mBottomItem2.setVisibility(View.GONE);
			mBottomItem3.setVisibility(View.GONE);
			mBottomItem4.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.hq.data.DATA_TRANSMISSION");
		this.registerReceiver(mReceiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(mReceiver);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.d("chiguoqingCode","mPosition= "+mPosition+"; itemCode= "+itemCode+": itemCodeLocal"+itemCodeLocal);
		if(mPosition == 0 && itemCode != 0){
			mBottomItem1.setVisibility(View.VISIBLE);
			mBottomItem2.setVisibility(View.GONE);
			mBottomItem3.setVisibility(View.GONE);
			mBottomItem4.setVisibility(View.GONE);
		}else if(mPosition == 1 && itemCodeLocal != 0){
			mBottomItem1.setVisibility(View.VISIBLE);
			mBottomItem2.setVisibility(View.GONE);
			mBottomItem3.setVisibility(View.GONE);
			mBottomItem4.setVisibility(View.GONE);
		}
		
	}

	private void setItemCode(int i) {
		if (mPosition == 0) {
			itemCode = i;
		} else {
			itemCodeLocal = i;
		}
	}
}
