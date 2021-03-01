/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.juego.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego.R;
import com.example.juego.db.ScoreListAdapter;


/**
 * Activity to edit an existing or create a new word.
 */
public class EditScoreActivity extends AppCompatActivity {

    private static final String TAG = EditScoreActivity.class.getSimpleName();

    private static final int NO_ID = -99;
    private static final String NO_SCORE = "";

    private EditText mEditScoreView;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    int mId = RankedActivity.SCORE_ADD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_score);

        mEditScoreView = (EditText) findViewById(R.id.edit_score);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int id = extras.getInt(ScoreListAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(ScoreListAdapter.EXTRA_SCORE, NO_SCORE);
            if (id != NO_ID && !word.equals(NO_SCORE)) {
                mId = id;
                mEditScoreView.setText(word);
            }
        }
    }

    public void returnReply(View view) {
        String score = ((EditText) findViewById(R.id.edit_score)).getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, score);
        replyIntent.putExtra(ScoreListAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}

