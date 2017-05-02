package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import kotako.java.info.bluebird.model.entity.Tweet;

import java.util.ArrayList;

public class TimelineListAdapter extends BaseAdapter {
    ArrayList<Tweet> list;

    public TimelineListAdapter(Context context, ArrayList<Tweet> list){
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Tweet getItem(int p){
        return list.get(p);
    }

    @Override
    public long getItemId(int p){
        return p;
    }

    @Override
    public View getView(int p,View convertView,ViewGroup viewGroup){
        return convertView;
    }
}
