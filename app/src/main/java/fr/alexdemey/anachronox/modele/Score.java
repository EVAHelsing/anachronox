package fr.alexdemey.anachronox.modele;

/**
 * Created by Alex on 13/03/14.
 */
public class Score {
    private long id;
    private long temps;
    private long date;

    public Score(long temps, long date) {
        super();
        this.id = 0;
        this.temps = temps;
        this.date = date;
    }

    public Score(long id, long temps, long date) {
        super();
        this.id = id;
        this.temps = temps;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getTemps() {
        return temps;
    }

    public void setTemps(Long temps) {
        this.temps = temps;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

}