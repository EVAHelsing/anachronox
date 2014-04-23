package fr.alexdemey.anachronox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class TypeDePartie extends Activity implements OnClickListener {

    private Context context;
    private Intent i;
    private Button btnNormal, btnChrono;
    private ImageButton btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_de_partie);
        context = this;

        /** Ecouteur sur les boutons **/
        btnNormal = (Button) findViewById(R.id.normal);
        btnChrono = (Button) findViewById(R.id.chrono);
        btnRetour = (ImageButton) findViewById(R.id.logoretour);
        btnNormal.setOnClickListener(this);
        btnChrono.setOnClickListener(this);
        btnRetour.setOnClickListener(this);

        /* LA FONT */
        Typeface font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        btnNormal.setTypeface(font);
        btnChrono.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                i = new Intent();
                i.setClass(context, PartieNormal.class);
                context.startActivity(i);

                break;

            case R.id.chrono:
                i = new Intent();
                i.setClass(context, PartieChrono.class);
                context.startActivity(i);

                //Toast.makeText(getApplicationContext(), "Ce mode de jeu sera bientôt disponible.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logoretour:
                finish();

                break;

            default:
                break;
        }
    }


}
