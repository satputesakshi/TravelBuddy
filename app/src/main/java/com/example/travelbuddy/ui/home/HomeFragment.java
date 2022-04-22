package com.example.travelbuddy.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelbuddy.Description;
import com.example.travelbuddy.R;
import com.example.travelbuddy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    //Variable Declaration
    private FragmentHomeBinding binding;
    LinearLayout canLayout, usaLayout, nzeLayout, ausLayout, singLayout, ireLayout, franceLayout, amsterLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        canLayout = (LinearLayout) root.findViewById(R.id.canLayout);
        usaLayout = (LinearLayout) root.findViewById(R.id.usaLayout);
        nzeLayout = (LinearLayout) root.findViewById(R.id.nzlLayout);
        ausLayout = (LinearLayout) root.findViewById(R.id.ausLayout);
        singLayout = (LinearLayout) root.findViewById(R.id.singLayout);
        ireLayout = (LinearLayout) root.findViewById(R.id.ireLayout);
        franceLayout = (LinearLayout) root.findViewById(R.id.franceLayout);
        amsterLayout = (LinearLayout) root.findViewById(R.id.amsterLayout);
        //Listener for click button
        canLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            //Provide information for card clicked
            //Canada
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "canada";
                String country = "Canada";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.canadaicon);
                intent.putExtra("image", R.drawable.canadabg);
                startActivity(intent);
            }
        });
        //USA
        usaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "usa";
                String country = "USA";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.usaicon);
                intent.putExtra("image", R.drawable.us);
                startActivity(intent);
            }
        });
        //New Zealand
        nzeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fromCountry = "india";
                String toCountry = "newzealand";
                String country = "New Zealand";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.nzicon);
                intent.putExtra("image", R.drawable.nz);
                startActivity(intent);
            }
        });
        //Australia
        ausLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "australia";
                String country = "Australia";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.ausicon);
                intent.putExtra("image", R.drawable.aus);
                startActivity(intent);
            }
        });
        //Singapore
        singLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "singapore";
                String country = "Singapore";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.singaporeicon);
                intent.putExtra("image", R.drawable.sing);
                startActivity(intent);
            }
        });
        //Ireland
        ireLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "ireland";
                String country = "Ireland";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.irelandicon);
                intent.putExtra("image", R.drawable.ireland);
                startActivity(intent);
            }
        });
        //France
        franceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "france";
                String country = "France";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.franceicon);
                intent.putExtra("image", R.drawable.paris);
                startActivity(intent);
            }
        });
        //Netherland
        amsterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = "india";
                String toCountry = "netherland";
                String country = "Netherland";
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                intent.putExtra("country", country);
                intent.putExtra("flag", R.drawable.nethicon);
                intent.putExtra("image", R.drawable.ams);
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