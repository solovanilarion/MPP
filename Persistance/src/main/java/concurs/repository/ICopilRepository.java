package concurs.repository;

import concurs.model.Copil;

/**
 * Created by Dana on 08-Apr-17.
 */
public interface ICopilRepository extends IRepository<Integer, Copil> {
    int getId(String nume, String prenume, int varsta);
}
