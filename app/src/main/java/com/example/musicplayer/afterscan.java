package com.example.musicplayer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;


public class afterscan extends AppCompatActivity {
    public static TextView last;
    public Button profile;
    Date c = Calendar.getInstance().getTime();
    TextView newUser, newNum, newTok;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterscan);
        profile = findViewById(R.id.pro);
        last =findViewById(R.id.last);
        newUser= findViewById(R.id.nam);
        newNum = findViewById(R.id.numb);
        newTok= findViewById(R.id.tok);
        last.setText("Scanned successfully on: "+ c);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUser.setText(userProfile.getUserName());
                newNum.setText(userProfile.getUserNumber());
                String a = userProfile.getToken();
                Integer res = Integer.parseInt(a) -1;

                newTok.setText(res.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newUser.getText().toString();
                String num = newNum.getText().toString();
                String tok = newTok.getText().toString();
                UserProfile userProfile = new UserProfile(name, num, tok);
                databaseReference.setValue(userProfile);
                startActivity(new Intent(afterscan.this, pre.class));

            }
        });

        }

}
