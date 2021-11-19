package com.example.agrilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    View placeholder;
    Context mContext;
    EditText email_id,full_name_profile,phone_no, pin ,editaddresss , editsector, editdiscription;
    Button update , logout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        placeholder = inflater.inflate(R.layout.fragment_profile, container, false);
        full_name_profile = placeholder.findViewById(R.id.editTextTextPersonName);
        email_id = placeholder.findViewById(R.id.editTextTextEmailAddress);
        phone_no = placeholder.findViewById(R.id.editTextPhone);
        pin = placeholder.findViewById(R.id.editTextpincdoe);
        update = (Button) placeholder.findViewById(R.id.update);
        logout = (Button) placeholder.findViewById(R.id.logout);

        editaddresss = placeholder.findViewById(R.id.editadress);
        editsector = placeholder.findViewById(R.id.editsector);
        editdiscription = placeholder.findViewById(R.id.editdescription);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_email_id,s_full_name_profile,s_phone_no,s_website , s_account_no , s_pin, s_editintsa ,s_editaddresss , s_editsector, s_editdiscription;
                s_full_name_profile = full_name_profile.getText().toString();
                s_email_id = email_id.getText().toString();
                s_phone_no = phone_no.getText().toString();
                s_pin = pin.getText().toString();
                s_editaddresss = editaddresss.getText().toString();
                s_editsector = editsector.getText().toString();
                s_editdiscription = editdiscription.getText().toString();

                // dont know what to do sad life

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(mContext,MainActivity.class));

            }
        });

        return placeholder;
    }
}