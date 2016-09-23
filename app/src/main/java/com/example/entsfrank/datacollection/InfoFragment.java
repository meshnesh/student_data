package com.example.entsfrank.datacollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);SingleCall task = new SingleCall(
                getActivity(),
                getActivity().getIntent().getLongExtra("eventId",0),
                "read",
                null
        );
        TextView title = (TextView) view.findViewById(R.id.single_call_title);
        TextView description = (TextView) view.findViewById(R.id.single_call_description);
        TextView purpose = (TextView) view.findViewById(R.id.single_call_purpose);
        TextView association = (TextView) view.findViewById(R.id.single_call_association);
        TextView time = (TextView) view.findViewById(R.id.single_call_time);
        TextView reminder = (TextView) view.findViewById(R.id.single_call_reminder);
        try{
            this.event = task.execute().get();
            if(event!=null) {
                title.setText(event.getTitle());
                description.setText(event.getDescription());
                purpose.setText(event.getPurpose());
                association.setText(event.getAssociation());
                time.setText(event.getTime());
                reminder.setText(event.getReminder());
                setHasOptionsMenu(true);
            } else {
                title.setText(R.string.null_event);
            }
        } catch (InterruptedException|ExecutionException e){
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_del_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_action_icon:
                openEditFrag();
                return true;
            case R.id.del_action_icon:
                deleteCall();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openEditFrag() {
        EditCallFragment editFrag = new EditCallFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("eventId", event.getId());
        bundle.putString("title", event.getTitle());
        bundle.putString("description", event.getDescription());
        bundle.putString("association", event.getAssociation());
        bundle.putString("purpose", event.getPurpose());
        bundle.putString("time", event.getTime());
        bundle.putString("reminder", event.getReminder());
        editFrag.setArguments(bundle);
        FragmentManager fManager = getFragmentManager();
        fManager.beginTransaction()
                .replace(R.id.singleCallContainer, editFrag)
                .addToBackStack(null)
                .commit();

    }

    public void deleteCall(){
        SingleCall task = new SingleCall(
                getActivity(), event.getId(),
                "delete", null
        );
        task.execute();
        getActivity().finish();
    }

}
