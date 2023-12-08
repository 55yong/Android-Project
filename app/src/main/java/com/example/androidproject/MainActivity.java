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
    Random random;
    int win,draw,lose;
    boolean trigger = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");    //Intro에서 입력받은 유저명을 가져온다

        //인트로에서 받아온 유저명 변경
        TextView userName_text = findViewById(R.id.userName);
        userName_text.setText(userName);

        sBtn = findViewById(R.id.sBtn);
        rBtn = findViewById(R.id.rBtn);
        pBtn = findViewById(R.id.pBtn);

        endBtn = findViewById(R.id.end_btn);   //게임 종료 버튼 (그만 하기)

        cptImage = findViewById(R.id.cptImage); //컴퓨터가 낸 가위,바위,보 이미지 뷰
        userImage = findViewById(R.id.userImage); //유저가 낸 가위,바위,보 이미지 뷰
        random = new Random();  //난수 생성을 위한 랜덤 객체 생성

        //컴퓨터의 가위,바위,보 이미지를 자동으로 전환 해주는 함수
        startImageSwitch();

        rBtn.setOnClickListener(new View.OnClickListener() { //유저가 바위를 낸 상황
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = random.nextInt(3) + 1;     //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정

                userImage.setImageResource(R.drawable.rock);
                switch (num){
                    case 1: //컴퓨터(바위)
                        cptImage.setImageResource(R.drawable.rock);
                        draw += 1;  //비긴경우
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                    case 2: //컴퓨터(보)
                        cptImage.setImageResource(R.drawable.paper);
                        lose += 1;  //유저가 패배한 경우
                        showResult(userName +"님이 패배했습니다!");
                        break;
                    case 3: //컴퓨터(가위)
                        cptImage.setImageResource(R.drawable.scissors);
                        win += 1;   //유저가 이긴 경우
                        showResult(userName +"님이 이겼습니다.!");
                        break;
                }
            }
        });

        pBtn.setOnClickListener(new View.OnClickListener() {   //유저가 보를 낸 상황
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = random.nextInt(3) + 1;         //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정

                userImage.setImageResource(R.drawable.paper);
                switch (num){
                    case 1://컴퓨터(바위)
                        cptImage.setImageResource(R.drawable.rock);
                        win += 1;   //유저가 이긴 경우
                        showResult(userName +"님이 이겼습니다.!");
                        break;
                    case 2://컴퓨터(보)
                        cptImage.setImageResource(R.drawable.paper);
                        draw += 1;  //비긴 경우
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                    case 3://컴퓨터(가위)
                        cptImage.setImageResource(R.drawable.scissors);
                        lose += 1;  //유저기 패배한 경우
                        showResult(userName +"님이 패배했습니다!");
                        break;
                }
            }
        });

        sBtn.setOnClickListener(new View.OnClickListener() {    //유저가 가위를 냄
            @Override
            public void onClick(View view) {
                trigger = false;
                int num = random.nextInt(3) + 1;             //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정

                userImage.setImageResource(R.drawable.scissors);
                switch (num){
                    case 1: //컴퓨터(바위)
                        cptImage.setImageResource(R.drawable.rock);
                        lose += 1;  //유저가 패배한 경우
                        showResult(userName +"님이 패배했습니다!");
                        break;
                    case 2: //컴퓨터(보)
                        cptImage.setImageResource(R.drawable.paper);
                        win += 1;
                        showResult(userName +"님이 이겼습니다.!");
                        break;
                    case 3: //컴퓨터(가위)
                        cptImage.setImageResource(R.drawable.scissors);
                        draw += 1;
                        showResult("컴퓨터와 비겼습니다.");
                        break;
                }
            }
        });


        //그만하기 버튼을 누를 시 전적(승,패,무)과 사용자명을 EndActivity로 전달하고 이동
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
                trigger =  true; // 확인 버튼을 누르면 check를 true로 변경
                startImageSwitch();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //컴퓨터의 가위,바위,보 이미지 자동 전화을 위한 함수
    private void startImageSwitch() {
        if (!trigger) {
            return; // 이미지 회전 중지 상태이면 아무 것도 하지 않음
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