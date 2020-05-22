package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class InputDateFragment extends Fragment {

    private EditText inputDate;
    private TextView dateError;
    private Button btn;
    private String rate;

    public static InputDateFragment newInstance() {
        return new InputDateFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_input_date, container, false);
        btn = v.findViewById(R.id.btn_change_date);
        inputDate = v.findViewById(R.id.input_change_date);
        dateError = v.findViewById(R.id.date_error);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkError()) {
                    rate = inputDate.getText().toString();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_container, new ListFragment(rate))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return v;
    }

    private boolean checkError() {
        boolean hasError = false;
        if (inputDate.getText().toString().isEmpty()) {
            hasError = true;
            dateError.setVisibility(View.VISIBLE);
        } else {
            dateError.setVisibility(View.GONE);
        }
        return hasError;
    }
}