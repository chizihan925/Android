package com.hq.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hq.filemanagertestmain.R;
import com.hq.filemanagertestmain.StorageState;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FriendFragment extends Fragment {

	private Context mContext;
	private String mAvailableStorage;
	@SuppressWarnings("unused")
	private String mTotalStorage;
	private String mUsedStorage;
	private String mExternalAvailableStorage;
	@SuppressWarnings("unused")
	private String mExternalTotalStorage;
	private String mExternalUsedStorage;
	private StorageState mStorageState = new StorageState();
	private ListView mLocalList;
	
	/*
	 * Control the interface whether go to second 
	 * 2:in the first interface
	 * 3:in the second interface
	 */
	private int mInterfaceCodeLocal;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View chatView = inflater.inflate(R.layout.activity_local_main,
				container, false);
		return chatView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = getActivity();
		initialize();
		setStorageListView();
	}

	private void addFragment(Fragment fragment, String Tag) {
		FragmentTransaction fragmentManager = FriendFragment.this.getActivity()
				.getSupportFragmentManager().beginTransaction();
		fragmentManager.add(R.id.local_second, fragment, Tag);
		fragmentManager.addToBackStack(null);
		fragmentManager.commit();
	}

	private void initialize() {
		
		mInterfaceCodeLocal = 2;
		
		mTotalStorage = mStorageState.formatFileSize(mStorageState
				.getTotalInternalMemorySize());
		mAvailableStorage = mStorageState.formatFileSize(mStorageState
				.getAvailableInternalMemorySize());
		long usedStorage = mStorageState.getTotalInternalMemorySize()
				- mStorageState.getAvailableInternalMemorySize();
		mUsedStorage = mStorageState.formatFileSize(usedStorage);

		mExternalTotalStorage = mStorageState.formatFileSize(mStorageState
				.getTotalExternalMemorySize());
		mExternalAvailableStorage = mStorageState.formatFileSize(mStorageState
				.getAvailableExternalMemorySize());
		long externalUsedStorage = mStorageState.getTotalExternalMemorySize()
				- mStorageState.getAvailableExternalMemorySize();
		mExternalUsedStorage = mStorageState
				.formatFileSize(externalUsedStorage);
		mLocalList = (ListView) getView().findViewById(R.id.lv);
	}

	private void setStorageListView() {
		mLocalList = (ListView) getView().findViewById(R.id.lv);
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(mContext, getData(),
				R.layout.activity_local_list, new String[] { "ItemImage_left",
						"ItemImage", "ItemTitle", "ItemText" }, new int[] {
						R.id.ItemImage_left, R.id.ItemImage, R.id.ItemTitle,
						R.id.ItemText });
		mLocalList.setAdapter(mSimpleAdapter);
		mLocalList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position == 0) {
					InternalStorageFragment fragment1 = new InternalStorageFragment();
					addFragment(fragment1, "InternalStorageFragment");
					Intent intent = new Intent("com.hq.data.DATA_TRANSMISSION");
					intent.putExtra("codeLocal", mInterfaceCodeLocal);
					intent.putExtra("data", 3);
					mContext.sendBroadcast(intent);
				} else if (position == 1) {
					ChatFragmentReal fragment2 = new ChatFragmentReal();
					addFragment(fragment2, "ChatFragmentReal");
				}

			}
		});
	}

	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemImage_left", R.drawable.ic_classify_internalstorage);
		map.put("ItemImage", R.drawable.list_arrow);
		map.put("ItemTitle", "鍐呴儴瀛樺偍");
		map.put("ItemText", "宸茬敤锛� " + mUsedStorage + ", " + "鍙敤锛� "
				+ mAvailableStorage);
		list.add(map);

		if (mStorageState.externalMemoryAvailable()) {
			map = new HashMap<String, Object>();
			map.put("ItemImage_left", R.drawable.ic_classify_sdcard);
			map.put("ItemImage", R.drawable.list_arrow);
			map.put("ItemTitle", "SD 鍗�");
			map.put("ItemText", "宸茬敤锛� " + mExternalUsedStorage + ", " + "鍙敤锛� "
					+ mExternalAvailableStorage);
			list.add(map);
		}

		return list;
	}
}
