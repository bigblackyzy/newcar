package com.example.newcar.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.newcar.Home.Adapter.HomeHorGridAdapter;
import com.example.newcar.Home.Adapter.HomeVerGridAdapter;
import com.example.newcar.Home.model.HomeHorGridModel;
import com.example.newcar.Home.model.HomeVerGridModel;
import com.example.newcar.Home.utils.MyGridView;
import com.example.newcar.Map.GeoFence_District_Activity;
import com.example.newcar.R;
import com.example.newcar.refresh_load.BaseRefreshListener;
import com.example.newcar.refresh_load.PullToRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnBannerListener, BaseRefreshListener, View.OnClickListener {

    private View view;
    private Banner banner;
    private ArrayList<Integer> imgs;

    private MyGridView home_hor_grid;
    private List<HomeHorGridModel> hormodels = new ArrayList<>();
    private HomeHorGridAdapter horadapter;

    private MyGridView home_ver_grid;
    private List<HomeVerGridModel> vermodels = new ArrayList<>();
    private HomeVerGridAdapter veradapter;

    private PullToRefreshLayout pull;
    private int page = 1;

    private ImageView home_address_img;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home,container,false);
        initView();
        initHorGrid();
        initVerGrid();
        return view;
    }

    private void initVerGrid() {
        if (page==1){
            vermodels.clear();
            for (int i=0;i<20;i++){
                HomeVerGridModel model1 = new HomeVerGridModel(R.drawable.ddf,"车"+i,"201"+1+"款",
                        "201"+i+"年",i+"0万公里",i+i+i+i+"万","新车:"+i+i+i+2+"万");
                vermodels.add(model1);
            }
        }else {
            for (int i=0;i<20;i++){
                HomeVerGridModel model1 = new HomeVerGridModel(R.drawable.ddf,"车"+i,"201"+1+"款",
                        "201"+i+"年",i+"0万公里",i+i+i+i+"万","新车:"+i+i+i+2+"万");
                vermodels.add(model1);
            }
        }
    }

    private void initHorGrid() {
        HomeHorGridModel model1 = new HomeHorGridModel(R.mipmap.renwu,"查征信");
        hormodels.add(model1);
        HomeHorGridModel model2 = new HomeHorGridModel(R.mipmap.zhibo,"捡漏车");
        hormodels.add(model2);
        HomeHorGridModel model3 = new HomeHorGridModel(R.mipmap.shoufa,"限时秒杀");
        hormodels.add(model3);
        HomeHorGridModel model4 = new HomeHorGridModel(R.mipmap.paiw,"拍卖车");
        hormodels.add(model4);
        HomeHorGridModel model5 = new HomeHorGridModel(R.mipmap.geng,"车型上新");
        hormodels.add(model5);
        HomeHorGridModel model6 = new HomeHorGridModel(R.mipmap.shoufu,"0首付");
        hormodels.add(model6);
        HomeHorGridModel model7 = new HomeHorGridModel(R.mipmap.papo,"近期买车");
        hormodels.add(model7);
        HomeHorGridModel model8 = new HomeHorGridModel(R.mipmap.maiche,"近期卖车");
        hormodels.add(model8);
    }

    private void initView() {
        banner = (Banner)view.findViewById(R.id.home_banner);
        imgs = new ArrayList<>();
        imgs.add(R.drawable.aq);
        imgs.add(R.drawable.ddf);
        imgs.add(R.drawable.dq);
        imgs.add(R.drawable.er);
        imgs.add(R.drawable.p);
        banner.setImageLoader(new MyLoader());
        banner.setImages(imgs);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setBannerStyle(BannerConfig.RIGHT)
                .setOnBannerListener(this)
                .start();
        home_hor_grid = (MyGridView) view.findViewById(R.id.home_hor_grid);
        horadapter = new HomeHorGridAdapter(getContext(),hormodels);
        home_hor_grid.setAdapter(horadapter);

        home_ver_grid = (MyGridView)view.findViewById(R.id.home_ver_grid);
        veradapter = new HomeVerGridAdapter(getContext(),vermodels);
        home_ver_grid.setAdapter(veradapter);

        pull = (PullToRefreshLayout)view.findViewById(R.id.home_pull);
        pull.setRefreshListener(this);

        home_address_img  = (ImageView)view.findViewById(R.id.home_address_img);
        home_address_img.setOnClickListener(this);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void refresh() {
        page = 1;
        initHorGrid();
        initVerGrid();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishRefresh();
            }
        },2000);
    }

    @Override
    public void loadMore() {
        page++;
        initHorGrid();
        initVerGrid();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pull.finishLoadMore();
            }
        },2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_address_img:
                startActivity(new Intent(getContext(), GeoFence_District_Activity.class));
                break;
        }
    }

    private class MyLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
