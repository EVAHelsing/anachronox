package fr.alexdemey.anachronox.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.alexdemey.anachronox.modele.Jeu;
import fr.alexdemey.anachronox.modele.Tour;
import fr.alexdemey.anachronox.R;

public class TourAdapter extends BaseAdapter {

    protected ArrayList<Tour> listeDeTour;
    protected LayoutInflater monLayoutInflater;
    protected Context monContext;
    protected Jeu laPartie;

    public TourAdapter(Context context, Jeu partie) {
        this.monLayoutInflater = LayoutInflater.from(context);
        this.listeDeTour = new ArrayList<Tour>();
        this.monContext = context;
        this.laPartie = partie;
    }

    public void add(Tour tour) {
        notifyDataSetChanged();
        this.listeDeTour.add(0, tour);
    }

    public void addAll(ArrayList<Tour> listTour) {
        notifyDataSetChanged();
        this.listeDeTour.addAll(listTour);
    }

    @Override
    public int getCount() {
        return this.listeDeTour.size();
    }

    @Override
    public Tour getItem(int position) {
        // TODO Auto-generated method stub
        return this.listeDeTour.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void reset() {
        notifyDataSetChanged();
        this.listeDeTour.clear();
    }

    public void annulerCouleur() {
        notifyDataSetChanged();
        this.getItem(0).annulerDerniereCouleur();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        if(convertView == null) {
            layoutItem = (LinearLayout) monLayoutInflater.inflate(R.layout.tour, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        // Si la liste est nul
        if(getCount() == 0) {return layoutItem;}

        // Les variables
        Tour leTour = listeDeTour.get(position);

        ImageView valeur1 = (ImageView) layoutItem.findViewById(R.id.valeur1);
        ImageView valeur2 = (ImageView) layoutItem.findViewById(R.id.valeur2);
        ImageView valeur3 = (ImageView) layoutItem.findViewById(R.id.valeur3);
        ImageView valeur4 = (ImageView) layoutItem.findViewById(R.id.valeur4);

        // Pour chaque valeur on applique la couleur
        for(int i = 0; i < 4; i++) {
            int laCouleur = leTour.getTour()[i];
            switch (i) {
                case 0:
                    valeur1.setImageResource(laPartie.getCouleurById(laCouleur));
                    break;
                case 1:
                    valeur2.setImageResource(laPartie.getCouleurById(laCouleur));
                    break;
                case 2:
                    valeur3.setImageResource(laPartie.getCouleurById(laCouleur));
                    break;
                case 3:
                    valeur4.setImageResource(laPartie.getCouleurById(laCouleur));
                    break;

                default:
                    break;
            }
        }

        // Si le tour est fini alors on affiche la correction
        if (laPartie.getTourFini()) {
            ImageView correction1 = (ImageView) layoutItem.findViewById(R.id.correction1);
            ImageView correction2 = (ImageView) layoutItem.findViewById(R.id.correction2);
            ImageView correction3 = (ImageView) layoutItem.findViewById(R.id.correction3);
            ImageView correction4 = (ImageView) layoutItem.findViewById(R.id.correction4);

            int idCouleurATester = 0;
            int correctionApplique;

            /*
                On boucle pour parcourir le tableau de la correction composé de 3 colonnes
                Colonne 1 = nombre de couleurs bien placés
                Colonne 2 = nombre de couleurs mal placés
                Colonne 3 = nombre d'erreur
                La somme des colonnes est toujours égale à 4
             */
            for (int i = 0; i < 3; i++) {
                // Si la colonne est égale à 0 on ne fait pas le traitement
                if (leTour.getCorrection()[i] != 0) {
                    // On boucle sur chaque colonne pour applique la couleur de la correction
                    for (int j = 0; j < leTour.getCorrection()[i]; j++) {
                        // En fonction de la colonne on désigne la couleur à appliqué
                        switch (i) {
                            case 0 :
                                correctionApplique = R.drawable.correction_juste;
                                break;
                            case 1 :
                                correctionApplique = R.drawable.correction_mal_place;
                                break;
                            default :
                                correctionApplique = R.drawable.correction_defaut;
                                break;
                        }

                        // On applique la correction à chaque élement
                        switch (idCouleurATester) {
                            case 0 :
                                correction1.setImageResource(correctionApplique);
                                idCouleurATester++; // On incrémente pour passer à l'élement suivant
                                break;
                            case 1 :
                                correction2.setImageResource(correctionApplique);
                                idCouleurATester++;
                                break;
                            case 2 :
                                correction3.setImageResource(correctionApplique);
                                idCouleurATester++;
                                break;
                            case 3 :
                                correction4.setImageResource(correctionApplique);
                                idCouleurATester++;
                                break;
                        }
                    }
                }
            }
        }

        return layoutItem;
    }

}
