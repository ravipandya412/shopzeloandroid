package com.example.user.shopzelo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.user.shopzelo.DBConnector.email;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText cardnum,expdate,p_date,amount;
    private Button btn_finalpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cardnum = (EditText)findViewById(R.id.cardno);
        expdate = (EditText)findViewById(R.id.expdate);
        p_date = (EditText)findViewById(R.id.p_date);
        amount = (EditText)findViewById(R.id.amount);
        btn_finalpay = (Button)findViewById(R.id.btn_finalpay);
        btn_finalpay.setOnClickListener(this);
    }
    private void payamount(){
        String card_num = cardnum.getText().toString();
        String exp_date = expdate.getText().toString();
        String pdate = p_date.getText().toString();
        String pay_amount = amount.getText().toString();
        if (TextUtils.isEmpty(card_num)) {
            Toast.makeText(this, "Enter Card Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(exp_date)) {
            Toast.makeText(this, "Enter Expiry Date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pdate)) {
            Toast.makeText(this, "Enter Payment Date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pay_amount)) {
            Toast.makeText(this, "Enter Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            DBConnector.go_payment(DBConnector.email,CheckoutActivity.finalTotalPrice+"",pdate ,card_num,exp_date);

            Toast.makeText(PaymentActivity.this, "Your order has been placed." , Toast.LENGTH_SHORT).show();

            CheckoutActivity.finalTotalPrice = 0;

            finish();
            startActivity(new Intent(PaymentActivity.this, ItemlistActivity.class));
        }
    }
    @Override
    public void onClick(View v) {
        if(v == btn_finalpay){
            payamount();
        }
    }

}

