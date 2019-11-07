package com.example.newcar.Buy.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newcar.Buy.Model.BuyHotListModel;
import com.example.newcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyHotListAdapter extends BaseAdapter {
    private Context context;
    private List<BuyHotListModel> models = new ArrayList<>();

    public BuyHotListAdapter(Context context, List<BuyHotListModel> models) {
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
        BuyHotListModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_hot_lv_item,null);
            viewHolder.title = (TextView)convertView.findViewById(R.id.buy_hot_title);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.buy_hot_duihao);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(model.getTitle());
        viewHolder.img.setImageResource(model.getImg());
        return convertView;
    }

    class ViewHolder{
        TextView title;
        ImageView img;
    }

}
