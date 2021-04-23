package com.example.nesnetanima;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AboutTreePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_tree_page, container, false);
        Button goHakkimdaTreePage = view.findViewById(R.id.go_hakkimda_geri2);
        goHakkimdaTreePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goSecond(view);
            }
        });
        return view;
    }


    public void goSecond(View view)
    {
        AboutTwoPageFragment aboutTwoPageFragment = new AboutTwoPageFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,aboutTwoPageFragment,"Second");
        transaction.commit();
    }
}