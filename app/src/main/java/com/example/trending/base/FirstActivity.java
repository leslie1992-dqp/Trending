package com.example.trending.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

public abstract class FirstActivity<T extends FirstPrenster> extends AppCompatActivity implements View.OnClickListener {
    public T mT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco的设置
        Fresco.initialize(this);
        mT=getPrenster();
       mT.bindView(this);
        setContentView(getContentViewID());
        initView();
        initListener();
        initData();

    }
    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    public abstract int getContentViewID();
    public abstract T getPrenster();
}
