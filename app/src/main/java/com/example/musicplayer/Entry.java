/*package com.example.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class Entry extends AppCompatActivity {
    Button pause, next, previous, log_out;
    FirebaseAuth firebaseAuth;
    SeekBar songSeekBar;
    static MediaPlayer myMediaPlayer;
    TextView songTextLabel;
    int pos;
    String sname;
    ArrayList<File> mySongs;
    Thread updateseekBar;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        pause = (Button)findViewById(R.id.btn_play);
        next = (Button)findViewById(R.id.btn_next);
        previous = (Button)findViewById(R.id.btn_previous);
        log_out = (Button)findViewById(R.id.logout);
        songTextLabel= (TextView) findViewById(R.id.textView);
        songSeekBar = (SeekBar)findViewById(R.id.seekBar2) ;
        updateseekBar = new Thread(){
            @Override
            public void run() {
                int totalDuration = myMediaPlayer.getDuration();
                int currentPosition = 0;
                while(currentPosition<totalDuration){
                    try{
                        sleep(500);
                        currentPosition = myMediaPlayer.getCurrentPosition();
                        songSeekBar.setProgress(currentPosition);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };


        log_out.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Entry.this, MainActivity.class));
            }
        });

        if(myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        mySongs= (ArrayList)bundle.getParcelableArrayList("songs");
        sname = mySong;
    s.get(pos).getName().toString();
        String songName = i.getStringExtra("songname");

        songTextLabel.setText(songName);
        songTextLabel.setSelected(true);

        pos = bundle.getInt("pos", 0);
        Uri u = Uri.parse((mySongs.get(pos).toString()));
        myMediaPlayer= MediaPlayer.create(getApplicationContext(), u);
        myMediaPlayer.start();
        songSeekBar.setMax((myMediaPlayer.getDuration()));
        updateseekBar.start();
        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo((seekBar.getProgress()));
            }
        });

        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                songSeekBar.setMax(myMediaPlayer.getDuration());
                if(myMediaPlayer.isPlaying()){
                    pause.setBackgroundResource(R.drawable.icon_play);
                    myMediaPlayer.pause();

                }
                else {
                    pause.setBackgroundResource(R.drawable.icon_pause);
                    myMediaPlayer.start();
                }
            }
        });


        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                pos= ((pos+1)%mySongs.size());

                Uri u = Uri.parse(mySongs.get(pos).toString());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                sname  = mySongs.get(pos).getName().toString();
                songTextLabel.setText(sname);
                myMediaPlayer.start();

            }
        });

        previous.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                pos= ((pos-1)<0)?(mySongs.size()-1):(pos-1);

                Uri u = Uri.parse(mySongs.get(pos).toString());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                sname  = mySongs.get(pos).getName().toString();
                songTextLabel.setText(sname);
                myMediaPlayer.start();
            }
        });

    }
}*/