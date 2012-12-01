package com.example.listviewprototype;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends ArrayAdapter<Post> {
	private final List<Post> list;
	private final Activity context;
	
	public PostAdapter(Activity context, List<Post> list) {
		super(context, R.layout.rowpost_facebook, list);
		this.context = context;
		this.list = list;
	}
	
	static class ViewHolder {
		protected ImageView mediaImage;
		protected TextView mediaName;
		protected TextView postingTs;
		protected TextView content;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ViewHolderパターン(http://www.vogella.com/articles/AndroidListView/article.html#adapterperformance)
		View view = null;
		
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			view = inflater.inflate(R.layout.rowpost_facebook, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.mediaImage = (ImageView) view.findViewById(R.id.media_image);
			viewHolder.mediaName = (TextView) view.findViewById(R.id.media_name);
			viewHolder.postingTs = (TextView) view.findViewById(R.id.posting_ts);
			viewHolder.content = (TextView) view.findViewById(R.id.content);
			view.setTag(viewHolder);
		}

		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.mediaName.setText(list.get(position).getMediaName());
		holder.postingTs.setText(list.get(position).getPostingTs());
		holder.content.setText(list.get(position).getContent());

		// AsyncTask(http://d.hatena.ne.jp/Nagise/20120309/1331265123)
		AsyncTask<String, Void, Drawable> task = new AsyncTask<String, Void, Drawable>() {
			@Override
			protected Drawable doInBackground(String... url) {
				try {
					InputStream is = (InputStream) new URL(url[0]).getContent();
					Drawable d = Drawable.createFromStream(is, "src name");
					return d;
				} catch (Exception e) {
					System.out.println("Exc=" + e);
					return null;
				}
			}
			@Override
			protected void onPostExecute(Drawable drawable) {
				holder.mediaImage.setImageDrawable(drawable);
			}
		};
		task.execute(list.get(position).getMediaImageUrl());
		
		return view;
	}
}

