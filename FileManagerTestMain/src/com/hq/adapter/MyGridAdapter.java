package com.hq.adapter;

import com.hq.filemanagertestmain.R;
import com.hq.source.BaseViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridAdapter extends BaseAdapter {

	private Context mContext;

	public String[] img_text = { "图片", "音频", "视频", "文档", "压缩包", "应用",
			"最近访问", "文件书签", "保密柜", };
	public String[] num_text = {"("+" "+")","("+" "+")","("+" "+")","("+" "+")","("+" "+")",
			"("+" "+")","("+" "+")","("+" "+")","("+" "+")"};
	public int[] imgs = { R.drawable.category_icon_image, R.drawable.category_icon_music,
			R.drawable.category_icon_video, R.drawable.category_icon_document,
			R.drawable.category_icon_compress, R.drawable.category_icon_application,
			R.drawable.category_icon_recent, R.drawable.category_icon_favorite, R.drawable.ic_mai_strongbox };

	public MyGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		TextView num = BaseViewHolder.get(convertView, R.id.num_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		setNumText();
		num.setText(num_text[position]);
		return convertView;
	}
	
	public void setNumText(){
		for(int i=0;i<10;i++){
			switch(i){
			case 0:
				num_text[i] = "( "+"0"+" )";
				break;
			case 1:
				num_text[i] = "( "+"1"+" )";
				break;
			case 2:
				num_text[i] = "( "+"2"+" )";
				break;
			case 3:
				num_text[i] = "( "+"3"+" )";
				break;
			case 4:
				num_text[i] = "( "+"4"+" )";
				break;
			case 5:
				num_text[i] = "( "+"5"+" )";
				break;
			case 6:
				num_text[i] = "( "+"6"+" )";
				break;
			case 7:
				num_text[i] = "( "+"7"+" )";
				break;
			case 8:
				num_text[i] = "( "+"8"+" )";
				break;
			default :
				break;
			}
		}
	}

}
