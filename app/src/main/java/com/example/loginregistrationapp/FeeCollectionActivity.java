package com.example.loginregistrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeeCollectionActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    TextView textStudentName;
    Button buttonSave;
    EditText fees, due;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_collection);

        dbHelper = new DatabaseHelper(this);
        textStudentName = findViewById(R.id.Student_name);
        buttonSave = findViewById(R.id.btnsave);
        fees = findViewById(R.id.fees_edit_text);
        due = findViewById(R.id.duefees_edit_text);
        Intent intent = getIntent();
        String text = intent.getStringExtra("name");
        textStudentName.setText(text);

        //boolean result = dbHelper.insertData_fees(textStudentName.getText().toString(), _fees, _due);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _fees = Integer.parseInt(fees.getText().toString());
                int _due = Integer.parseInt(due.getText().toString());
                boolean result = dbHelper.insertData_fees(_fees, _due, textStudentName.getText().toString());
                if(result)
                {
                    Toast.makeText(FeeCollectionActivity.this, "Save", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(FeeCollectionActivity.this, "Not Save", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
