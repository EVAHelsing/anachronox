package fr.alexdemey.anachronox;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Aide extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

        /* LA FONT */
        Typeface font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        TextView titre = (TextView) findViewById(R.id.titre);
        TextView content = (TextView) findViewById(R.id.content);
        titre.setTypeface(font);
        content.setTypeface(font);
    }

}