package concurs.client.gui;

import concurs.model.Copil;
import concurs.model.Proba;
import concurs.services.ConcursException;
import concurs.services.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class CautareViewController extends UnicastRemoteObject implements Serializable{
    @FXML
    ComboBox<String> comboboxProba;
    @FXML
    ComboBox<String> comboboxCategoria;
    @FXML
    Button buttonCauta;
    @FXML
    TableView<Copil> tableviewCopii;
    @FXML
    Label labelEmptyTable;
    @FXML
    TableColumn<Copil,String> columnNume;
    @FXML
    TableColumn<Copil,String> columnPrenume;
    @FXML
    TableColumn<Copil,Integer> columnVarsta;

    private IServer server;

    //constructor
    public CautareViewController() throws RemoteException {
        super();
    }

    void setService(IServer server){
        this.server = server;
    }

    void initData() throws ConcursException {
        initCombobox();
        initTableview();
    }

    private void initTableview() {
        labelEmptyTable.setText("Momentan nu exista participanti inscrisi");
        tableviewCopii.setPlaceholder(labelEmptyTable);
        columnNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        columnPrenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        columnVarsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
    }

    private void initCombobox() throws ConcursException {
        try{
            server.getProbe().forEach(denumire -> comboboxProba.getItems().add(denumire));
            server.getCategorii().forEach(categorie -> comboboxCategoria.getItems().addAll(categorie));
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonCauta(){
        try{
            String denumire = comboboxProba.getSelectionModel().getSelectedItem();
            String categorie = comboboxCategoria.getSelectionModel().getSelectedItem();
            Proba proba = new Proba(denumire, categorie);
            tableviewCopii.getItems().clear();
            ObservableList<Copil> modelCopil = FXCollections.observableArrayList((Collection<? extends Copil>) server.getCopii(proba));
            tableviewCopii.setItems(modelCopil);
        } catch(NullPointerException e){
            showErrorMessage();
        } catch (ConcursException e) {
            e.printStackTrace();
        }
    }

    private void showErrorMessage(){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText("Introduceti date inainte de adaugare");
        message.showAndWait();
    }

}
