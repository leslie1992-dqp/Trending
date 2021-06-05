package com.example.trending;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trending.base.FirstActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FirstActivity<MainPrenster> {

    public RecyclerView mRecyclerView;
    private List<ListBean> mData;
    private List<ListBean> mData2;
    private SwipeRefreshLayout refreshLayout;
    private int flag=0;
    private Button mBtn_retry;

    @Override
    public void initView() {
          //初始视图
        mRecyclerView=findViewById(R.id.recyclerview);
        mBtn_retry=findViewById(R.id.retry);
    }

    @Override
    public void initListener() {
        if (flag==1)
            mBtn_retry.setOnClickListener(this);

    }

    @Override
    public void initData() {
        //刷新的功能
        refreshLayout=findViewById(R.id.refresh);
        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mT.getList(null,"weekly",null);

            }
        });
        mT.getList(null,"weekly",null);
    }

    @Override
    public int getContentViewID() {
        //根据flag的值显示不同的界面
        switch (flag){
            case 0:
                return R.layout.activity_main;
            default:
                return R.layout.retry_look;

        }
    }

    @Override
    public MainPrenster getPrenster() {
        return new MainPrenster();
    }

    @Override
    //可选择的菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,1,"星星排序");
        menu.add(0,1,1,"名字排序");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mData==null) return false;
        mData2=new ArrayList<>(mData.size());
        switch (item.getItemId()){
            //将数据重新排序，根据星星的多少
            case 0:
                while (mData.size()!=0){
                    int i=0;
                    ListBean single =mData.get(i);
                    for(int j=i+1;j<mData.size();j++) {
                        if (mData.get(j).getStars() > single.getStars()){
                            single = mData.get(j);
                        }
                    }
                    mData.remove(single);
                    mData2.add(single);
                }
                setData(mData2);
                return  true;
            case 1:
                while (mData.size()!=0){
                    int i=0;
                    ListBean single =mData.get(i);
                    for(int j=i+1;j<mData.size();j++){
                        //charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length()-1
                        //取字符长度较小的那个作比较
                        int a=single.getName().length();
                        int b=mData.get(j).getName().length();
                        int c=a > b ? b :a;
                        for(int k=0;k<=c;k++) {
                            if (Character.toLowerCase(mData.get(j).getName().charAt(k))
                                    < Character.toLowerCase(single.getName().charAt(k)))
                                single = mData.get(j);
                        }
                    }
                    mData.remove(single);
                    mData2.add(single);
                }
                setData(mData2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.retry:
                flag = 0;
                mT = getPrenster();
                mT.bindView(this);
                setContentView(getContentViewID());
                initView();
                initListener();
                initData();
                break;
        }

    }

    public void setData(List<ListBean> list){
        this.mData=list;
        refreshLayout.setRefreshing(false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ViewAdapter adapter=new ViewAdapter(mData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //LinearLayout end = (LinearLayout) findViewById(R.id.all_Layout);
                //显示隐藏的bar
                //end.setVisibility(View.VISIBLE);
            }
        });
        }


    public void changeView() {
        flag=1;
        setContentView(getContentViewID());
        initView();
        initListener();
    }
}