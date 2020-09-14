package concurs.model;

import java.io.Serializable;

/**
 * Created by Dana on 15-Mar-17.
 */
public class PersoanaOficiala implements Serializable {
    int idPers;
    String nume;
    String username;
    String password;

    public PersoanaOficiala(String nume, String username, String password) {
        this.nume = nume;
        this.username = username;
        this.password = password;
    }

    public PersoanaOficiala(String username, String password){
        this.username = username;
        this.password = password;
        this.nume = "";
    }

    @Override
    public String toString() {
        return "concurs.model.PersoanaOficiala{" +
                "idPers=" + idPers +
                ", nume='" + nume + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public String getNume() {
        return nume;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getIdPers(){
        return idPers;
    }
}
