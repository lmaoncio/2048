package com.example.juego.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScoreHelper extends SQLiteOpenHelper {
    private static final String TAG = ScoreHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String SCORE_LIST_TABLE = "score_entries";
    private static final String DATABASE_NAME = "scoreList";
    public static final String KEY_ID = "_id";
    public static final String KEY_SCORE = "name";
    private static final String[] COLUMNS = {KEY_ID, KEY_SCORE};
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + SCORE_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_SCORE + " TEXT " + " );";
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public ScoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String[] words = {"Android", "Adapter", "ListView", "AsyncTask",
                "Android Studio", "SQLiteDatabase", "SQLOpenHelper",
                "Data model", "ViewHolder", "Android Performance",
                "OnClickListener"};

        ContentValues values = new ContentValues();

        for (int i = 0; i < words.length; i++) {
            values.put(KEY_SCORE, words[i]);
            db.insert(SCORE_LIST_TABLE, null, values);
        }
    }

    public ScoreItem query(int position) {
        String query = "SELECT * FROM " + SCORE_LIST_TABLE +
                " ORDER BY " + KEY_SCORE + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;

        ScoreItem entry = new ScoreItem();
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setScore(cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry;
        }
    }

    public String getBestScore() {
        String query = "SELECT * FROM " + SCORE_LIST_TABLE +
                " ORDER BY " + KEY_SCORE + " DESC " +
                "LIMIT " + "1";

        Cursor cursor = null;

        ScoreItem entry = new ScoreItem();
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setScore(cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry.getScore();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ScoreHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + SCORE_LIST_TABLE);
        onCreate(db);
    }

    public long insert(String word) {
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, word);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(SCORE_LIST_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());

        }
        return newId;
    }

    public int update(int id, String word) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_SCORE, word);
            mNumberOfRowsUpdated = mWritableDB.update(SCORE_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "UPDATE EXCEPTION: " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(SCORE_LIST_TABLE,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }

    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, SCORE_LIST_TABLE);
    }

    public Cursor search(String word) {
        String[] columns = new String[]{KEY_SCORE};
        word = "%" + word + "%";
        String where = KEY_SCORE + " LIKE ?";
        String[] whereArgs = new String[]{word};

        Cursor cursor = null;

        try {
            if (mWritableDB == null) {
                mWritableDB = getReadableDatabase();
            }
            cursor = mWritableDB.query(SCORE_LIST_TABLE, columns, where, whereArgs, null, null, null);

        } catch (Exception e) {
            Log.d(TAG, "SEARCH EXCEPTION! " + e.getMessage());
        }
        return cursor;
    }
}
