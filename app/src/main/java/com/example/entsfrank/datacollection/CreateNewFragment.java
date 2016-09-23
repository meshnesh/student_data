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
        TextView purposeHint = (TextView)view.findViewById(R.id.generateGender);
        purposeHint.setOnClickListener(clickHandler);
        addCall.setOnClickListener(clickHandler);
        return view;
    }
    View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.submit_new_call:
                    EditText name = (EditText) view.findViewById(R.id.new_name);
                    EditText age = (EditText) view.findViewById(R.id.new_age);
                    TextView gender = (TextView) view.findViewById(R.id.new_gender);
                    EditText university = (EditText) view.findViewById(R.id.new_university);
                    EditText job = (EditText) view.findViewById(R.id.new_dream_job);
                    EditText salary = (EditText) view.findViewById(R.id.new_salary);
                    EditText company = (EditText) view.findViewById(R.id.new_company);
                    if (name.getText().toString().equals("")) {
                        name.setError("Please fill in the title");
                        return;
                    }
                    if (age.getText().toString().equals("")) {
                        age.setError("Please fill in the description");
                        return;
                    }
                    if (gender.getText().toString().equals("")) {
                        gender.setError("Please set in the purpose");
                        return;
                    }
                    if (university.getText().toString().equals("")) {
                        university.setError("Please fill in the association");
                        return;
                    }
                    if (job.getText().toString().equals("")) {
                        job.setError("Please set in the time");
                        return;
                    }
                    if (salary.getText().toString().equals("")) {
                        salary.setError("Please set in the reminder");
                        return;
                    }
                    if (company.getText().toString().equals("")) {
                        company.setError("Please set in the reminder");
                        return;
                    }

                    SaveData task = new SaveData(
                            getActivity(), name.getText().toString(),
                            age.getText().toString(), gender.getText().toString(),
                            university.getText().toString(), job.getText().toString(),
                            salary.getText().toString(),company.getText().toString()
                    );
                    task.execute();

                    getFragmentManager()
                            .popBackStackImmediate();
                    break;
                case R.id.generateGender:
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
            TextView purpose = (TextView) view.findViewById(R.id.new_gender);
            purpose.setText(menuItem.getTitle());
            return true;
        }
    };
}
