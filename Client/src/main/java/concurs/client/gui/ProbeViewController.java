package concurs.client.gui;

import concurs.model.Proba;
import concurs.services.ConcursException;
import concurs.services.IClient;
import concurs.services.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProbeViewController extends UnicastRemoteObject implements IClient, Serializable{
    @FXML
    TableView<Proba> tableviewProbe;

    @FXML
    TableColumn<Proba, String> columnProba;
    @FXML
    TableColumn<Proba, String> columnCategorie;
    @FXML
    TableColumn<Proba, Integer> columnNrParticipanti;

    private ObservableList<Proba> modelProba;
    private List<Proba> myList;

    public ProbeViewController() throws RemoteException {
        super();
    }

    void setService(IServer server) throws ConcursException, RemoteException {
        IServer server1 = server;
        this.modelProba = FXCollections.observableArrayList((Collection<? extends Proba>) server.getAll());
        tableviewProbe.setItems(modelProba);
    }

    @FXML
    public void initialize(){
        initTableView();
    }

    private void initTableView() {
        myList = new ArrayList<>();
        columnProba.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        columnCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        columnNrParticipanti.setCellValueFactory(new PropertyValueFactory<>("nrParticipanti"));
    }

    @Override
    public synchronized void increasedNrPart(Proba proba) throws ConcursException {
        System.out.println("probe view controller - increasedNrPart");
        List<Proba> myList2 = new ArrayList<>();

        tableviewProbe.getItems().forEach(p -> {
            String denumire = p.getDenumire();
            String categorie = p.getCategorie();
            int nrPart = p.getNrParticipanti();
            if (denumire.equals(proba.getDenumire()) && categorie.equals(proba.getCategorie())){
                myList2.add(new Proba(denumire, categorie, nrPart+1));
            }
            else {
                myList2.add(p);
            }
        });

        myList.clear();
        for (Proba p : myList2){
            myList.add(p);
        }
        probeRefresh();
    }

    void probeRefresh(){
        this.modelProba = FXCollections.observableArrayList(myList);
        tableviewProbe.setItems(modelProba);
    }
}