package com.hq.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hq.filemanagertestmain.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class InternalStorageFragment extends Fragment {
	
	private Context mContext;
	private ListView mLocalList;
	View chatView;
	
	TextView mItemLocalTitleNext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		chatView = inflater.inflate(R.layout.insert_storage_list,
				container, false);
		Log.d("chiguoqing", "InternalStorageFragment");
		return chatView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = getActivity();
		init();
		setStorageListView();
	}
	
	private void init(){
		mItemLocalTitleNext = (TextView)getActivity().findViewById(R.id.ItemLocalTitleNext);
		mItemLocalTitleNext.setText("内部存储");
		mLocalList = (ListView) getView().findViewById(R.id.item_list);
	}

	private void setStorageListView(){
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(mContext, getData(),
				R.layout.insert_storage_list_item, new String[] { "ItemImage_left",
						"ItemImage", "ItemTitle", "ItemText" }, new int[] {
						R.id.ItemImage_left, R.id.ItemImage, R.id.ItemTitle,
						R.id.ItemText });
		mLocalList.setAdapter(mSimpleAdapter);
		mLocalList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
			}
		});
	}
	
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map;
		for(int i=0;i<10;i++){
			map = new HashMap<String, Object>();
			map.put("ItemImage_left", R.drawable.icon_folder);
			map.put("ItemImage", R.drawable.list_arrow);
			map.put("ItemTitle", "Android");
			map.put("ItemText", "文件： "+i+", " + "文件夹： 0");
			list.add(map);
		}
		return list;
	}

	
}
