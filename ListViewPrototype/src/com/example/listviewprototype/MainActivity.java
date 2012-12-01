package com.example.listviewprototype;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<Post> adapter = new PostAdapter(this, getPosts());
		setListAdapter(adapter);
	}

	private List<Post> getPosts() {
		List<Post> list = new ArrayList<Post>();
		list.add(get(
				"http://icons-search.com/img/icons-land/IconsLandVistaStyleEmoticonsDemo.zip/IconsLandVistaStyleEmoticonsDemo-PNG-256x256-Cool.png-256x256.png",
				"takase page",
				"11月28日 10:00",
				"facebookページへの投稿内容を表示する。\n" +
						"複数行で表示。\n" +
						"URLはリンクに変換できるか？\n",
				"高瀬 憲祐"
				));
		list.add(get(
				"http://icons-search.com/img/icons-land/IconsLandVistaStyleEmoticonsDemo.zip/IconsLandVistaStyleEmoticonsDemo-PNG-256x256-Adore.png-256x256.png",
				"tajima",
				"11月28日 12:00",
				"URLはリンクに変換できるか？\n" +
						"→できそうです。\n" +
						"https://www.google.co.jp/ \n",
				"高瀬 憲祐"
				));
		return list;
	}
	
	// 個々らへんをモデルに抽象化する
	private Post get(String mediaImageUrl, String mediaName, String postingTs, String content, String insertUserName) {
		return new Post(mediaImageUrl, mediaName, postingTs, content, insertUserName);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
