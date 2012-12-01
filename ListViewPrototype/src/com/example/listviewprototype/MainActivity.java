package com.example.listviewprototype;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnItemClickListener,
	OnItemLongClickListener {

	//フリックで画面を横スクロールする方法(http://www.toyomane.com/jm/develop-memo/android/101.html)
    //前・今・次ページのインスタンス
    ListView mListView1;
    ListView mListView2;

    // ViewFlipperのインスタンス
    ViewFlipper mViewFlipper;

    // ジェスチャー
    GestureDetector mGestureDetector;

    // アニメーション
    Animation mInFromLeft;
    Animation mOutToRight;
    Animation mInFromRight;
    Animation mOutToLeft;
    
    // タッチ処理リスナー
    OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (mGestureDetector.onTouchEvent(event))
                return true;
            return false;
        }
    };

    // ジェスチャーリスナー
    OnGestureListener mGestureListener = new OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent arg0) {
            return false;
        }

        // フリック処理を実装
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            float dx = Math.abs(velocityX);
            float dy = Math.abs(velocityY);
            if (dx > dy && dx > 150) {
                if (e1.getX() < e2.getX()) {
                    mViewFlipper.setInAnimation(mInFromLeft);
                    mViewFlipper.setOutAnimation(mOutToRight);
                    mViewFlipper.showPrevious();

//                    setDate(mFromDate);
                } else {
                    mViewFlipper.setInAnimation(mInFromRight);
                    mViewFlipper.setOutAnimation(mOutToLeft);
                    mViewFlipper.showNext();

//                    setDate(mToDate);
                }
                searchList();
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent arg0) {
        }

        @Override
        public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                float arg3) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent arg0) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent arg0) {
            return false;
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<Post> adapter1 = new PostAdapter(this, getPosts());
		ArrayAdapter<Post> adapter2 = new PostAdapter(this, getPosts2());
		
		ListView lv1 = (ListView)findViewById(R.id.listView1);
		lv1.setAdapter(adapter1);
		ListView lv2 = (ListView)findViewById(R.id.listView2);
		lv2.setAdapter(adapter2);
		
		initFlipper();
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

	private List<Post> getPosts2() {
		List<Post> list = new ArrayList<Post>();
		list.add(get(
				"",
				"yamamoto",
				"11月28日 10:00",
				"twitterへの投稿内容を表示する。\n" +
						"単一行で表示？\n" +
						"URLはリンクに変換できるか？\n",
				"高瀬 憲祐"
				));
		list.add(get(
				"",
				"hoshino",
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
	
	private void initFlipper() {
        // 画面オブジェクトを取得
        mListView1 = (ListView) findViewById(R.id.listView1);
        mListView2 = (ListView) findViewById(R.id.listView2);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
        // ジェスチャーを生成
        mGestureDetector = new GestureDetector(this, mGestureListener);

        // アニメーションを生成
        mInFromLeft = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        mOutToRight = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        mInFromRight = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        mOutToLeft = AnimationUtils.loadAnimation(this, R.anim.out_to_left);

        // リストの選択リスナーを設定
        mListView1.setOnItemClickListener(this);
        mListView2.setOnItemClickListener(this);
        mListView1.setOnItemLongClickListener(this);
        mListView2.setOnItemLongClickListener(this);

        // フリック処理のためのイベント設定
        mListView1.setOnTouchListener(mTouchListener);
        mListView2.setOnTouchListener(mTouchListener);

        // 初期データ読み込み
        searchList();
	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {

    	/*
        // 選択したアイテムから日付を取得
        CursorAdapter adapter = (CursorAdapter) parent.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null) {
            // MemoActivityを呼び出すIntentを生成
            Intent intent = new Intent(this, MemoActivity.class);
            // パラメーターに選択した日付を設定
            intent.putExtra(Defines.KEY_DATE, cursor.getLong(cursor
                    .getColumnIndex(DatabaseHelper.FIELD_DATE)));
            // Intent呼び出しを実行
            startActivity(intent);
        }
        */
    }

    private void searchList() {
    	/*
            // 検索したデータをもとにアダプターを生成
            DiaryAdapter adapter = new DiaryAdapter(this, mCursor);

            // アダプターをListViewに設定
            GridView gridview = (GridView) mViewFlipper.getCurrentView();
            gridview.setAdapter(adapter);
        }
        */
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
