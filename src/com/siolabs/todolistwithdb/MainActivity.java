package com.siolabs.todolistwithdb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.siolabs.android.sqlite.Item;
import com.siolabs.android.sqlite.ItemsDAO;

public class MainActivity extends Activity {

	EditText eText;
	ListView lv ;
	
	List<Item> data;
	ArrayAdapter<Item> aa;
	
	ItemsDAO itemsDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eText = (EditText)findViewById(R.id.editText1);
		lv = (ListView)findViewById(R.id.listView1);
		
		itemsDAO = new ItemsDAO(this);
		itemsDAO.open();
		
		data = itemsDAO.getAllItems();
		
		aa = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, data);
		
		lv.setAdapter(aa);
		
		//function for click on list view item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				String s = data.get(position).getDesc();
				long itemId  = data.get(position).getId();
				
				Intent i = new Intent(getApplicationContext(), Details.class);
				
				i.putExtra("id", itemId);
				
				startActivityForResult(i, 10);
				
			}
		});
		
		
		
	}
	
	

	
	
	
	public void addToList(View view){
		
		Item item = null;
		String s  = eText.getText().toString();
		item = itemsDAO.createItem(s);
		aa.add(item);
		
		aa.notifyDataSetChanged();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	Toast.makeText(this, "Updated", Toast.LENGTH_SHORT);
	this.data = itemsDAO.getAllItems();
	aa.clear();
	aa.addAll(this.data);
	super.onActivityResult(requestCode, resultCode, data);		
		
		
	}
}
