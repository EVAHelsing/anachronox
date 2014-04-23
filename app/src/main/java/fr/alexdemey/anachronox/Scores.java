package fr.alexdemey.anachronox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import fr.alexdemey.anachronox.R;
import fr.alexdemey.anachronox.adapter.ScoreAdapter;
import fr.alexdemey.anachronox.bdd.ScoreDAO;
import fr.alexdemey.anachronox.modele.Score;


public class Scores extends Activity implements OnClickListener {
    protected ScoreDAO scoreDAO;
    protected Button btnRetour;
    protected ScoreAdapter scoreAdapter;
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        /* Écouteurs sur les boutons */
        btnRetour = (Button) findViewById(R.id.retourMenu);
        btnRetour.setOnClickListener(this);

        /* On récupère les scores */
        scoreDAO = new ScoreDAO(this);
        Score[] lesScores = scoreDAO.selectionnerLaBase();
        ArrayList<Score> laListeDeScore = new ArrayList<Score>();
        for(int i = 0; i < lesScores.length; i++) {
            laListeDeScore.add(lesScores[i]);
        }

        /* On créer l'adapter et la liste*/
        listView = (ListView) findViewById(R.id.lesScores);
        scoreAdapter = new ScoreAdapter(this, laListeDeScore);
        /* Ajoute l'adapter au layout */
        listView.setAdapter(scoreAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retourMenu:
                finish();

                break;
            default:
                break;
        }
    }
}
