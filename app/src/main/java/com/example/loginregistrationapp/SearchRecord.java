package com.example.loginregistrationapp;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchRecord extends AppCompatActivity {

    TextView name, email;
    EditText searchName;
    Button btnSearch;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_record);

        name = (TextView) findViewById(R.id.tv_name);
        email = (TextView) findViewById(R.id.tv_email);
        searchName = (EditText) findViewById(R.id.et_search_name);
        btnSearch = (Button) findViewById(R.id.btn_search);

        databaseHelper = new DatabaseHelper(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(searchName.getText()))
                {
                    Toast.makeText(SearchRecord.this, "Please Enter name to Search", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cursor cursor = databaseHelper.searchData(searchName.getText().toString());

                    if(cursor.getCount()>0)
                    {
                        while(cursor.moveToNext())
                        {

                            name.setText(cursor.getString(1));
                            email.setText(cursor.getString(2));

                        }
                    }
                    else
                    {
                        Toast.makeText(SearchRecord.this, "Record Does Not Exist", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
