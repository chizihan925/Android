package com.hq.source;

import com.hq.adapter.MyGridAdapter;
import com.hq.filemanagertestmain.FileMainActivity;
import com.hq.filemanagertestmain.R;
import com.hq.view.CircleView;
import com.hq.view.MyGridView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.AdapterView.OnItemClickListener;

public class ChatFragmentReal extends Fragment implements OnItemClickListener {

	private View v;
	private MyGridView gridview;
	private Context mContext;
	
	public static int gridSelect;

	private Fragment gridviewclickfragmenmt;

	private android.support.v4.app.FragmentTransaction filemanger;
	
	/*
	 * Control the interface whether go to second 
	 * 0:in the first interface
	 * 1:in the second interface
	 */
	private int mInterfaceCode;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.lattice_main, container, false);
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
		initCircleView();
	}

	private void init() {
		
		getResources().getStringArray(R.array.grid_name);
		
		mInterfaceCode = 0;

		gridview = (MyGridView) getView().findViewById(R.id.gridview);
		gridview.setAdapter(new MyGridAdapter(mContext));
		gridview.setOnItemClickListener(this);

		gridviewclickfragmenmt = new GridViewItemClickFragment();
		filemanger = getFragmentManager().beginTransaction();
		
		
	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		gridSelect = arg2;
		switch (arg2) {
		case 0:
			GridViewItemClickFragment.mSelection = DataBaseSelection.IMAGE_SELECTION;
			break;
		case 1:
			GridViewItemClickFragment.mSelection = DataBaseSelection.AUDIO_SELECTION;
			break;
		case 2:
			GridViewItemClickFragment.mSelection = DataBaseSelection.VIDEO_SELECTION;
			break;
		case 3:
			GridViewItemClickFragment.mSelection = DataBaseSelection.DOC_SELECTION;
			break;
		case 4:
			GridViewItemClickFragment.mSelection = DataBaseSelection.RAR_SELECTION;
			break;
		case 5:
			GridViewItemClickFragment.mSelection = DataBaseSelection.APK_SELECTION;
			break;

		default:
			break;
		}
		sendData();
		filemanger.replace(R.id.chat_fragment, gridviewclickfragmenmt);
		filemanger.addToBackStack(null);
		filemanger.commit();

	}

	private void initCircleView() {
		FrameLayout circleMax = (FrameLayout) getView().findViewById(R.id.root);
		circleMax.addView(new CircleView(mContext));
	}
	private void sendData(){
		int i = 4;
		switch (gridSelect) {
		case 0:
			i = 2;
			mInterfaceCode = 1;
			break;
		case 1:
			i = 3;
			mInterfaceCode = 1;
			break;
		case 2:
			i = 3;
			mInterfaceCode = 1;
			break;
		case 3:
			i = 3;
			mInterfaceCode = 1;
			break;
		default:
			i = 4;
			mInterfaceCode = 0;
			break;
		}
		Intent intent = new Intent("com.hq.data.DATA_TRANSMISSION");
		intent.putExtra("data", i);
		intent.putExtra("code", mInterfaceCode);
		mContext.sendBroadcast(intent);
	}
}
