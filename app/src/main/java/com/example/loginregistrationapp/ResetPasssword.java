package com.example.loginregistrationapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasssword extends AppCompatActivity {

    EditText  email_forgetpass, password_update, name_forgetpass;
    Button btn_reset_pass, btn_chk_info;
    String chk_email;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passsword);

        email_forgetpass =(EditText) findViewById(R.id.et_email_forgotPass);
        name_forgetpass =(EditText) findViewById(R.id.et_name_forgotPass);
        password_update=(EditText) findViewById(R.id.et_reset_Pass);
        btn_chk_info=(Button) findViewById(R.id.btn_chk_info);
        btn_reset_pass=(Button) findViewById(R.id.btn_resetpassword);

        password_update.setVisibility(View.INVISIBLE);
        btn_reset_pass.setVisibility(View.INVISIBLE);

        databaseHelper = new DatabaseHelper(this);

        btn_chk_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name_forgetpass.getText().toString().trim()))
                {
                    Toast.makeText(ResetPasssword.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(email_forgetpass.getText().toString().trim()))
                {
                    Toast.makeText(ResetPasssword.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor = databaseHelper.resetPassword(name_forgetpass.getText().toString().trim(),email_forgetpass.getText().toString().trim());

                if(cursor.getCount()>0)
                {
                    password_update.setVisibility(View.VISIBLE);
                    btn_reset_pass.setVisibility(View.VISIBLE);
                    email_forgetpass.setEnabled(false);
                    name_forgetpass.setEnabled(false);
                }
                else
                {
                    Toast.makeText(ResetPasssword.this, "Wrong Information", Toast.LENGTH_SHORT).show();
                    password_update.setVisibility(View.INVISIBLE);
                    btn_reset_pass.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_reset_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean res = databaseHelper.updatePassword(email_forgetpass.getText().toString(), name_forgetpass.getText().toString(), password_update.getText().toString());

                if(res)
                {
                    Toast.makeText(ResetPasssword.this, "Password Update", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ResetPasssword.this, Login.class);
                    startActivity(intent);

                }


            }
        });
    }
}
