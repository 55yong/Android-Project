package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageButton rBtn, pBtn, sBtn;
    Button endBtn;
    ImageView cptImage, userImage;
    Random rand;
    int win,draw,lose;
    boolean trigger = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextView userNameText = findViewById(R.id.userName);
        userNameText.setText(userName);

        sBtn = findViewById(R.id.sBtn);
        rBtn = findViewById(R.id.rBtn);
        pBtn = findViewById(R.id.pBtn);

        endBtn = findViewById(R.id.endBtn);

        cptImage = findViewById(R.id.cptImage);
        userImage = findViewById(R.id.userImage);
        rand = new Random();

        startImageSwitch();

        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = rand.nextInt(3) + 1;

                userImage.setImageResource(R.drawable.rock);
                switch (num){
                    case 1:
                        cptImage.setImageResource(R.drawable.rock);
                        draw += 1;
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                    case 2:
                        cptImage.setImageResource(R.drawable.paper);
                        lose += 1;
                        showResult(userName +"님이 패배했습니다..");
                        break;
                    case 3:
                        cptImage.setImageResource(R.drawable.scissors);
                        win += 1;
                        showResult(userName +"님이 이겼습니다!!");
                        break;
                }
            }
        });

        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = rand.nextInt(3) + 1;

                userImage.setImageResource(R.drawable.paper);
                switch (num){
                    case 1://컴퓨터(바위)
                        cptImage.setImageResource(R.drawable.rock);
                        win += 1;   //유저가 이긴 경우
                        showResult(userName +"님이 이겼습니다!!");
                        break;
                    case 2://컴퓨터(보)
                        cptImage.setImageResource(R.drawable.paper);
                        draw += 1;  //비긴 경우
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                    case 3://컴퓨터(가위)
                        cptImage.setImageResource(R.drawable.scissors);
                        lose += 1;
                        showResult(userName +"님이 패배했습니다..");
                        break;
                }
            }
        });

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = rand.nextInt(3) + 1;

                userImage.setImageResource(R.drawable.scissors);
                switch (num){
                    case 1:
                        cptImage.setImageResource(R.drawable.rock);
                        lose += 1;
                        showResult(userName +"님이 패배했습니다..");
                        break;
                    case 2:
                        cptImage.setImageResource(R.drawable.paper);
                        win += 1;
                        showResult(userName +"님이 이겼습니다!!");
                        break;
                    case 3:
                        cptImage.setImageResource(R.drawable.scissors);
                        draw += 1;
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                }
            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GameEndActivity.class);

                intent.putExtra("win", win);
                intent.putExtra("lose", lose);
                intent.putExtra("draw", draw);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });
    }

    private void showResult(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("게임 결과").setMessage(message);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                trigger =  true;
                startImageSwitch();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void startImageSwitch() {
        if (!trigger) {
            return;
        }
        final Handler handler = new Handler();
        final int[] imageResources = {R.drawable.rock, R.drawable.paper, R.drawable.scissors};
        final int[] imageIndex = {0};
        final Runnable imageSwitcher = new Runnable() {
            @Override
            public void run() {
                if (!trigger) {
                    return;
                }
                imageIndex[0] = (imageIndex[0] + 1) % imageResources.length;
                cptImage.setImageResource(imageResources[imageIndex[0]]);
                handler.postDelayed(this, 700);
            }
        };
        handler.postDelayed(imageSwitcher, 700);
    }
}