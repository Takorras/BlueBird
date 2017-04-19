package kotako.java.info.bluebird.presentation.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TimelineAdapter extends BaseAdapter{
    private Context context;

    public TimelineAdapter(Context context){
        super();
    }

    // 要素数を返す
    @Override
    public int getCount(){
        return 0;
    }

    @Override
    public Object getItem(int p){
        return p;
    }

    // 特殊なIDをわたす
    @Override
    public long getItemId(int p){
        return p;
    }

    // 基本的な描画はここで行う
    @Override
    public View getView(int p, View convertView, ViewGroup parent){
        return convertView;
    }
}
