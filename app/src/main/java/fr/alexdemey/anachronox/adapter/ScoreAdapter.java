package fr.alexdemey.anachronox.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fr.alexdemey.anachronox.R;
import fr.alexdemey.anachronox.modele.Score;

/**
 * Created by Alex on 17/03/14.
 */
public class ScoreAdapter extends BaseAdapter {

    protected ArrayList<Score> listeDeScore;
    protected LayoutInflater monLayoutInflater;
    protected Context monContext;

    public ScoreAdapter(Context context, ArrayList<Score> listeDeScore) {
        this.monLayoutInflater = LayoutInflater.from(context);
        this.listeDeScore = listeDeScore;
        this.monContext = context;
    }

    public void addAll(ArrayList<Score> listeScore) {
        notifyDataSetChanged();
        this.listeDeScore.addAll(listeScore);
    }

    @Override
    public int getCount() {
        return this.listeDeScore.size();
    }

    @Override
    public Score getItem(int position) {
        Score unScore = listeDeScore.get(position);
        return unScore;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        if(convertView == null) {
            layoutItem = (LinearLayout) monLayoutInflater.inflate(R.layout.score, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        if (getCount() == 0) {return layoutItem;}

        // On récupère les textView
        TextView scoreDate = (TextView) layoutItem.findViewById(R.id.lesScoresDates);
        TextView scoreScore = (TextView) layoutItem.findViewById(R.id.lesScoresScore);

        Score unScore = listeDeScore.get(position);

        // On injecte la date et le score
        /*** BUG ***/
        //Log.e("Les Dates ", String.valueOf(leScore.getDate()));
        //scoreDate.setText(String.valueOf(unScore.getDate()));
        //scoreScore.setText(String.valueOf(unScore.getTemps()));

        return layoutItem;
    }
}
