package com.example.demopaserjson.adapterdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demopaserjson.MainActivity;
import com.example.demopaserjson.PlayerVideoActivity2;
import com.example.demopaserjson.R;
import com.example.demopaserjson.objectdemo.Item;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {


    public ImageView imvThum;
    private Context context;
    private ArrayList<Item> arrContact;

    public ItemAdapter(Context context, ArrayList<Item> arr) {
        this.context = context;
        this.arrContact = arr;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Item> getArrContact() {
        return arrContact;
    }

    public void setArrContact(ArrayList<Item> arrContact) {
        this.arrContact = arrContact;
    }

    @Override
    public int getCount() {
        return arrContact.size();
    }

    @Override
    public Object getItem(int i) {
        return arrContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Item item = arrContact.get(i);

        Log.e("eee", "currentPage" + item.getUrl());

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();// khoửi tạo inflater

        View rowView = inflater.inflate(R.layout.item_view, viewGroup, false);// từ ìnflater ta tạo ra một cái layout item giao diện view
//        TextView tvNAme = rowView.findViewById(R.id.tvName);
//        tvNAme.setText(item.get_id());


        TextView tvstt = rowView.findViewById(R.id.tvstt);
        tvstt.setText((i + 1) + "");

        imvThum = rowView.findViewById(R.id.imThum);


        if (item.getImg() != "")

            Glide.with(context).load(item.getImg()).into(imvThum);
        return rowView;







    }

}