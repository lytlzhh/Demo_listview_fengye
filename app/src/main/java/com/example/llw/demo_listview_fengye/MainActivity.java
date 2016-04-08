package com.example.llw.demo_listview_fengye;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListview.Callbackinterface_data {
    private LoadListview listview;
    private List<Mygetitem> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Mybaseadapter mybaseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myinitview();
    }

    public void myinitview() {
        listview = (LoadListview) findViewById(R.id.listview_id);
        listview.setInterface_Call(this);
        list = new ArrayList<>();
        setadapter();
    }


    public void ShowListview(List<Mygetitem> list) {
        if (mybaseadapter == null) {
            mybaseadapter = new Mybaseadapter(list, this);
            listview.setAdapter(mybaseadapter);
        } else {
            mybaseadapter.onDateChange(list);
        }

    }

    //  String[] ss = {"hello", "nihao", "tiank", "zhongguo"};

    public void setadapter() {
        for (int i = 0; i < 30; i++) {
            list.add(new Mygetitem("更好的数据"));
        }

        listview.setAdapter(new Mybaseadapter(list, this));
    }

    String[] sr = {"更多数据", "最新数据", "新闻直播间"};

    public void getadapter() {
        for (int i = 0; i < 3; i++) {
            list.add(new Mygetitem(sr[i]));
        }
        listview.setAdapter(new Mybaseadapter(list, this));
    }


    @Override
    public void onLoad_Date() {
        android.os.Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新数据，
                getadapter();
                // 通知listview显示item*/
                ShowListview(list);
                //通知listview加载完毕，可以隐藏提示信息
                listview.LoadComplete();
            }
        }, 3000);

    }


}
