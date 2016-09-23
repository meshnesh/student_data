package com.example.entsfrank.datacollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataListFragment extends Fragment {

    public DataListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_list, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.newRecycler);
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.addBtn);
        floatingActionButton.setOnClickListener(clickHandler);
        List<DataContract> dataList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ReadData task = new ReadData(getActivity());
        try{
            dataList = task.execute().get();
            RecyclerView.Adapter adapter = new DataListAdapter(dataList, getActivity());
            recyclerView.setAdapter(adapter);
        } catch (InterruptedException|ExecutionException e){
            e.printStackTrace();
        }
        return view;
    }
    View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.dataCollectionContainer, new CreateNewFragment())
                    .commit();
        }
    };
}
