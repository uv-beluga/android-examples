package com.example.listactivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPerformanceArrayAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] names;
	
	static class ViewHolder {
		public TextView text;
		public ImageView image;
	}

	public MyPerformanceArrayAdapter(Activity context, String[] names) {
		super(context, R.layout.rowlayout, names);
		this.context = context;
		this.names = names;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;
		// convertViewには(スクロールの結果等で)もう表示されなくなったビューが
		// 再利用目的で渡される
		// これを利用することでXMLのinfrateとJavaオブジェクトの構築を回避することができる
		if (rowView == null) { // まだ再利用できるビューがない場合
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rowlayout, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) rowView.findViewById(R.id.label);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(viewHolder);
		}
		
		// View Holderパターン
		// View#setTag()メソッドでヒモづけられたView Holderを介してViewにアクセスすることで
		// findViewById()メソッドを使わずにtextやimageにアクセスできる
		ViewHolder holder = (ViewHolder) rowView.getTag();
		String s = names[position];
		holder.text.setText(s);
		if (s.startsWith("Windows7") || s.startsWith("iPhone") || s.startsWith("Solaris")) {
			holder.image.setImageResource(R.drawable.no);
		} else {
			holder.image.setImageResource(R.drawable.ok);
		}
		
		return rowView;
	}
}
