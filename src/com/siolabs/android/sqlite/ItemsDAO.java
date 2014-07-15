package com.siolabs.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDAO {

	//db details
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns  = {"_id", "item"};

	public ItemsDAO(Context context){
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();

	}

	public void close(){
		dbHelper.close();
	}

	//read a single item
	public Item getItem(long id){


		Cursor cursor = database.query("items",
				allColumns, "_id" + " = " + id, null,
				null, null, null);

		cursor.moveToFirst();
		return cursorToItem(cursor);

	}

	//create a single item
	public Item createItem(String item) {
		ContentValues values = new ContentValues();
		values.put("item", item);
		long insertId = database.insert("items", null,
				values);
		Cursor cursor = database.query("items",
				allColumns, "_id" + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Item newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}

	
	//delete an item
	public void deleteItem(Item item) {
		long id = item.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete("items", "desc"
				+ " = " + id, null);
	}

	//get all items
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();

		Cursor cursor = database.query("items",
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Item item = cursorToItem(cursor);
			items.add(item);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return items;
	}
	
	//update an item
	public boolean update(long id, String desc){
		
		ContentValues values = new ContentValues();
		values.put("item", desc);
		
		database.update("items", values, "_id = "+id, null);
		
		return true;
	}

	//just get the Item where the cursor currently points
	private Item cursorToItem(Cursor cursor) {
		Item item  = new Item();
		item.setId(cursor.getLong(0));
		item.setDesc(cursor.getString(1));
		return item;
	}


}
