package com.rymal.games.common.content;

public class TitleItem {
	public final String _id;
	public final String _content;

	public TitleItem(String id, String content) {
		_id = id;
		_content = content;
	}
	
	public String getId() {
		 return _id;
	}
	
	public String getContent() {
		return _content;
	}
	
	public String toString() {
		return getContent();
	}
}