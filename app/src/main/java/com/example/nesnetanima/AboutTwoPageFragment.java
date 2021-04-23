package com.example.nesnetanima;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AboutTwoPageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_about_two_page, container, false);

        Button btnGeri=(Button)view.findViewById(R.id.go_hakkimda_geri);
        Button btnIleri=(Button)view.findViewById(R.id.go_hakkimda1) ;
        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSecond(view);
            }
        });
        btnIleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSecond2(view);
            }
        });
        return view;
    }
    public void goSecond(View view)
    {
        AboutFragment aboutFragment = new AboutFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,aboutFragment,"One");
        transaction.commit();
    }
    public void goSecond2(View view)
    {
        AboutTreePageFragment aboutTreePageFragment = new AboutTreePageFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,aboutTreePageFragment,"Tree");
        transaction.commit();
    }
}