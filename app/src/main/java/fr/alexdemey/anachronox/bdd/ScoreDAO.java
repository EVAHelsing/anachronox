package fr.alexdemey.anachronox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import fr.alexdemey.anachronox.modele.Score;

/**
 * Created by Alex on 13/03/14.
 */
public class ScoreDAO extends DAOBase {
    public static final String TABLE_NAME = "score";
    public static final String KEY = "id";
    public static final String TEMPS = "temps";
    public static final String DATE = "date";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TEMPS + " INTEGER, " + DATE + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public ScoreDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @param s le score à ajouter à la base
     *          La base comprend max 10 valeurs
     */
    public void ajouter(Score s) {
        open();
        // Si il y a moins de 10 valeur
        ContentValues value = new ContentValues();
        value.put(ScoreDAO.TEMPS, s.getTemps());
        value.put(ScoreDAO.DATE, s.getDate());

        mDb.insert(ScoreDAO.TABLE_NAME, null, value);

        close();
    }

    /**
     * @param id l'identifiant du score à supprimer
     */
    public void supprimer(long id) {
        open();

        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});

        close();
    }

    /**
     * @param s le score modifié
     */
    public void modifier(Score s) {
        open();

        ContentValues value = new ContentValues();
        value.put(TEMPS, s.getTemps());
        value.put(DATE, s.getDate());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[] {String.valueOf(s.getId())});

        close();
    }

    /**
     * @return un tableau des 10 meilleurs scores
     */
    public Score[] selectionnerLaBase() {
        open();

        Cursor c = mDb.rawQuery("SELECT id as _id, temps, date FROM " + TABLE_NAME, null);
        Score[] tabScore = new Score[10];

        while (c.moveToNext()) {
            long id = c.getLong(0);
            long temps = c.getLong(1);
            long date = c.getLong(2);
            tabScore[c.getPosition()] = new Score(id, temps, date);
        }
        close();

        return tabScore;
    }
}