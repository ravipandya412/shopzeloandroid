package com.example.user.shopzelo;

import android.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StorelistActivity extends AppCompatActivity implements View.OnClickListener {
    ListView store_list;
    int position;
    private Button btn_viewlocation;
    String[] storename = {
            "Apple Store",
            "BestBuy Store",
            "Canadian Tire Store",
            "Samsung",
            "Wireless Wave",
    };
    String[] storeadreess = {
            "CF Fairview Mall,1800 Sheppard Ave E, M2J 5A7",
            "Scarborough Town Centre, 480 Progress Ave,, ON M1P 5J1",
            "4650 Sheppard Ave E, M1S 3J7",
            "Scarborough Town Centre, 300 Borough Dr #95, ON M1P 4P5",
            "Scarborough Town Centre, 300 Borough Drive, Upper Level, M1P 4P5"

    };
    Integer[] imgid = {
            R.drawable.store1,
            R.drawable.store2,
            R.drawable.store3,
            R.drawable.store4,
            R.drawable.store5,
    };
    String[] storemob = {
            "647-123-9876",
            "897-908-9875",
            "416-781-9890",
            "786-876-0986",
            "897-908-6742",
    };
    ArrayList arrayList_mob = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist);
        btn_viewlocation = (Button) findViewById(R.id.btn_viewlocation);
        CustomListAdapter adapter = new CustomListAdapter(this, storename, storeadreess, imgid);
        store_list = (ListView) findViewById(R.id.store_list);

        store_list.setAdapter(adapter);


        arrayList_mob.add("78979089085");
        arrayList_mob.add("78979089086");
        arrayList_mob.add("78979089087");
        arrayList_mob.add("78979089088");
        arrayList_mob.add("78979089089");
        btn_viewlocation.setOnClickListener(this);

        store_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                final int new_po = position;
                long num = Long.parseLong(arrayList_mob.get(position).toString());

                AlertDialog.Builder builder1 = new AlertDialog.Builder(StorelistActivity.this);
                builder1.setMessage("Want to call " + storename[position] + " ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (ActivityCompat.checkSelfPermission(StorelistActivity.this, android.Manifest.permission.CALL_PHONE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(StorelistActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 123);
                                } else {

                                    Intent itemCALL = new Intent(Intent.ACTION_CALL);
                                    itemCALL.setData(Uri.parse("tel:" + arrayList_mob.get(position).toString()));
                                    startActivity(itemCALL);
                                }


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

                return false;
            }

        });
        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), BuyActivity.class);
                i.putExtra("sname", position + "");
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btn_viewlocation) {
            Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(i);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_ok:

                Intent i = new Intent(getApplicationContext(), ShopLocationActivity.class);
                startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

}

//    /*@Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
//    {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle("Select The Action");
//        menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
//        //menu.add(0, v.getId(), 0, "SMS");
//        System.out.println("hello");
//    }
//
//    public boolean onContextItemSelected(MenuItem item,final int position) {
//        if (ActivityCompat.checkSelfPermission(StorelistActivity.this, android.Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(StorelistActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 123);
//        } else {
//            Intent itemCALL = new Intent(Intent.ACTION_CALL);
//            itemCALL.setData(Uri.parse("tel:" + storemob[position]));
//            startActivity(itemCALL);
//        }
//        return false;
//
//    }
//*/

// @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    final ContextMenu.ContextMenuInfo menuInfo) {
//        MenuItem itemCALL = menu.add("Call");
//        itemCALL.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (ActivityCompat.checkSelfPermission(StorelistActivity.this, android.Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(StorelistActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 123);
//                } else {
//                    //final int position = 0;
//                    Intent itemCALL = new Intent(Intent.ACTION_CALL);
//                    itemCALL.setData(Uri.parse(storemob[position]));
//                    startActivity(itemCALL);
//                }
//                return false;
//            }
//        });
//
//
//    }
