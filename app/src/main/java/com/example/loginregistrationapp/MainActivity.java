package com.example.loginregistrationapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    Button btn_register;
    TextView tv_login;
    DatabaseHelper databaseHelper;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_pass);
        tv_login =(TextView) findViewById(R.id.tv_login);

        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(name.getText().toString().trim()))
                {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(email.getText().toString().trim()))
                {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password.getText().toString().trim()))
                {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean result = databaseHelper.insertData(name.getText().toString(),email.getText().toString(),password.getText().toString());
                if(result)
                {
                    Toast.makeText(MainActivity.this, "Student Registered...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,MainMenu.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Registration Failed...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }
}

