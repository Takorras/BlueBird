package kotako.java.info.bluebird.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kotako.java.info.bluebird.R;
import kotako.java.info.bluebird.presentation.adapter.TimelineRecAdapter;

import java.util.ArrayList;

public class TimeLineFragment extends Fragment {

    private Context context;
    private ArrayList list;
    private RecyclerView recyclerView;
    private TimelineRecAdapter adapter;

    public TimeLineFragment() {
        // Required empty public constructor
    }

    public static TimeLineFragment newInstance() {
        // !TODO なんかしらのキーワードを受け取ってユーザリストとかパブリックとか切り替えたい
        return new TimeLineFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        list = new ArrayList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_time_line, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_timeline);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(divider);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        adapter = new TimelineRecAdapter(context,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
