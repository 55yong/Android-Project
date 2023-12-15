package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView userNameText;
    ImageButton rBtn, pBtn, sBtn;
    Button endBtn;
    ImageView cptImage, userImage;
    int rounds, win, draw, lose;
    double userScore;
    String userName;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        userNameText = findViewById(R.id.userName);

        sBtn = findViewById(R.id.sBtn);
        rBtn = findViewById(R.id.rBtn);
        pBtn = findViewById(R.id.pBtn);
        cptImage = findViewById(R.id.cptImage);
        userImage = findViewById(R.id.userImage);

        userName = intent.getStringExtra("userName");
        userList = intent.getParcelableArrayListExtra("userList");
        userNameText.setText(userName);

        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { playGame(0); }
        });

        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { playGame(1); }
        });

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { playGame(2); }
        });
    }

    private void playGame(int userChoice) {
        Random random = new Random();
        int cpt = random.nextInt(3);

        if (cpt == 0) cptImage.setImageResource(R.drawable.rock);
        else if (cpt == 1) cptImage.setImageResource(R.drawable.scissors);
        else cptImage.setImageResource(R.drawable.paper);

        if (userChoice == 0) userImage.setImageResource(R.drawable.rock);
        else if (userChoice == 1) userImage.setImageResource(R.drawable.scissors);
        else userImage.setImageResource(R.drawable.paper);

        String result;

        if (userChoice == cpt) {
           showResult("비겼습니다.");
           draw++;
        }
        else if ((userChoice == 0 && cpt == 1) || (userChoice == 1 && cpt == 2) || (userChoice == 2 && cpt == 0)) {
            showResult("이겼습니다!!!");
            win++;
        }
        else {
            showResult("졌습니다....");
            lose++;
        }
        rounds += 1;
        if (rounds == 5) {
            endGame();
        }
    }

    private void showResult(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("게임 결과").setMessage(message);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void endGame() {
        Intent intent = new Intent(getApplicationContext(), GameEndActivity.class);

        userScore = win + (draw * 0.5) - lose;

        intent.putExtra("win", win);
        intent.putExtra("lose", lose);
        intent.putExtra("draw", draw);
        intent.putExtra("userName", userName);
        intent.putExtra("userScore", userScore);
        intent.putParcelableArrayListExtra("userList", (ArrayList<? extends Parcelable>) userList);

        startActivity(intent);
    }
}
