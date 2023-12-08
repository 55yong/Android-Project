package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameend);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        int win = intent.getIntExtra("win",0);
        int draw = intent.getIntExtra("draw",0);
        int lose = intent.getIntExtra("lose",0);

        TextView textView = findViewById(R.id.userName);
        TextView result = findViewById(R.id.result);
        Button returnBtn = findViewById(R.id.returnBtn);

        textView.setText(userName + "님");
        result.setText(win+"승\t"+lose+"패\t"+draw+"무");

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GameStartActivity.class);
                startActivity(intent);
            }
        });
    }
}