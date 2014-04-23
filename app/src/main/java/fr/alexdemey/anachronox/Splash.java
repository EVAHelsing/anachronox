package fr.alexdemey.anachronox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Splash extends Activity {

    /** Durée d'affichage du SplashScreen **/
    private int splashTime = 5000;
    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        /* LA FONT */
        Typeface font = Typeface.createFromAsset(getAssets(), "LithosPro-Regular.otf");
        TextView chargement = (TextView) findViewById(R.id.chargement);
        chargement.setTypeface(font);

        final Splash splashActivity = this;

        /** Thread pour l'affichage du SplashScreen **/
        splashTread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    synchronized(this)
                    {
                        wait(splashTime);
                    }
                } catch(InterruptedException e) {}
                finally
                {
                    finish();
                    Intent i = new Intent();
                    i.setClass(splashActivity, Main.class);
                    startActivity(i);
                }
            }
        };

        splashTread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        /** Si l'utilisateur fait un mouvement de haut en bas on passe à l'Activity principale */
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(splashTread)
            {
                splashTread.notifyAll();
            }
        }
        return true;
    }

}
