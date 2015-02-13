package com.example.sah.testarray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sah on 2/12/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String,String>> list;

    public ListViewAdapter(Context c,ArrayList<HashMap<String,String>> ls){
        context = c;
        list = ls;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        View convertView = view;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_detail,null);
            holder = new ViewHolder();
            holder.tv1 = (TextView)convertView.findViewById(R.id.txt1);
            holder.tv2 = (TextView)convertView.findViewById(R.id.txt2);
            holder.tv3 = (TextView)convertView.findViewById(R.id.txt3);
            holder.tv4 = (TextView)convertView.findViewById(R.id.txt4);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
            holder.tv1.setText(list.get(position).get("Name"));
            holder.tv2.setText(list.get(position).get("Id"));
            holder.tv3.setText(list.get(position).get("Phone"));
            holder.tv4.setText(list.get(position).get("Sex"));
        return convertView;
    }
    public class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
    }
}
