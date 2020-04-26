package com.example.musicplayer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
    private EditText newUser, newPass, email, num;
    private Button sign, newLog;
    private FirebaseAuth firebaseAuth;
    private String Token, Name, Password,Email,Num;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUI();
        firebaseAuth = FirebaseAuth.getInstance();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String user_name = newUser.getText().toString().trim();
                    String user_pass = newPass.getText().toString().trim();
                    String user_mail = email.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_mail, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               sendUserData();
                               Toast.makeText(registration.this, "Reg successful", Toast.LENGTH_SHORT);
                               startActivity(new Intent(registration.this, MainActivity.class));
                           }else {
                               Toast.makeText(registration.this, "Reg UNsuccessful", Toast.LENGTH_SHORT);

                           }
                           }

                    });


                }
            }
        });

        newLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registration.this, MainActivity.class));
            }
        });
    }
    private  void  setupUI(){
        newUser = (EditText)findViewById(R.id.uname2);
        newPass = (EditText)findViewById(R.id.pass2);
        num = (EditText)findViewById(R.id.num) ;
        email = (EditText)findViewById(R.id.mail);
        sign = (Button)findViewById(R.id.signup);
        newLog = (Button)findViewById(R.id.log2);
    }
    private Boolean validate(){
        Boolean result = false;
        Name = newUser.getText().toString();
        Password = newPass.getText().toString();
        Email = email.getText().toString();
        Num = num.getText().toString();
        Token = "30";



        if(Name.isEmpty() || Password.isEmpty() || Email.isEmpty()){
            Toast.makeText(this, "Enter all", Toast.LENGTH_SHORT);
        }else {
            result = true;
        }
        return result;
    }

    private void sendUserData(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(Name, Num, Token);
        myRef.setValue(userProfile);
    }
}
