package com.example.adapter;

import java.util.List;

import com.example.bean.Good;
import com.example.liqunabin_1308e.R;
import com.example.util.LoadImage;
import com.example.util.LoadImage.IloadImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodAdapter extends BaseAdapter {

	List<Good> Item;
	Context context;
	static final String url = "http://192.168.1.33:8080/page_a/";
	
	public GoodAdapter(List<Good> item, Context context) {
		super();
		Item = item;
		this.context = context;
	}

	@Override
	public int getCount() {
		return Item.size();
	}

	@Override
	public Object getItem(int position) {
		return Item.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class Holder{
		ImageView iv;
		TextView title,price,level;
	}
	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final Holder holder;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			holder = new Holder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.title = (TextView) convertView.findViewById(R.id.name);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.level = (TextView) convertView.findViewById(R.id.level);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		
		new LoadImage().getImage(url+Item.get(position).getUrl(), new IloadImage() {
			
			@Override
			public void load(String url, Bitmap bitmap) {
				
				holder.iv.setImageBitmap(bitmap);
				
			}
		});
		holder.title.setText(Item.get(position).getTitle());
		holder.price.setText(Item.get(position).getPrice());
		holder.level.setText(Item.get(position).getLevel());
		return convertView;
	}

}
