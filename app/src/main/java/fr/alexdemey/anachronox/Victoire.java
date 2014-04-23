package fr.alexdemey.anachronox;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.alexdemey.anachronox.modele.Score;
import fr.alexdemey.anachronox.bdd.ScoreDAO;
import fr.alexdemey.anachronox.modele.Jeu;

public class Victoire extends Activity {

    ImageView valeur1, valeur2, valeur3, valeur4;
    Button btnRetourMenu;
    Jeu unJeu;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victoire);

        // On créer un jeu et on y intègre la bonne combinaison
        unJeu = new Jeu();
        unJeu.setCombinaisonGagnante(getIntent().getIntArrayExtra("bonnevaleur"));

        btnRetourMenu = (Button) findViewById(R.id.retourMenu);
        btnRetourMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        valeur1 = (ImageView) findViewById(R.id.valeur1);
        valeur2 = (ImageView) findViewById(R.id.valeur2);
        valeur3 = (ImageView) findViewById(R.id.valeur3);
        valeur4 = (ImageView) findViewById(R.id.valeur4);

        valeur1.setImageResource(unJeu.getCouleurById(unJeu.getCombinaisonGagnante()[0]));
        valeur2.setImageResource(unJeu.getCouleurById(unJeu.getCombinaisonGagnante()[1]));
        valeur3.setImageResource(unJeu.getCouleurById(unJeu.getCombinaisonGagnante()[2]));
        valeur4.setImageResource(unJeu.getCouleurById(unJeu.getCombinaisonGagnante()[3]));

        /* LA FONT */
        Typeface font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        TextView titre = (TextView) findViewById(R.id.titre);
        btnRetourMenu.setTypeface(font);
        titre.setTypeface(font);

        // Si on est en mode chrono
        if(getIntent().getBooleanExtra("typeChrono", false)) {
            Long leTemps = getIntent().getLongExtra("chrono", 0);
            // Le timestamp actuel
            Long tsLong = System.currentTimeMillis()/1000;

            // On créer un chrono
            Chronometer chronometer = new Chronometer(this);
            // On récupère le wrappeur
            LinearLayout leWrapper = (LinearLayout) findViewById(R.id.wrapChrono);
            // On met la valeur du chrono et la police
            chronometer.setBase(leTemps);
            chronometer.setTextAppearance(this, R.style.texteTitre);
            chronometer.setTypeface(font);

            leWrapper.addView(chronometer);

            // ENREGISTREMEN EN BDD
            //On créer le score
            Score leScore = new Score(leTemps, tsLong);
            ScoreDAO scoreDAO = new ScoreDAO(this);
            scoreDAO.ajouter(leScore);
        }
    }

}
