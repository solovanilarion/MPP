package concurs.services;

import concurs.model.Proba;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Dana on 30-Mar-17.
 */
public interface IClient extends Remote {
    void increasedNrPart(Proba proba) throws ConcursException, RemoteException;
}
