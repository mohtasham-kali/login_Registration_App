package com.example.loginregistrationapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ShowAllRecord extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_record);
        listView = (ListView) findViewById(R.id.listview);
        databaseHelper = new DatabaseHelper(this);

        displayAllData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ShowAllRecord.this, FeeCollectionActivity.class);
                intent.putExtra("name", text);
                startActivity(intent);
//                Toast.makeText(ShowAllRecord.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayAllData() {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = databaseHelper.allData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data in Database", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                arrayList.add(cursor.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
