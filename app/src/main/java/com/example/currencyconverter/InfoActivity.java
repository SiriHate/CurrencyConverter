package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        //Обьявляем переменную кнопки назад
        ImageButton go_back;

        //Находим кнопку по id
        go_back = findViewById(R.id.go_back);

        //Обработка нажатия кнопки info
        go_back.setOnClickListener(v -> startActivity(new Intent(InfoActivity.this, MainActivity.class)));

    }
}