package com.example.user.shopzelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        TextView txt_store = (TextView)findViewById(R.id.txt_store);
        TextView txt_price = (TextView)findViewById(R.id.txt_price);
        Button btn_payment = (Button)findViewById(R.id.btn_payment);

        Bundle b = getIntent().getExtras();
        String pos = b.getString("sname");

        if(pos.equals("0")){
           txt_store.setText("Apple Store");
        }
        else if(pos.equals("1")){
            txt_store.setText("BestBuy Store");
        }
        else if(pos.equals("2")){
            txt_store.setText("Canadian Tire Store");
        }
        else if(pos.equals("3")){
            txt_store.setText("Samsung Store");
        }
        else if(pos.equals("4")){
            txt_store.setText("Wirelesswave Store");

        }

        for(int i=0; i< ItemlistActivity.arralist_itemname.size();i++){
            txt_store.append("\n"+ItemlistActivity.arralist_itemname.get(i).toString());
        }


    txt_price.append("\n\n"+CheckoutActivity.finalTotalPrice+"");

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(i);
            }
        });
    }
}
