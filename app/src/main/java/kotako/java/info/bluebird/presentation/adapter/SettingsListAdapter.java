package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SettingsListAdapter extends BaseAdapter {

    ArrayList<String> list;

    public SettingsListAdapter(Context context, ArrayList<String> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int p) {
        return list.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(int p, View convertView, ViewGroup parent) {
        return convertView;
    }

}
