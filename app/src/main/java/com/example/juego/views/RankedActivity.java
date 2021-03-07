package com.example.juego.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.juego.R;
import com.example.juego.db.ScoreItem;
import com.example.juego.db.ScoreListAdapter;
import com.example.juego.db.ScoreHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RankedActivity extends AppCompatActivity {

    private static final String TAG = RankedActivity.class.getSimpleName();
    private ScoreHelper mDB;
    public static final int SCORE_EDIT = 1;
    public static final int SCORE_ADD = -1;
    ImageButton backImageButton;
    RecyclerView mRecyclerView;
    ScoreListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked);

        backImageButton = findViewById(R.id.backImageButton);
        backImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(RankedActivity.this, MenuActivity.class);
            startActivity(intent);
        });
        mDB = new ScoreHelper(this);

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ScoreListAdapter(this, mDB);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_name:
                mAdapter.setScoreItemList(mDB.orderByName());
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.action_score:
                mAdapter.setScoreItemList(mDB.orderByScore());
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SCORE_EDIT) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(EditScoreActivity.EXTRA_REPLY);
                String score = data.getStringExtra(EditScoreActivity.EXTRA_REPLY_TWO);
                if (!TextUtils.isEmpty(score) && !TextUtils.isEmpty(name)) {
                    int id = data.getIntExtra(ScoreListAdapter.EXTRA_ID, -99);
                    if (id == SCORE_ADD) {
                        mDB.insert(name, score);
                    } else if (id >= 0) {
                        mDB.update(id, name, Integer.parseInt(score));
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}