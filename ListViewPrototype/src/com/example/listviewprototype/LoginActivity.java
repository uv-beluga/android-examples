package com.example.listviewprototype;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button loginPost = (Button)findViewById(R.id.loginPost);
		
		loginPost.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TextView loginEmail = (TextView)findViewById(R.id.loginEmail);
				TextView loginPassword = (TextView)findViewById(R.id.loginPassword);
				
				String email = loginEmail.getText().toString();
				String password = loginPassword.getText().toString();
				
				/*
				AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
				alert.setTitle("ログイン");
				alert.setMessage(email + "/" + password);
				alert.setPositiveButton("OK", null);
				alert.show();
				*/
				
				WebService webService = new WebService();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("email", email);
				map.put("password", password);
				
				//webService.get(map);
				
				User user = new User();
				user.setUsername(email);
				
				Share share = Share.getInstance();
				share.setUser(user);
				
				finish();
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		if (Share.isLoggedIn()) {
			finish();
		}
	}
}
