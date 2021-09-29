package com.example.loginregistrationapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText et_email, et_password;
    private Button btnLogin;
    private TextView tv_forgotpass,tv_singup;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email_login);
        et_password = (EditText) findViewById(R.id.et_pass_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tv_forgotpass=(TextView)findViewById(R.id.tv_forgetPass);
        tv_singup=(TextView)findViewById(R.id.tv_signup);

        databaseHelper = new DatabaseHelper(this);

        tv_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,ResetPasssword .class);
                startActivity(intent);
            }
        });

        tv_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity .class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(et_email.getText().toString().trim()))
                {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(et_password.getText().toString().trim()))
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }



                Cursor cursor = databaseHelper.login(et_email.getText().toString(),et_password.getText().toString());

                if(cursor.getCount()>0)
                {
                    Intent intent = new Intent(Login.this,MainMenu .class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Login.this, "Password may be wrong ", Toast.LENGTH_SHORT).show();;
                }



            }
        });
    }
}