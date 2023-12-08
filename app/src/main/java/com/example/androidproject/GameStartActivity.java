package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class GameStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);

        Button button = findViewById(R.id.startBtn);
        EditText userName = findViewById(R.id.userName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //사용자가 입력한 사용자명을 전달
                intent.putExtra("userName", userName.getText().toString());
                //MainActivity로 이동
                startActivity(intent);
            }
        });
    }
}
