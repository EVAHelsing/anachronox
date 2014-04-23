package fr.alexdemey.anachronox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

    private Context context;
    private Intent i;
    private Button btnPartie, btnScore, btnAide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        /** Ecouteur sur les boutons **/
        btnPartie = (Button) findViewById(R.id.partie);
        btnScore = (Button) findViewById(R.id.scores);
        btnAide = (Button) findViewById(R.id.aide);
        btnPartie.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnAide.setOnClickListener(this);

        /* LA FONT */
        Typeface font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        btnPartie.setTypeface(font);
        btnScore.setTypeface(font);
        btnAide.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.partie:
                i = new Intent();
                i.setClass(context, TypeDePartie.class);
                context.startActivity(i);

                break;
            case R.id.scores:
                i = new Intent();
                i.setClass(context, Scores.class);
                context.startActivity(i);

                break;
            case R.id.aide:
                i = new Intent();
                i.setClass(context, Aide.class);
                context.startActivity(i);

                break;
            default:
                break;
        }
    }
}
