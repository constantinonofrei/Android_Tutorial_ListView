package com.example.conofrei.listview;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by conofrei on 11/1/2016.
 */
public class CustomAdapter extends ArrayAdapter<RowBean>{
    public CustomAdapter(Context context, RowBean[] resource) {
        super(context, R.layout.list_row, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater render = LayoutInflater.from(getContext());
        View listView = render.inflate(R.layout.list_row, parent, false);

        RowBean item = getItem(position);
        TextView tv = (TextView)listView.findViewById(R.id.textView);
        ImageView iv =(ImageView)listView.findViewById(R.id.imageView);
        tv.setText(item.getName());
        iv.setImageResource(item.getImgRsc());
        return listView;
    }


}
