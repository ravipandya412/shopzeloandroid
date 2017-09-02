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

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final String[] itemprice;
    private final Integer[] imgid;
public CustomListAdapter(Activity context, String[] itemname,String[] itemprice, Integer[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemprice=itemprice;
        this.imgid=imgid;
        }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView item = (TextView) rowView.findViewById(R.id.item);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView item_price = (TextView) rowView.findViewById(R.id.item_price);

        item.setText(itemname[position]);
        icon.setImageResource(imgid[position]);
        item_price.setText(itemprice[position]);
        return rowView;
    };
}
