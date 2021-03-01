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

package com.example.juego.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.juego.R;
import com.example.juego.views.EditScoreActivity;
import com.example.juego.views.RankedActivity;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreHolder> {
    private static final String EXTRA_POSITION = "POSITION";

    class ScoreHolder extends RecyclerView.ViewHolder {
        public final TextView scoreItemView;
        Button delete_button;
        Button edit_button;

        public ScoreHolder(View scoreItem) {
            super(scoreItem);
            scoreItemView = (TextView) scoreItem.findViewById(R.id.score);
            delete_button = (Button)scoreItem.findViewById(R.id.delete_button);
            edit_button = (Button)scoreItem.findViewById(R.id.edit_button);
        }
    }

    private static final String TAG = ScoreListAdapter.class.getSimpleName();
    ScoreHelper mDB;
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_SCORE = "SCORE";

    private final LayoutInflater mInflater;
    Context mContext;

    public ScoreListAdapter(Context context, ScoreHelper db) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }


    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.scorelist_item, parent, false);
        return new ScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ScoreHolder holder, final int position) {
        final ScoreItem current = mDB.query(position);
        holder.scoreItemView.setText(current.getScore());
        holder.edit_button.setOnClickListener(new MyButtonOnClickListener(
                current.getId(), current.getScore()) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditScoreActivity.class);
                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_POSITION, holder.getAdapterPosition());
                intent.putExtra(EXTRA_SCORE, score);

                ((Activity) mContext).startActivityForResult(
                        intent, RankedActivity.SCORE_EDIT);
            }
        });

        holder.delete_button.setOnClickListener(new MyButtonOnClickListener(
                current.getId(), current.getScore()) {
            @Override
            public void onClick(View v) {
                int id = mDB.query(holder.getAdapterPosition()).getId();
                mDB.delete(id);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) mDB.count();
    }
}


