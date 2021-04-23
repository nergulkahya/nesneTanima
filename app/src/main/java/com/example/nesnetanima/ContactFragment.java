package com.example.nesnetanima;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Button instagramButon = (Button) view.findViewById(R.id.instagram);
        Button twitterButon = (Button) view.findViewById(R.id.twetter);
        Button mailButon = (Button) view.findViewById(R.id.mail);
        instagramButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri instaLinki = Uri.parse("https://www.instagram.com/nergulkhy._/"); 
                Intent intent = new Intent(Intent.ACTION_VIEW, instaLinki);
                startActivity(intent);
            }
        });
        twitterButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri twitLink = Uri.parse("https://twitter.com/nergulkhy_"); //butona vermek istediğimiz linki buraya yazıyoruz.
                Intent intent = new Intent(Intent.ACTION_VIEW, twitLink);
                startActivity(intent);
            }
        });
        mailButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"gönderilecekmailadresi"});
                email.putExtra(Intent.EXTRA_SUBJECT, "konu");
                email.putExtra(Intent.EXTRA_TEXT, "mesaj");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "nergulkahya224@gmail.com"));
            }
        });
        return view;
    }
}