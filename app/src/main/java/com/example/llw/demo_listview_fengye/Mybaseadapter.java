package com.example.llw.demo_listview_fengye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by llw on 2016/4/8.
 */
public class Mybaseadapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Mygetitem> list;

    public Mybaseadapter(List<Mygetitem> list, MainActivity mainActivity) {
        this.list = list;
        this.context = mainActivity;
        layoutInflater = LayoutInflater.from(context);
    }

    public void onDateChange(List<Mygetitem> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.itmeo, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.textView.setText(list.get(position).srt);
        return convertView;
    }


    public class ViewHolder {
        public TextView textView;
    }
}
