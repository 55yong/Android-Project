package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameend);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        ArrayList<User> userList = intent.getParcelableArrayListExtra("userList");
        int win = intent.getIntExtra("win",0);
        int draw = intent.getIntExtra("draw",0);
        int lose = intent.getIntExtra("lose",0);
        double userScore = intent.getDoubleExtra("userScore", 0);
        int total = win + draw + lose;
        double rate = ((win + (draw * 0.5)) / total) * 100;
        rate = Math.round(rate * 100) / 100.0;

        TextView textView = findViewById(R.id.userName);
        TextView result = findViewById(R.id.result);
        TextView winrate = findViewById(R.id.winrate);
        TextView score = findViewById(R.id.userScore);
        Button returnBtn = findViewById(R.id.returnBtn);
        Button ratingBtn = findViewById(R.id.showRatingBtn);

        textView.setText(userName + "님");
        result.setText(total+"전\t"+win+"승\t"+lose+"패\t"+draw+"무");
        winrate.setText("승률 : " + rate + "%");
        score.setText("획득 점수 : " + userScore);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GameStartActivity.class);
                startActivity(intent);
            }
        });

        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RatingActivity.class);

                userList.add(new User(userName, userScore));
                intent.putParcelableArrayListExtra("userList", (ArrayList<? extends Parcelable>) userList);

                startActivity(intent);
            }
        });
    }
}