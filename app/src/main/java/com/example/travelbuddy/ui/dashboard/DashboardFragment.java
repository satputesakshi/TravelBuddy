package com.example.travelbuddy.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelbuddy.Description;
import com.example.travelbuddy.MainActivity;
import com.example.travelbuddy.R;
import com.example.travelbuddy.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    EditText fromTxt, toTxt;
    Button searchBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Fetch value from view and assign it to variables
        fromTxt = root.findViewById(R.id.fromEdtTxt);
        toTxt = root.findViewById(R.id.toEdtTxt);
        searchBtn = root.findViewById(R.id.btnSearch);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromCountry = fromTxt.getText().toString();
                String toCountry = toTxt.getText().toString();
                Intent intent = new Intent(getActivity(), Description.class);
                intent.putExtra("from", fromCountry);
                intent.putExtra("to", toCountry);
                //Condition for search criteria
                if(toCountry == "canada")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.canadaicon);
                    intent.putExtra("image", R.drawable.canadabg);
                }
                if(toCountry == "usa")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.usaicon);
                    intent.putExtra("image", R.drawable.us);
                }
                if(toCountry == "newzealand")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.nzicon);
                    intent.putExtra("image", R.drawable.nz);
                }
                if(toCountry == "australia")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.ausicon);
                    intent.putExtra("image", R.drawable.aus);
                }
                if(toCountry == "singapore")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.singaporeicon);
                    intent.putExtra("image", R.drawable.sing);
                }
                if(toCountry == "ireland")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.irelandicon);
                    intent.putExtra("image", R.drawable.ireland);
                }
                if(toCountry == "france")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.franceicon);
                    intent.putExtra("image", R.drawable.paris);
                }
                if(toCountry == "netherland")
                {
                    intent.putExtra("country", toCountry);
                    intent.putExtra("flag", R.drawable.nethicon);
                    intent.putExtra("image", R.drawable.ams);
                }
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