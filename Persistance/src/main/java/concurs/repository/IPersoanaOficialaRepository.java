package concurs.repository;


import concurs.model.PersoanaOficiala;

/**
 * Created by Dana on 20-Mar-17.
 */
public interface IPersoanaOficialaRepository extends IRepository<Integer,PersoanaOficiala> {
    PersoanaOficiala validareDate(String username, String password);
    PersoanaOficiala findBy(String username, String parola);
}
