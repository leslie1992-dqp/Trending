package com.example.trending;

import android.util.Log;

import com.example.trending.base.FirstModel;

import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends FirstModel<MainPrenster> {
    public List<ListBean> mListBeans;
    public MainModel(MainPrenster mC) {
        super(mC);
    }

    public void getList(String language,String since,String spoken_language_code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        API api=retrofit.create(API.class);
        Observable<List<ListBean>> observable= api.getList("java","weekly");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ListBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<ListBean> listBeans) {
                        mListBeans=listBeans;
                        mC.setListBeans(mListBeans);
                        mC.setData();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                       e.printStackTrace();
                       mC.NonList();
                    }

                    @Override
                    public void onComplete() {
                        if (mListBeans != null) ;
                    }
                });
    }

}
