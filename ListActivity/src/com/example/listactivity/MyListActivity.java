package com.example.listactivity;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;

public class MyListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS",
				"Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu",
				"Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7",
				"Max OS X", "Linux", "OS/2", "Android", "iPhone",
				"WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
				"Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Android", "iPhone", "WindowsMobile", "Blackberry",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu",
				"Windows7", "Max OS X", "Linux", "OS/2" };
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_list, menu);
		return true;
	}

}
