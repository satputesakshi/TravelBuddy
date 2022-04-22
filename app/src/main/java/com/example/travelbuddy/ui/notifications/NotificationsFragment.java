package com.example.travelbuddy.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelbuddy.Global;
import com.example.travelbuddy.Login;
import com.example.travelbuddy.R;
import com.example.travelbuddy.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationsFragment extends Fragment {
    //Declare Variables
    private FragmentNotificationsBinding binding;
    private FirebaseAuth mAuth;
    TextView nameTxt, emailTxt, phoneTxt, countryTxt;
    Button signoutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Initialise firebase authentication
        mAuth = FirebaseAuth.getInstance();
        //Get values from UI
        nameTxt = root.findViewById(R.id.userTxt);
        emailTxt = root.findViewById(R.id.emailTxt);
        phoneTxt = root.findViewById(R.id.phoneTxt);
        countryTxt = root.findViewById(R.id.ctryTxt);
        signoutBtn = root.findViewById(R.id.signOutBtn);
        //Fetch Data from db and display them on profile page
        nameTxt.setText(Global.currentname.getName());
        emailTxt.setText(Global.currentemail.getEmail());
        phoneTxt.setText(Global.currentphone.getPhone());
        countryTxt.setText(Global.currentcountry.getCountry());

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sign out button functionality
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}