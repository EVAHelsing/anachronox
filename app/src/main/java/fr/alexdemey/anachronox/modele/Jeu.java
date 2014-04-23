package fr.alexdemey.anachronox.modele;

import android.view.View;
import fr.alexdemey.anachronox.R;
import fr.alexdemey.anachronox.adapter.TourAdapter;

public class Jeu {

    protected final static int CIANT = 1;
    protected final static int BLEU = 2;
    protected final static int JAUNE = 3;
    protected final static int ORANGE = 4;
    protected final static int ROSE = 5;
    protected final static int ROUGE = 6;
    protected final static int VERT = 7;
    protected final static int VIOLET = 8;

    protected int[] combinaisonGagnante = new int[4];
    protected TourAdapter tourAdapter;
    public Tour tourEnCour;
    protected Boolean tourFini = false;

    private int _pionTeste = 100;

    public Jeu() {
        creationCombinaisonGagnante();
    }

    /**
     * Créer la combinaison gagnante de 4 chiffres aléatoires
     */
    protected void creationCombinaisonGagnante() {
        for (int i = 0; i < 4; i++) {
            // Un nombre entre 3 et 10
            int valeur = 1 + (int) (Math.random() * 8);
            // Création de la combinaison gagnante
            this.combinaisonGagnante[i] = valeur;
        }
    }

    /**
     * Point de départ
     */
    public void lancerPartie() {
        nouveauTour();
    }

    /**
     * Ajoute un Tour
     */
    protected void nouveauTour() {
        this.tourEnCour = new Tour();
        this.tourAdapter.add(tourEnCour);
    }

    /**
     * Ajout d'une couleur en fonction du bouton cliqué
     * @param view
     * @return true si la partie est gagné
     */
    public Boolean ajoutCouleur(View view) {
        // On récupère la couleur du bouton
        int couleur = getCouleurDuBouton(view);
        // Si elle n'existe pas alors on s'arrete
        if (couleur == 0) {
            return false;
        }

        // On ajoute la couleur au tour
        tourEnCour.ajoutCouleur(couleur);
        // On met a jour l'adapter
        tourAdapter.notifyDataSetChanged();

        // fin du tour
        if (tourEnCour.getIndex() > 3) {
            correction(tourEnCour);
            tourFini = true;

            // Si la partie est gagné
            if(tourEnCour.getCorrection()[0] == 4) {
                tourAdapter.notifyDataSetChanged();
                return true;
            }
            nouveauTour();
        }
        return false;
    }


    public void correction(Tour tour) {
        // On copie les combinaisons pour pouvoir la modifier
        int[] copieCombinaisonGagnante = this.combinaisonGagnante.clone();
        int[] tentative = tour.getTour().clone();
        int[] correction = tour.getCorrection();

        if (tentative.length == 4) {
            // Pion bien placé
            for (int i = 0; i < 4; i++) {
                if (tentative[i] == copieCombinaisonGagnante[i]) {
                    correction[0] = correction[0] + 1;
                    correction[2] = correction[2] - 1;

                    // On efface la valeur pour ne pas la retester
                    copieCombinaisonGagnante[i] = _pionTeste;
                    tentative[i] = _pionTeste;
                }
            }

            // Pion mal placé
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tentative[i] == copieCombinaisonGagnante[j]
                            && tentative[i] != _pionTeste) {
                        correction[1] = correction[1] + 1;
                        correction[2] = correction[2] - 1;

                        // On efface la valeur pour ne pas la retester
                        copieCombinaisonGagnante[j] = _pionTeste;
                        break;
                    }
                }
            }
        }

        tour.setCorrection(correction);
    }

    /**
     * Permet de recommencer une partie
     */
    public void recommencer() {
        // On recréer la combinaison gagnante
        creationCombinaisonGagnante();
        // On reset l'adapter
        tourAdapter.reset();
        // On lance un nouveau tour
        nouveauTour();
    }


    /**
     * On passe la view d'un bouton pour récuppérer la couleur
     * @param v View
     * @return int Couleur
     */
    public int getCouleurDuBouton(View v) {
        switch (v.getId()) {
            case R.id.ciant:
                return CIANT;
            case R.id.bleu:
                return BLEU;
            case R.id.jaune:
                return JAUNE;
            case R.id.orange:
                return ORANGE;
            case R.id.rose:
                return ROSE;
            case R.id.rouge:
                return ROUGE;
            case R.id.vert:
                return VERT;
            case R.id.violet:
                return VIOLET;
            default:
                return 0;
        }
    }

    /**
     * On passe l'id de la couleur et on récupère son nom
     * @param idCouleur
     * @return string
     */
    public int getCouleurById(int idCouleur) {
        switch (idCouleur) {
            case CIANT:
                return R.drawable.ciant_1;
            case BLEU:
                return R.drawable.bleu_2;
            case JAUNE:
                return R.drawable.jaune_3;
            case ORANGE:
                return R.drawable.orange_4;
            case ROSE:
                return R.drawable.rose_5;
            case ROUGE:
                return R.drawable.rouge_6;
            case VERT:
                return R.drawable.vert_7;
            case VIOLET:
                return R.drawable.violet_8;
            default:
                return R.drawable.defaut;
        }
    }

    /** Getters **/

    public int[] getCombinaisonGagnante() {
        return this.combinaisonGagnante;
    }

    public TourAdapter getTourAdapter() {
        return this.tourAdapter;
    }

    public Boolean getTourFini() {
        return this.tourFini;
    }

    /** Setters **/

    public void setCombinaisonGagnante(int[] combinaisonGagnante) {
        if (combinaisonGagnante.length == 4) {
            this.combinaisonGagnante = combinaisonGagnante;
        }
    }

    public void setTourAdapter(TourAdapter tourAdapter) {
        this.tourAdapter = tourAdapter;
    }

    public void setTourFini(Boolean tourFini) {
        this.tourFini = tourFini;
    }
}
