package com.example.newcar.Buy;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.newcar.Buy.Adapter.BuyHotListAdapter;
import com.example.newcar.Buy.Adapter.BuyListAdapter;
import com.example.newcar.Buy.Model.BuyHotListModel;
import com.example.newcar.Buy.Model.BuyListModel;
import com.example.newcar.Home.utils.ScrollViewContainListView;
import com.example.newcar.R;
import com.example.newcar.refresh_load.BaseRefreshListener;
import com.example.newcar.refresh_load.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BuyFragment extends Fragment implements BaseRefreshListener, View.OnClickListener {

    private View view;
    private ScrollViewContainListView lv;
    private List<BuyListModel> models = new ArrayList<>();
    private BuyListAdapter adapter;

    private PullToRefreshLayout pull;
    private int pagr = 1;

    private ScrollViewContainListView buy_hot_lv;
    private BuyHotListAdapter hotListAdapter;
    private List<BuyHotListModel> hotmodels = new ArrayList<>();


    private TextView buy_old,buy_new,home_black_item;
    private LinearLayout hotup,hotdown,nameup,namedown,priceup,pricedown,moreup,moredown;
    private RelativeLayout buy_hot_visi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_buy,container,false);

        initData();
        initHotData();
        initView();
        return view;
    }

    private void initHotData() {
        BuyHotListModel model1 = new BuyHotListModel(R.drawable.duihao,"热门车源");
        hotmodels.add(model1);
        BuyHotListModel model2 = new BuyHotListModel(R.drawable.duihao,"最新上架");
        hotmodels.add(model2);
        BuyHotListModel model3 = new BuyHotListModel(R.drawable.duihao,"价格最低");
        hotmodels.add(model3);
        BuyHotListModel model4 = new BuyHotListModel(R.drawable.duihao,"里程最短");
        hotmodels.add(model4);
        BuyHotListModel model5 = new BuyHotListModel(R.drawable.duihao,"里程最短");
        hotmodels.add(model5);
        BuyHotListModel model6 = new BuyHotListModel(R.drawable.duihao,"车龄最短");
        hotmodels.add(model6);
    }

    private void initData() {
        for (int i=0;i<20;i++){
            BuyListModel model = new BuyListModel(R.drawable.aq,"汽车"+i,
                    "201"+i+"款"+i+".0L"+"精英版","201"+i+"年0"+i+"月",
                    i+".7万公里","武汉","40.0"+i+"万","新车:40.0"+i+"万");
            models.add(model);
        }
    }

    private void initView() {
        pull = (PullToRefreshLayout)view.findViewById(R.id.buy_pull);
        pull.setRefreshListener(this);
        lv = (ScrollViewContainListView)view.findViewById(R.id.buy_lv);
        adapter = new BuyListAdapter(getContext(),models);
        lv.setAdapter(adapter);

        buy_old = (TextView)view.findViewById(R.id.buy_old);
        buy_new = (TextView)view.findViewById(R.id.buy_new);
        buy_old.setOnClickListener(this);
        buy_new.setOnClickListener(this);

        buy_hot_lv = (ScrollViewContainListView)view.findViewById(R.id.buy_hot_lv);
        hotListAdapter = new BuyHotListAdapter(getContext(),hotmodels);
        buy_hot_lv.setAdapter(hotListAdapter);

        hotup = (LinearLayout)view.findViewById(R.id.hotup);
        hotup.setOnClickListener(this);
        hotdown = (LinearLayout)view.findViewById(R.id.hotdown);
        hotdown.setOnClickListener(this);

        buy_hot_visi = (RelativeLayout)view.findViewById(R.id.buy_hot_visi);
        home_black_item = (TextView) view.findViewById(R.id.home_black_item);
    }

    @Override
    public void refresh() {
        pagr = 1;
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishRefresh();
            }
        },2000);
    }

    @Override
    public void loadMore() {
        pagr++;
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishLoadMore();
            }
        },2000);
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_old:
                buy_old.setTextColor(Color.RED);
                buy_new.setTextColor(Color.GRAY);
                buy_old.setBackgroundResource(R.drawable.gray_search_back);
                buy_new.setBackgroundResource(R.drawable.title_search_back);
                break;
            case R.id.buy_new:
                buy_new.setTextColor(Color.RED);
                buy_old.setTextColor(Color.GRAY);
                buy_new.setBackgroundResource(R.drawable.gray_search_back);
                buy_old.setBackgroundResource(R.drawable.title_search_back);
                break;
            case R.id.hotup:
                hotup.setVisibility(View.GONE);
                hotdown.setVisibility(View.VISIBLE);
                buy_hot_visi.setVisibility(View.VISIBLE);
                break;
            case R.id.hotdown:
                hotup.setVisibility(View.VISIBLE);
                hotdown.setVisibility(View.GONE);
                buy_hot_visi.setVisibility(View.GONE);
                break;
            case R.id.home_black_item:
                hotup.setVisibility(View.VISIBLE);
                hotdown.setVisibility(View.GONE);
                buy_hot_visi.setVisibility(View.GONE);
                break;
                default:
        }
    }



}
