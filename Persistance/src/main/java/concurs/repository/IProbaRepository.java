package concurs.repository;

import concurs.model.Proba;

/**
 * Created by Dana on 07-Apr-17.
 */
public interface IProbaRepository extends IRepository<Integer,Proba> {
    Iterable<String> getDenumiri();
    Iterable<String> getCategorii();
    int getIDProba(String denumire, String categorie);
}
