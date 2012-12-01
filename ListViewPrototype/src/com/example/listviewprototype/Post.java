package com.example.listviewprototype;

public class Post {

	private String mediaImageUrl;
	private String mediaName;
	private String postingTs; // TODO Date型にすべき？
	private String content;
	
	// TODO インタフェース要検討
	public Post(String mediaImageUrl, String mediaName, String postingTs, String content) {
		this.mediaImageUrl = mediaImageUrl;
		this.mediaName = mediaName;
		this.postingTs = postingTs;
		this.content = content;
	}
	
	public String getMediaImageUrl() {
		return this.mediaImageUrl;
	}
	
	public void setMediaImageUrl(String mediaImageUrl) {
		this.mediaImageUrl = mediaImageUrl;
	}

	public String getMediaName() {
		return this.mediaName;
	}
	
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	
	public String getPostingTs() {
		return this.postingTs;
	}
	
	public void setPostingTs(String postingTs) {
		this.postingTs = postingTs;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
