package concurs.client.gui;

import concurs.services.ConcursException;
import concurs.services.IServer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InscriereViewController extends UnicastRemoteObject implements Serializable {
    @FXML
    TextField textfieldNume;
    @FXML
    TextField textfieldPrenume;
    @FXML
    ComboBox<Integer> comboboxVarsta;
    @FXML
    ComboBox<String> comboboxProba1;
    @FXML
    ComboBox<String> comboboxProba2;
    @FXML
    Button buttonInscriere;
    @FXML
    TextField textfieldCategoria1;
    @FXML
    TextField textfieldCategoria2;

    private IServer server;
    private String username;
    private ProbeViewController probeViewController;

    public InscriereViewController() throws RemoteException {
        super();
    }

    void setService(IServer server){
        this.server = server;
    }

    void initData(String username, ProbeViewController probeViewController) throws ConcursException, RemoteException {
        initCombobox();
        this.username = username;
        this.probeViewController = probeViewController;
    }

    private void initCombobox() throws ConcursException, RemoteException {
        for(int i=6;i<=15;i++)
            comboboxVarsta.getItems().addAll(i);
        server.getProbe().forEach(denumire -> comboboxProba1.getItems().add(denumire));
        server.getProbe().forEach(denumire -> comboboxProba2.getItems().add(denumire));
    }

    @FXML
    private void handleButtonInscriere(){
        try{
            String nume = textfieldNume.getText();
            String prenume = textfieldPrenume.getText();
            int varsta = comboboxVarsta.getSelectionModel().getSelectedItem();

            if (nume.equals("") || prenume.equals("")){
                throw new ConcursException("Introduceti toate datele.");
            }

            String den1 = comboboxProba1.getSelectionModel().getSelectedItem();
            String categoria1 = textfieldCategoria1.getText();

            String den2 = comboboxProba2.getSelectionModel().getSelectedItem();
            String categoria2 = textfieldCategoria2.getText();

            server.inscriere(nume, prenume, varsta, den1, categoria1, den2, categoria2, username);
            showMessage(Alert.AlertType.INFORMATION);
            probeViewController.probeRefresh();
        } catch (NullPointerException ex){
            //ex.printStackTrace();
            showErrorMessage("Introduceti date inainte de inscriere.");
        } catch (ConcursException e){
            e.printStackTrace();

            showErrorMessage(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCombobox(){
        try {
            int varsta = comboboxVarsta.getSelectionModel().getSelectedItem();
            String cat = "";
            switch (varsta) {
                case 3:
                case 4:
                case 5:
                    cat = "3-5 ani";
                    break;
                case 6:
                case 7:
                case 8:
                    cat = "6-8 ani";
                    break;
                case 9:
                case 10:
                case 11:
                    cat = "9-11 ani";
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                    cat = "12-15 ani";
                    break;
                default:
                    break;
            }
            textfieldCategoria1.setText(cat);
            textfieldCategoria2.setText(cat);
        } catch (NullPointerException ex){
            showErrorMessage("Mai intai e nevoie sa introduceti varsta");
        }
    }

    private void showErrorMessage(String msg){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(msg);
        message.showAndWait();
    }

    private void showMessage(Alert.AlertType type){
        Alert message = new Alert(type);
        message.setHeaderText("Succes");
        message.setContentText("Inscriere cu succes");
        message.showAndWait();
    }

}
