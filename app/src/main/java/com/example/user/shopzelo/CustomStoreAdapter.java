package com.example.user.shopzelo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2017-04-20.
 */

public class CustomStoreAdapter  extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] storename;
    private final String[] storeaddress;
    private final Integer[] imgid;
    private final String[] storemob;
    public CustomStoreAdapter(Activity context, String[] storename,String[] storeaddress,String[] storemob, Integer[] imgid) {
        super(context, R.layout.store, storename);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.storename=storename;
        this.storeaddress=storeaddress;
        this.storemob=storemob;
        this.imgid=imgid;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.store, null,true);

        TextView store_name = (TextView) rowView.findViewById(R.id.store_name);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView store_address = (TextView) rowView.findViewById(R.id.store_address);
        TextView store_mob = (TextView) rowView.findViewById(R.id.store_mob);

        store_name.setText(storename[position]);
        icon.setImageResource(imgid[position]);
        store_address.setText(storeaddress[position]);
        store_mob.setText(storemob[position]);
        return rowView;
    };
}
