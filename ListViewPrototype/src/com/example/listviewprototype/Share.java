package com.example.listviewprototype;

public class Share {
	protected static Share mInstance = new Share();
	
	protected User mUser;
	
	public Share() {
		
	}
	
	public static Share getInstance() {
		return mInstance;
	}
	
	public User getUser() {
		return mUser;
	}
	
	public void setUser(User user) {
		mUser = user;
	}
	
	public static boolean isLoggedIn() {
		Share instance = getInstance();
		return (null != instance.getUser());
	}
}
