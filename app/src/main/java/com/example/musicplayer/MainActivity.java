package com.example.musicplayer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText user, password;
    private Button login, newuser, admin;
    private FirebaseAuth firebaseAuth;

    TextView tv2;
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    setupUI();
    firebaseAuth = FirebaseAuth.getInstance();
      /* FirebaseUser USER = firebaseAuth.getCurrentUser();
        if(USER != null){
            finish();
            startActivity(new Intent(MainActivity.this, list.class));
        }*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(user.getText().toString(), password.getText().toString());
            }
        });
    newuser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, registration.class));
        }
    });
}
private void setupUI(){
        user =  (EditText)findViewById(R.id.uname);
        admin = findViewById(R.id.admin);
        password =  (EditText)findViewById(R.id.pass);
        login =  (Button)findViewById(R.id.log);
        newuser =  (Button)findViewById(R.id.newuser);
    tv2 = (TextView) findViewById(R.id.textView2);
    tv4 = (TextView) findViewById(R.id.textView4);

    admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, admin.class));
        }
    });


}
        private void validate(String userName, String userPassword){
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this ,"Login Successful", Toast.LENGTH_LONG);
                        startActivity(new Intent(MainActivity.this, pre.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this ,"Login Error", Toast.LENGTH_LONG);
                    }

                }
            });
        }
        }
