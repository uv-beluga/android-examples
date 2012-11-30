package com.example.listactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyList extends ListActivity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(this, getModel());
		setListAdapter(adapter);
	}
	
	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		list.get(1).setSelected(true);
		return list;
	}
	
	private Model get(String s) {
		return new Model(s);
	}
}
