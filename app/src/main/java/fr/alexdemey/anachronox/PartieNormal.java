package fr.alexdemey.anachronox;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import fr.alexdemey.anachronox.adapter.TourAdapter;
import fr.alexdemey.anachronox.modele.Jeu;

public class PartieNormal extends Activity implements View.OnClickListener {

    private ImageButton bouttonBleu, bouttonBlanc, bouttonJaune, bouttonOrange, bouttonRose, bouttonRouge, bouttonVert, bouttonViolet;
    private Button bouttonMenu, bouttonAnnuler;
    private Dialog myDialog;
    private Context context;
    private Jeu laPartie;
    private Boolean partieGagne = false;
    private TourAdapter tourAdapter;
    private ListView listTour;
    private int attenteVictoire = 500;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie_normal);
        context = this;

		/* Initialisation du jeu et de la liste*/
        // Le jeu
        laPartie = new Jeu();
        // La liste
        tourAdapter = new TourAdapter(this, laPartie);
        listTour = (ListView) findViewById(R.id.listTour);
        listTour.setAdapter(tourAdapter);

        // On ajoute l'adapter au jeu et on lance la partie
        laPartie.setTourAdapter(tourAdapter);
        laPartie.lancerPartie();

        /** Ecouteurs sur les bouttons **/
        bouttonMenu = (Button) findViewById(R.id.retourMenu);
        bouttonAnnuler = (Button) findViewById(R.id.annulerCouleur);
        bouttonMenu.setOnClickListener(this);
        bouttonAnnuler.setOnClickListener(this);

        bouttonBleu = (ImageButton) findViewById(R.id.bleu);
        bouttonBlanc = (ImageButton) findViewById(R.id.ciant);
        bouttonJaune = (ImageButton) findViewById(R.id.jaune);
        bouttonOrange = (ImageButton) findViewById(R.id.orange);
        bouttonRose = (ImageButton) findViewById(R.id.rose);
        bouttonRouge = (ImageButton) findViewById(R.id.rouge);
        bouttonVert = (ImageButton) findViewById(R.id.vert);
        bouttonViolet = (ImageButton) findViewById(R.id.violet);

        bouttonBleu.setOnClickListener(this);
        bouttonBlanc.setOnClickListener(this);
        bouttonJaune.setOnClickListener(this);
        bouttonOrange.setOnClickListener(this);
        bouttonRose.setOnClickListener(this);
        bouttonRouge.setOnClickListener(this);
        bouttonVert.setOnClickListener(this);
        bouttonViolet.setOnClickListener(this);

        /* LA FONT */
        font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        bouttonMenu.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retourMenu:
                menuPause();
                break;

            case R.id.annulerCouleur:
                tourAdapter.annulerCouleur();
                break;

            default:
                // Si la partie n'est pas fini on ajoute la couleur
                if(!partieGagne) {
                    partieGagne = laPartie.ajoutCouleur(v);
                    if(partieGagne) partieGagner();

                    // Si on a 1 couleur ou plus on affiche le boutton annuler
                    //if(tourAdapter.getItem(0).getIndex() < 0) bouttonAnnuler.setVisibility(View.VISIBLE);
                    //if(tourAdapter.getItem(0).getIndex() == 4) bouttonAnnuler.setVisibility(View.INVISIBLE);

                } else {
                    partieGagner();
                }
                break;
        }
    }

    public void onBackPressed() {
        menuPause();
    }

    private void recommencer() {
        partieGagne = false;
        laPartie.recommencer();
    }

    private void partieGagner() {
        /** Thread pour l'affichage du SplashScreen **/

        Thread attenteTread;
        attenteTread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    synchronized(this)
                    {
                        wait(attenteVictoire);
                    }
                } catch(InterruptedException e) {}
                finally
                {
                    finish();
                    Intent i = new Intent(context, Victoire.class);
                    i.putExtra("bonnevaleur", laPartie.getCombinaisonGagnante());
                    context.startActivity(i);
                }
            }
        };
        attenteTread.start();

    }

    private void menuPause() {
        myDialog = new Dialog(context, R.style.DialogTheme);

        myDialog.setContentView(R.layout.menu_quitter);

        Button btnOui = (Button) myDialog.findViewById(R.id.oui);
        Button btnNon = (Button) myDialog.findViewById(R.id.non);
        TextView titre = (TextView) myDialog.findViewById(R.id.titre);

        btnOui.setTypeface(font);
        btnNon.setTypeface(font);
        titre.setTypeface(font);

        myDialog.show();


        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });

        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
                finish();
            }
        });
    }
}
