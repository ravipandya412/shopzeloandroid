package com.example.user.shopzelo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private EditText reg_dob,reg_fname,reg_lname,reg_email,reg_address,reg_city,reg_zip,reg_password,reg_phoneno
            ,sign_up,log_in;
    private Button SignUp;
    private TextView login_view;
    String[] province = {"Onatrio","Qbec", "Alberta", "British Columbia", "Sasketchwan","Manitoba"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        reg_dob = (EditText)findViewById(R.id.reg_dob);
        reg_fname = (EditText)findViewById(R.id.reg_fname);
        reg_lname = (EditText)findViewById(R.id.reg_lname);
        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_address = (EditText)findViewById(R.id.reg_address);
        reg_city = (EditText)findViewById(R.id.reg_city);
        reg_zip = (EditText)findViewById(R.id.reg_zip);
        reg_password = (EditText)findViewById(R.id.reg_password);
        reg_phoneno = (EditText)findViewById(R.id.reg_phoneno);
        SignUp = (Button)findViewById(R.id.join_button);
        login_view = (TextView)findViewById(R.id.login_text);

        reg_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //
                // Calendar mcurrentDate=Calendar.getInstance();
                //mYear=mcurrentDate.get(Calendar.YEAR);
               // mMonth=mcurrentDate.get(Calendar.MONTH);
               // mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

               // DatePickerDialog mDatePicker=new DatePickerDialog(this, new OnDateSetListener() {
                  //  public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                    }
               // },mYear, mMonth, mDay);
               // mDatePicker.setTitle("Select date");
                //mDatePicker.show();
           // }
        });
        SignUp.setOnClickListener(this);
        login_view.setOnClickListener(this);


    }
    private void signupUser(){
        String first_name = reg_fname.getText().toString().trim();
        String last_name = reg_lname.getText().toString().trim();
        String address = reg_address.getText().toString().trim();
        String emailId = reg_email.getText().toString().trim();
        String city = reg_city.getText().toString().trim();
        String dob = reg_dob.getText().toString().trim();
       // String province = prov.getText().toString().trim();
        String zipcode = reg_zip.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String phoneno = reg_phoneno.getText().toString().trim();
        String name= first_name+" "+last_name;
        String add = address+" "+city+" "+zipcode;

        if (TextUtils.isEmpty(first_name)) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(last_name)) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
            return;
        }
         if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(emailId)) {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
         if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(zipcode)) {
            Toast.makeText(this, "Enter Zip Code", Toast.LENGTH_SHORT).show();
            return;
        }
         if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
         if (TextUtils.isEmpty(phoneno)) {
            Toast.makeText(this, "Enter Re-Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
             System.out.println("Here");
            DBConnector.user_signup(name, emailId, add, phoneno ,dob, password);
             finish();
             startActivity(new Intent(SignupActivity.this, LoginActivity.class));
             Toast.makeText(getApplicationContext(),"User Signup Successful",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),province[position] ,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onClick(View v) {
        if(v == SignUp){
           signupUser();
        }
        if(v == login_view){
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }
    }
}
