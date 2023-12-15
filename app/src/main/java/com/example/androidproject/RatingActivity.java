package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RatingActivity extends AppCompatActivity {
    TextView rank1, rank2, rank3, rank4, rank5;
    Button btnBack;
    String name;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        name = getIntent().getStringExtra("playerName");
        score = getIntent().getIntExtra("score", 0);

        rank1 = (TextView) findViewById(R.id.rank1);
        rank2 = (TextView) findViewById(R.id.rank2);
        rank3 = (TextView) findViewById(R.id.rank3);
        rank4 = (TextView) findViewById(R.id.rank4);
        rank5 = (TextView) findViewById(R.id.rank5);
        btnBack = (Button) findViewById(R.id.btnBack);

        ArrayList<User> userList = getIntent().getParcelableArrayListExtra("userList");

        calculateAndDisplayRanking(userList);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to return to MainActivity
                Intent intent = new Intent(getApplicationContext(), GameStartActivity.class);
                intent.putParcelableArrayListExtra("userList", (ArrayList<? extends Parcelable>) userList);
                startActivity(intent);
            }
        });
    }

    private void calculateAndDisplayRanking(ArrayList<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Double.compare(user2.getScore(), user1.getScore());
            }
        });

        for (int i = 0; i < 5 && i < users.size(); i++) {
            User user = users.get(i);
            switch (i) {
                case 0:
                    rank1.setText("1등 " + user.getName() + " : " + user.getScore());
                    break;
                case 1:
                    rank2.setText("2등 " + user.getName() + " : " + user.getScore());
                    break;
                case 2:
                    rank3.setText("3등 " + user.getName() + " : " + user.getScore());
                    break;
                case 3:
                    rank4.setText("4등 " + user.getName() + " : " + user.getScore());
                    break;
                case 4:
                    rank5.setText("5등 " + user.getName() + " : " + user.getScore());
                    break;
            }
        }
    }
}
