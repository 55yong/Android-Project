package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class GameStartActivity extends AppCompatActivity {

    Button startBtn;
    EditText userName;
    ArrayList userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);

        startBtn = findViewById(R.id.startBtn);
        userName = findViewById(R.id.userName);

        userList = new ArrayList<>();

        ArrayList<User> list = getIntent().getParcelableArrayListExtra("userList");
        if(list != null && !list.isEmpty()) userList = list;

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = userName.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userName", uName);
                intent.putParcelableArrayListExtra("userList", (ArrayList<User>) userList);

                startActivity(intent);
            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                startBtn.setEnabled(true);
            }
        });
    }
}
