package com.example.user.shopzelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_emailid;
    private EditText login_password;
    private Button login;
    private TextView join_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_emailid = (EditText)findViewById(R.id.login_email);
        login_password = (EditText)findViewById(R.id.login_pass);
        login = (Button)findViewById(R.id.login_button);
        join_now = (TextView)findViewById(R.id.join);
        login.setOnClickListener(this);
        join_now.setOnClickListener(this);
    }
    private void loginUser() {
        String emailid = login_emailid.getText().toString().trim();
        String password = login_password.getText().toString().trim();
        if (TextUtils.isEmpty(emailid)) {
            Toast.makeText(this, "Enater Email ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enater Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            String s = DBConnector.check_login(login_emailid.getText().toString(), login_password.getText().toString());
            if(s.equals("1")){
               // my_email = login_emailid.getText().toString();
                DBConnector.email = login_emailid.getText().toString();
                Intent i = new Intent(getApplicationContext(),ItemlistActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"User data invalid",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onClick(View v) {
        if(v == login){
            loginUser();
        }
        if(v == join_now){
           Intent i = new Intent(getApplicationContext(),SignupActivity.class);
            startActivity(i);
        }
    }
}
