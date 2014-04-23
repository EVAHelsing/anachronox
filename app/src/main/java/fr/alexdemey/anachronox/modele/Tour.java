package fr.alexdemey.anachronox.modele;

public class Tour {
    protected int[] tour = new int[4];
    // 0 pion bien placé, 0 pion mal placé, 4 pions faux
    protected int[] correction = { 0, 0, 4 };
    private int index = 0;


    public void annulerDerniereCouleur() {
        if (this.index == 1 || this.index == 2 || this.index == 3) {
            this.index = this.index - 1;
            this.tour[this.index] = 0;
        }
    }

    public void ajoutCouleur(int couleur) {
        this.tour[this.index] = couleur;
        this.index++;
    }

    /** GETTERS **/
    public int[] getTour() {
        return this.tour;
    }

    public int[] getCorrection() {
        return this.correction;
    }

    public int getIndex() {
        return this.index;
    }

    /** SETTERS **/
    public void setTour(int[] tour) {
        this.tour = tour;
    }

    public void setCorrection(int[] correction) {
        this.correction = correction;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
