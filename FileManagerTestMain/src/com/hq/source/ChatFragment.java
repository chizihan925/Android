package com.hq.source;

import com.hq.filemanagertestmain.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatFragment extends Fragment {
	private Fragment mChatFragmentReal;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View chatView = inflater.inflate(R.layout.chat_fragment, container,
				false);
		mChatFragmentReal = new ChatFragmentReal();
		FragmentTransaction filemanager = getFragmentManager().beginTransaction();
		filemanager.replace(R.id.chat_fragment, mChatFragmentReal);
		filemanager.commit();
		return chatView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}
