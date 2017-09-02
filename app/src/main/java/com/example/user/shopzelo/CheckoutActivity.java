package com.example.user.shopzelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    static int finalTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView checkout = (TextView) findViewById(R.id.txt_checkout);
        Button btn_f_checkout = (Button)findViewById(R.id.btn_finalcheck);

        String status = DBConnector.get_checkout_info(DBConnector.email);

        System.out.println("Status : "+status);
        if(status.equals("0")){
            checkout.setText("No Item In cart");
        }
        else{
            for(int i = 0; i < DBConnector.arrayList_product.size();i++ ){
                checkout.append("\nProduct : "+DBConnector.arrayList_product.get(i)+"\nPrice : "+DBConnector.arrayList_price.get(i)+"\nBooked Date : "+DBConnector.arrayList_date.get(i));

            }
            int noShops = Integer.parseInt(DBConnector.noShops);

            int totalPrice = 0;
            String price;

            for(int i = 0 ; i < DBConnector.arrayList_price.size() ; i++){
                price = DBConnector.arrayList_price.get(i).toString();
                price = price.substring(0, price.length()-1);

                totalPrice = totalPrice + Integer.parseInt(price);
            }

            noShops++;

            finalTotalPrice = totalPrice - (totalPrice * noShops/100);

            checkout.append("\n\nTotal amount : "+totalPrice);
            checkout.append("\nDiscount : "+noShops+" %");
            checkout.append("\n\nAmount after Discount : "+finalTotalPrice);

            System.out.println(totalPrice);
            btn_f_checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(getApplicationContext(),ShopLocationActivity.class));
                    startActivity(new Intent(getApplicationContext(),StorelistActivity.class));
                }
            });

        }


    }
}
