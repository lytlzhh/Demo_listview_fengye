package com.example.llw.demo_listview_fengye;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by llw on 2016/4/8.
 */
public class LoadListview extends ListView implements AbsListView.OnScrollListener {
    private LayoutInflater layoutInflater;
    private View view;
    Callbackinterface_data callbackinterface_date;//数据回调接口
    boolean isLoadding;//正在加载
    int totalItemCount;
    int lastvisibleitem;

    public LoadListview(Context context) {
        super(context);
        initview(context);
    }

    public LoadListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public LoadListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    //加载底部布局文件
    public void initview(Context context) {
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout, null);
        view.findViewById(R.id.linearlayout_id).setVisibility(View.GONE);//设置隐藏底部布局
        this.addFooterView(view);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //如果最后可见的item所在位置已经等于item的总量而且滚动状态是停止 就需要加载更多的数据
        if (totalItemCount == lastvisibleitem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoadding) {
                isLoadding = true;
                //加载之前需要显示：提示加载信息
                view.findViewById(R.id.linearlayout_id).setVisibility(View.VISIBLE);

                //加载更多
                callbackinterface_date.onLoad_Date();//获取最新数据
            }

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastvisibleitem = firstVisibleItem + visibleItemCount;//最后一个显示的item的位置
        this.totalItemCount = totalItemCount;
    }

    //如何用listview调用主界面类：即获取外部最新数据; 这里定义一个数据回调接口

    public interface Callbackinterface_data {
        public void onLoad_Date();
    }

    //listview如何调用 Callbackinterface_data接口呢 如下：

    public void setInterface_Call(Callbackinterface_data callbackinterface_date) {
        this.callbackinterface_date = callbackinterface_date;//只要在外部中调用接口，这里就可以得到最新数据
    }


    /*
    * 加载完成隐藏提示信息
    * */

    public void LoadComplete() {
        isLoadding = false;
        //加载之前需要显示：隐藏提示信息
        view.findViewById(R.id.linearlayout_id).setVisibility(View.GONE);
    }

}
