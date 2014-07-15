package com.siolabs.todolistwithdb;

import com.siolabs.android.sqlite.ItemsDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Details extends Activity {

	EditText eText1;
	long id;
	int pos;
	
	ItemsDAO itemsDAO ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		itemsDAO = new ItemsDAO(this);
		itemsDAO.open();
		
		Intent i = getIntent();
		
		 id  = i.getExtras().getLong("id");
		
		
		eText1 = (EditText)findViewById(R.id.editText1);
		eText1.setText(itemsDAO.getItem(id).getDesc());
		
	}
	
	
	public void update(View view){
		
		this.finish();
	}


	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		
		
		itemsDAO.update(id, eText1.getText().toString());
		
		Intent intent = new Intent();
		intent.putExtra("action","success");
		
		setResult(10, intent);		
		super.finish();
	}


	
	
	

}
