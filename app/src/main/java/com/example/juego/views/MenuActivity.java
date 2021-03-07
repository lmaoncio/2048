package com.example.juego.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.juego.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> menuOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listview = findViewById(R.id.menu);
        menuOptions = new ArrayList<>();
        menuOptions.add("START");
        menuOptions.add("MATCHES");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuOptions);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener((adapterView, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }

            if (position == 1) {
                Intent intent = new Intent(MenuActivity.this, RankedActivity.class);
                startActivity(intent);
            }
        });
    }
}
