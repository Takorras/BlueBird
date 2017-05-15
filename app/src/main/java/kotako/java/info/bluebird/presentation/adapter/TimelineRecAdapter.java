package kotako.java.info.bluebird.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotako.java.info.bluebird.R;

import java.util.ArrayList;

public class TimelineRecAdapter extends RecyclerView.Adapter<TimelineRecAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList list;

    public TimelineRecAdapter(Context context,ArrayList list){
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.view_tweet_preview,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder,final int i){
        if(list==null) return;

        // 描画と処理
    }

    @Override
    public int getItemCount(){
        if(list != null){
            return list.size();
        }
        return 0;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        public CustomViewHolder(View itemView){
            super(itemView);
        }
    }
}
