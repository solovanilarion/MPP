package concurs.model;

import java.io.Serializable;

/**
 * Created by Dana on 09-Mar-17.
 */
public class Copil implements Serializable{
    private int id_copil;
    private String nume;
    private String prenume;
    private int varsta;

    public Copil(int id, String nume, String prenume, int varsta){
        id_copil=id;
        this.nume=nume;
        this.prenume=prenume;
        this.varsta=varsta;
    }

    public Copil(String nume, String prenume, int varsta){
        this.nume=nume;
        this.prenume=prenume;
        this.varsta=varsta;
    }

    @Override
    public String toString() {
        return "concurs.model.Copil{" +
                "id_copil=" + id_copil +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta="+ varsta +
                '}';
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getVarsta(){
        return this.varsta;
    }

//    public int getId_copil(){
//        return id_copil;
//    }
}
