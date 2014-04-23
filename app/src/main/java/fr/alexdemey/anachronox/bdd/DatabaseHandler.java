package fr.alexdemey.anachronox.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 14/03/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String SCORE_KEY = "id";
    public static final String SCORE_TEMPS = "temps";
    public static final String SCORE_DATE = "date";

    // Les tables
    protected ScoreDAO tableScore;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableScore.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(tableScore.TABLE_DROP);
        onCreate(db);
    }
}
