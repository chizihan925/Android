package com.hq.source;

import com.hq.adapter.MyGridAdapter;
import com.hq.filemanagertestmain.R;
import com.hq.view.CircleView;
import com.hq.view.MyGridView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.lattice_main, container, false);
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		init();
		initCircleView();
		return v;
	}

	private void init() {
		getResources().getStringArray(R.array.grid_name);

		gridview = (MyGridView) getView().findViewById(R.id.gridview);
		gridview.setAdapter(new MyGridAdapter(mContext));

		// mGridView = (GridView) v.findViewById(R.id.mGridView);
		// mGridView.setAdapter(new GridViewAdapter(getActivity(), item_name,
		// image_resourse));
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

		filemanger.replace(R.id.chat_fragment, gridviewclickfragmenmt);
		filemanger.addToBackStack(null);
		filemanger.commit();

	}

	private void initCircleView() {
		FrameLayout circleMax = (FrameLayout) getView().findViewById(R.id.root);
		circleMax.addView(new CircleView(mContext));
	}

}
