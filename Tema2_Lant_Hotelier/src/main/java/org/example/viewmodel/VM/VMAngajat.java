package org.example.viewmodel.VM;

import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import org.example.model.entities.Camera;
import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.CameraRepository;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.command.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VMAngajat extends VMRoom {
    private ICommand commandCRUDCamereButton;
    private ICommand commandAddRoom;
    private ICommand commandUpdateRoom;
    private ICommand commandDeleteRoom;
    private ICommand commandCrudClientiButton;
    private ICommand commandCreateClientiTable;
    private ICommand commandAddClient;
    private ICommand commandDeleteClient;
    private ICommand commandUpdateClient;
    private ICommand commandBackButton;
    private ICommand commandBackButtonMeniuAngajat;
    private ICommand commandRezervareButton;
    private ICommand commandCreateReservation;
    private ICommand commandCreateRezervariTable;
    private ICommand commandDownloadReservations;
    private Property<String> nume;
    private Property<String> email;
    private Property<String> parola;
    private Property<DefaultComboBoxModel<String>> camere;
    private Property<String> selectedCamera;
    private Property<DefaultComboBoxModel<String>> clienti;
    private Property<String> selectedClient;
    private Property<DefaultTableModel> tableModel;


    public VMAngajat() {
        super(TipUtilizator.ANGAJAT);
        commandCRUDCamereButton = new CommandCRUDCamereButton(this);
        commandAddRoom = new CommandAddRoom(this);
        commandUpdateRoom = new CommandUpdateCamera(this);
        commandDeleteRoom = new CommandDeleteRoom(this);
        commandCrudClientiButton = new CommandCRUDClientiButton(this);
        commandCreateClientiTable = new CommandCreateClientiTable(this);
        commandAddClient = new CommandAddClient(this);
        commandDeleteClient = new CommandDeleteClient(this);
        commandUpdateClient = new CommandUpdateClient(this);
        commandBackButton = new CommandBackButton(this);
        commandBackButtonMeniuAngajat = new CommandBackButtonMeniuAngajat(this);
        commandRezervareButton = new CommandRezervareButton(this);
        commandCreateReservation = new CommandCreateReservation(this);
        commandCreateRezervariTable = new CommandCreateRezervariTable(this);
        commandDownloadReservations = new CommandDownloadReservations(this);
        camere = PropertyFactory.createProperty("camere", this, new DefaultComboBoxModel<>(generateCamereList().toArray(new String[0])));
        selectedCamera = PropertyFactory.createProperty("selectedCamera", this, String.class);
        clienti = PropertyFactory.createProperty("clienti", this, new DefaultComboBoxModel<>(generateClientiList().toArray(new String[0])));
        selectedClient = PropertyFactory.createProperty("selectedClient", this, String.class);

        nume = PropertyFactory.createProperty("nume", this, String.class);
        email = PropertyFactory.createProperty("email", this, String.class);
        parola = PropertyFactory.createProperty("parola", this, String.class);

        tableModel = PropertyFactory.createProperty("tableRezervari", this, new DefaultTableModel());
    }

    public ICommand getCommandDownloadReservations() {
        return commandDownloadReservations;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel.set(tableModel);
    }

    public ICommand getCommandCreateRezervariTable() {
        return commandCreateRezervariTable;
    }

    public ICommand getCommandRezervareButton() {
        return commandRezervareButton;
    }

    public ICommand getCommandBackButtonMeniuAngajat() {
        return commandBackButtonMeniuAngajat;
    }

    public ICommand getCommandBackButton() {
        return commandBackButton;
    }

    public ICommand getCommandUpdateClient() {
        return commandUpdateClient;
    }

    public ICommand getCommandAddClient() {
        return commandAddClient;
    }

    public DefaultTableModel getTableModel() {
        return tableModel.get();
    }

    public ICommand getCommandDeleteClient() {
        return commandDeleteClient;
    }

    public ICommand getCommandAddRoom() {
        return commandAddRoom;
    }

    public ICommand getCommandCreateReservation() {
        return commandCreateReservation;
    }

    public ICommand getCommandCRUDCamereButton() {
        return this.commandCRUDCamereButton;
    }

    public ICommand getCommandUpdateRoom() {
        return commandUpdateRoom;
    }

    public ICommand getCommandCrudClientiButton() {
        return commandCrudClientiButton;
    }

    public ICommand getCommandCreateClientiTable() {
        return commandCreateClientiTable;
    }

    public ICommand getCommandDeleteRoom() {
        return commandDeleteRoom;
    }

    public String getNume() {
        return this.nume.get();
    }

    public String getEmail() {
        return this.email.get();
    }

    public String getParola() {
        return this.parola.get();
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setParola(String parola) {
        this.parola.set(parola);
    }

    public DefaultComboBoxModel<String> getCamere() {
        return camere.get();
    }

    public DefaultComboBoxModel<String> getClienti() {
        return clienti.get();
    }

    public String getSelectedCamera() {
        return selectedCamera.get();
    }

    public String getSelectedClient() {
        return selectedClient.get();
    }

    private List<String> generateCamereList() {
        List<String> camereList = new ArrayList<>();
        List<Camera> camere = new CameraRepository().readAll();
        for (Camera camera : camere) {
            if (camera.getEsteDisponibila() == true) {
                camereList.add(camera.getNumarCamera());
            }
        }
        return camereList;
    }

    private List<String> generateClientiList() {
        List<String> clientiList = new ArrayList<>();
        List<Utilizator> clienti = new UtilizatorRepo().readAll().stream()
                .filter(utilizator -> utilizator.getTipUtilizator() == TipUtilizator.CLIENT)
                .collect(Collectors.toList());
        for (Utilizator client : clienti) {
            clientiList.add(client.getEmail());
        }
        return clientiList;
    }
}
