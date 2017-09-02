package com.example.user.shopzelo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemlistActivity extends AppCompatActivity {
    ListView list_view;
    String[] itemname ={
            "Wirless Bluetooth",
            "Headphone",
            "Pendrive",
            "Hard Drive",
            "Hands Free",
            "Wireless Speaker ",
            "Apple Charger",
            "Mobile Case"
    };
    String[] itemprice ={
            "30$",
            "40$",
            "25$",
            "45$",
            "55$",
            "23$ ",
            "24$",
            "21$"
    };
    Integer[] imgid={
            R.drawable.pro1,
            R.drawable.prod2,
            R.drawable.prod3,
            R.drawable.prod4,
            R.drawable.prod5,
            R.drawable.prod6,
            R.drawable.prod7,
            R.drawable.prod8,
            R.drawable.prod9,
    };

    static  ArrayList arralist_itemname = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        CustomListAdapter adapter=new CustomListAdapter(this, itemname,itemprice, imgid);
        list_view=(ListView)findViewById(R.id.item_list);
        list_view.setAdapter(adapter);
        Button btn_checkout = (Button)findViewById(R.id.btn_checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemlistActivity.this, CheckoutActivity.class));
            }
        });
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView_itemName = (TextView)view.findViewById(R.id.item);
                TextView textView_itemPrice = (TextView)view.findViewById(R.id.item_price);
                final String selecteditemname = textView_itemName.getText().toString();
                final String selecteditemPrice = textView_itemPrice.getText().toString();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ItemlistActivity.this);
                builder1.setTitle("Product : "+selecteditemname+"\nPrice : "+selecteditemPrice);
                builder1.setMessage("Add to cart ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                arralist_itemname.add(selecteditemname);
                                DBConnector.add_cart(selecteditemname, selecteditemPrice);


                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
}
