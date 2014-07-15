package com.siolabs.android.sqlite;

public class Item {

	private long id; 
	private String  desc;
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	@Override
	public String toString(){
		
		return desc;
	}
	
	
}
