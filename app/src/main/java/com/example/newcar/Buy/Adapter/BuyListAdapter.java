package com.example.newcar.Buy.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newcar.Buy.Model.BuyListModel;
import com.example.newcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyListAdapter extends BaseAdapter {

    private Context context;
    private List<BuyListModel> models = new ArrayList<>();

    public BuyListAdapter(Context context, List<BuyListModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BuyListModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_listview,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.buy_lv_img);
        }
        return null;
    }

    class ViewHolder{
        ImageView img;
        TextView name,xinxi,time,km,area,price,newprice;
    }
}
