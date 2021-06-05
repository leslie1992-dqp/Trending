package com.example.trending;

import android.util.Log;

import com.example.trending.base.FirstPrenster;

import java.util.List;

public class MainPrenster extends FirstPrenster<MainActivity,MainModel> {
    public List<ListBean> mListBeans;

    @Override
    public MainModel getModelInstance() {
        return new MainModel(this);
    }
    public void getList(String language, String since, String spoken_language_code){

        mB.getList(language,since,spoken_language_code);

    }

    public void setListBeans(List<ListBean> listBeans) {
        this.mListBeans = listBeans;
        Log.d("1","2"+mListBeans.size());
    }
    public void setData(){
        mA.setData(mListBeans);
    }

    public void NonList() {
        mA.changeView();
    }
}
