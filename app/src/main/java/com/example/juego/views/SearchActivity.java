package com.example.juego.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego.R;
import com.example.juego.db.ScoreHelper;

public class SearchActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditScoreView;
    private ScoreHelper mDB;
    private Button searchButton;
    ImageButton backImageButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEditScoreView = ((EditText) findViewById(R.id.search_score));
        mTextView = findViewById(R.id.search_result);
        mDB = new ScoreHelper(this);

        backImageButton = findViewById(R.id.backImageButton2);
        backImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, RankedActivity.class);
            startActivity(intent);
        });

        searchButton = findViewById(R.id.button_search);

        searchButton.setOnClickListener(this::showResult);
    }

    public void showResult(View view) {
        String score = mEditScoreView.getText().toString();
        mTextView.setText("Result for " + score + ":\n\n");
        Cursor cursor = mDB.search(score);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index;
            String result;
            do {
                index = cursor.getColumnIndex(ScoreHelper.KEY_SCORE);
                result = cursor.getString(index);
                mTextView.append(result + "\n");
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

}
