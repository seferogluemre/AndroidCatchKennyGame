package com.example.catchthekenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    TextView timeTxt;
    TextView scoreTxt;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timeTxt=findViewById(R.id.timeText);
        scoreTxt=findViewById(R.id.txtScore);
        score=0;

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageViev);
        imageArray=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        randomImages();

        new CountDownTimer(11000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeTxt.setText("Süre:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeTxt.setText("Zamanın Bitti Bir dahaki Sefere ");
                handler.removeCallbacks(runnable);
                hideImages();
                AlertDialog.Builder alert  =new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Ama güzel ilerlemiştin");
                alert.setMessage("Tekrar Oynamak İstermisin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Oyunu Kaybettin!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view){
        score=score+2;
        scoreTxt.setText("Score "+score);
        if(score==18){
            Toast.makeText(getApplicationContext(),"Harika gidiyorsun",Toast.LENGTH_LONG).show();
        } else if (score==32) {
             Toast.makeText(getApplicationContext(),"Böyle devam et",Toast.LENGTH_LONG).show();
        }
    }
    public void hideImages(){
         for (ImageView image : imageArray){
             image.setVisibility(View.INVISIBLE);
         }
    }
    public void randomImages(){
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                hideImages();
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,420);
                if(score==22){
                    handler.postDelayed(runnable,440);
                }
            }
        };
        handler.post(runnable);
    }
}