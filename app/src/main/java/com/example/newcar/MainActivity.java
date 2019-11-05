package com.example.newcar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.newcar.Buy.BuyFragment;
import com.example.newcar.Home.HomeFragment;
import com.example.newcar.Map.GeoFence_District_Activity;
import com.example.newcar.My.MyFragment;
import com.example.newcar.Sell.SellFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb_home,rb_buy,rb_sell,rb_my;
    private RadioGroup mRadioGroup;
    private FrameLayout mFragment;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private BuyFragment buyFragment;
    private SellFragment sellFragment;
    private MyFragment myFragment;
    private Fragment curragefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags())==0){
            transaction = getSupportFragmentManager().beginTransaction();
            hideFragments(transaction);
            if (homeFragment == null){
                homeFragment = new HomeFragment();
                transaction.add(R.id.mFragment,homeFragment);
            }else {
                transaction.show(homeFragment);
            }
            transaction.commit();
            rb_home.setClickable(true);
            rb_buy.setClickable(false);
            rb_sell.setClickable(false);
            rb_my.setClickable(false);
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if (buyFragment!=null){
            transaction.hide(buyFragment);
        }
        if (sellFragment!=null){
            transaction.hide(sellFragment);
        }
        if (myFragment!=null){
            transaction.hide(myFragment);
        }
    }

    private void initView() {
        mRadioGroup = (RadioGroup)findViewById(R.id.mRadioGroup);
        rb_home = (RadioButton)findViewById(R.id.rb_home);
        rb_buy = (RadioButton)findViewById(R.id.rb_classify);
        rb_sell = (RadioButton)findViewById(R.id.rb_mai);
        rb_my = (RadioButton)findViewById(R.id.rb_my);
        initFragment();
        wtDrawableTop();
    }

    private void wtDrawableTop() {
        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = rb_home;
        rbs[1] = rb_buy;
        rbs[2] = rb_sell;
        rbs[3] = rb_my;

        for (RadioButton rb : rbs){
            Drawable[] drawables = rb.getCompoundDrawables();
            Rect rect = new Rect(0,10,100,100);
            drawables[1].setBounds(rect);
            rb.setCompoundDrawables(null,drawables[1],null,null);
        }
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        buyFragment = new BuyFragment();
        sellFragment = new SellFragment();
        myFragment = new MyFragment();
        curragefragment = new Fragment();
        curragefragment = homeFragment;

        getSupportFragmentManager().beginTransaction().add(R.id.mFragment,curragefragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.mFragment,buyFragment).hide(buyFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.mFragment,sellFragment).hide(sellFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.mFragment,myFragment).hide(myFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                if (curragefragment == homeFragment){
                    return;
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(curragefragment).show(homeFragment).commit();
                    curragefragment = homeFragment;
                }
                break;
            case R.id.rb_classify:
                if (curragefragment == buyFragment){
                    return;
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(curragefragment).show(buyFragment).commit();
                    curragefragment = buyFragment;
                }
                break;
            case R.id.rb_mai:
                if (curragefragment == sellFragment){
                    return;
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(curragefragment).show(sellFragment).commit();
                    curragefragment = sellFragment;
                }
                break;
            case R.id.rb_my:
                if (curragefragment == myFragment){
                    return;
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(curragefragment).show(myFragment).commit();
                    curragefragment = myFragment;
                }
                break;
        }
    }
}
