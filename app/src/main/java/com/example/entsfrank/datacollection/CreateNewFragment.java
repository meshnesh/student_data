package com.example.entsfrank.datacollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateNewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateNewFragment extends Fragment {

    private View view;


    public CreateNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_create_new, container, false);
        Button addCall = (Button)view.findViewById(R.id.submit_new_call);
        TextView timePicker = (TextView)view.findViewById(R.id.generateCallTimePicker);
        TextView reminderPicker = (TextView)view.findViewById(R.id.generateCallReminderPicker);
        TextView purposeHint = (TextView)view.findViewById(R.id.generateCallPurpose);
        purposeHint.setOnClickListener(clickHandler);
        addCall.setOnClickListener(clickHandler);
        timePicker.setOnClickListener(clickHandler);
        reminderPicker.setOnClickListener(clickHandler);
        return view;
    }
    View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.submit_new_call:
                    EditText title = (EditText) view.findViewById(R.id.new_call_title);
                    EditText description = (EditText) view.findViewById(R.id.new_call_description);
                    TextView purpose = (TextView) view.findViewById(R.id.new_call_purpose);
                    EditText association = (EditText) view.findViewById(R.id.new_call_association);
                    TextView time = (TextView) view.findViewById(R.id.new_call_time);
                    TextView reminder = (TextView) view.findViewById(R.id.new_call_reminder);

                    if (title.getText().toString().equals("")) {
                        title.setError("Please fill in the title");
                        return;
                    }
                    if (description.getText().toString().equals("")) {
                        description.setError("Please fill in the description");
                        return;
                    }
                    if (purpose.getText().toString().equals("")) {
                        purpose.setError("Please set in the purpose");
                        return;
                    }
                    if (association.getText().toString().equals("")) {
                        association.setError("Please fill in the association");
                        return;
                    }
                    if (time.getText().toString().equals("")) {
                        time.setError("Please set in the time");
                        return;
                    }
                    if (reminder.getText().toString().equals("")) {
                        reminder.setError("Please set in the reminder");
                        return;
                    }

                    SaveData task = new SaveData(
                            getActivity(), title.getText().toString(),
                            description.getText().toString(), association.getText().toString(),
                            purpose.getText().toString(), time.getText().toString(),
                            reminder.getText().toString()
                    );
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);

                    try {
                        long eventId = task.execute().get();

                    } catch (InterruptedException|ExecutionException e){
                        e.printStackTrace();
                    }
                    getFragmentManager()
                            .popBackStackImmediate();
                    break;
                case R.id.generateCallPurpose:
                    PopupMenu popupMenu = new PopupMenu(getActivity(),v);
                    popupMenu.setOnMenuItemClickListener(popupClickHandler);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.purposes_menu, popupMenu.getMenu());
                    popupMenu.show();
                    break;
            }
        }
    };

    PopupMenu.OnMenuItemClickListener popupClickHandler = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            TextView purpose = (TextView) view.findViewById(R.id.new_call_purpose);
            purpose.setText(menuItem.getTitle());
            return true;
        }
    };
}
