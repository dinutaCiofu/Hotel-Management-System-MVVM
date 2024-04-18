package org.example.viewmodel.VM;

import lombok.SneakyThrows;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import org.example.model.entities.PozitieCamera;
import org.example.model.entities.PozitieCameraMapper;
import org.example.model.entities.TipUtilizator;
import org.example.view.observer.TableModelObserver;
import org.example.viewmodel.command.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VMRoom {
    private ICommand commandCreateRoomsTable;
    private ICommand commandSearchRooms;
    private ICommand commandFilterRooms;
    private TipUtilizator tipUtilizator;
    private Property<DefaultTableModel> model;
    private Property<Integer> row;
    private Property<String> numarCamera;
    private Property<String> pret;
    private Property<String> esteDisponibila;
    private Property<String> pozitie;
    private Property<String> locatie;
    // Properties for the first ComboBox
    private Property<DefaultComboBoxModel<String>> filterByOptions;
    private Property<String> selectedFilter;

    // Properties for the second ComboBox
    private Property<DefaultComboBoxModel<String>> searchByOptions;
    private Property<String> selectedSearch;
    private List<TableModelObserver> observers = new ArrayList<>();

    @SneakyThrows
    public VMRoom(TipUtilizator tipUtilizator) {
        List<String> searchByList = new ArrayList<>();
        searchByList.add("Locatie");
        searchByList.add("Numar");
        this.tipUtilizator = tipUtilizator;
        commandCreateRoomsTable = new CommandCreateRoomsTable(this);
        commandFilterRooms = new CommandFilterRooms(this);
        commandSearchRooms = new CommandSearchRooms(this);
        filterByOptions = PropertyFactory.createProperty("filterByOptions", this, new DefaultComboBoxModel<>());
        selectedFilter = PropertyFactory.createProperty("selectedFilter", this, String.class, ""); // Assuming the default selection is an empty string
        searchByOptions = PropertyFactory.createProperty("searchByOptions", this, new DefaultComboBoxModel<>());
        selectedSearch = PropertyFactory.createProperty("selectedSearch", this, String.class, ""); // Assuming the default selection is an empty string
        model = PropertyFactory.createProperty("table", this, new DefaultTableModel());
        row = PropertyFactory.createProperty("row", this, Integer.class);
        numarCamera = PropertyFactory.createProperty("numarCameraTextField", this, String.class);
        pret = PropertyFactory.createProperty("pretTextField", this, String.class);
        esteDisponibila = PropertyFactory.createProperty("disponibilitateTextField", this, String.class);
        pozitie = PropertyFactory.createProperty("pozitieTextField", this, String.class);
        locatie = PropertyFactory.createProperty("locatieTextField", this, String.class);
        this.searchByOptions = PropertyFactory.createProperty("searchByOptions", this, new DefaultComboBoxModel<>(searchByList.toArray(new String[0])));
        this.filterByOptions = PropertyFactory.createProperty("filterByOptions", this, new DefaultComboBoxModel<>(generateFilterList().toArray(new String[0])));
    }

    private List<String> generateFilterList() {
        List<String> filterList = new ArrayList<>();
        filterList.add("Facilitati");
        filterList.add("Disponibilitate");
        filterList.add("Pret");
        // adauga pozitiile camerelor la lista
        List<PozitieCamera> pozitieCameraList = List.of(PozitieCamera.values());
        for (PozitieCamera pozitieCamera : pozitieCameraList) {
            String pozitieCameraString = PozitieCameraMapper.mapToPozitieString(pozitieCamera);
            if (!pozitieCameraString.isEmpty()) {
                filterList.add(pozitieCameraString);
            }
        }
        return filterList;
    }

    public TipUtilizator getTipUtilizator() {
        return tipUtilizator;
    }

    public ICommand getCommandCreateRoomsTable() {
        return commandCreateRoomsTable;
    }
    public ICommand getCommandFilterRooms() {
        return commandFilterRooms;
    }

    public ICommand getCommandSearchRooms() {
        return commandSearchRooms;
    }

    public String getNumarCamera() {
        return numarCamera.get();
    }

    public String getPret() {
        return pret.get();
    }

    public String getEsteDisponibila() {
        return esteDisponibila.get();
    }

    public String getPozitie() {
        return pozitie.get();
    }

    public String getLocatie() {
        return locatie.get();
    }

    public DefaultTableModel getModel() {
        return model.get();
    }

    public Integer getRow() {
        return this.row.get();
    }


    public void addObserver(TableModelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (TableModelObserver observer : observers) {
            observer.onTableModelChanged(model.get());
        }
    }

    public void setModel(DefaultTableModel model) {
        this.model.set(model);
        notifyObservers(); // notificare atunci se schimba modelul
    }

    public String getSelectedFilter() {
        return selectedFilter.get();
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter.set(selectedFilter);
    }

    public String getSelectedSearch() {
        return selectedSearch.get();
    }

    public void setSelectedSearch(String selectedSearch) {
        this.selectedSearch.set(selectedSearch);
    }
    public DefaultComboBoxModel<String> getFilterByOptionsModel() {
        return filterByOptions.get();
    }

    public DefaultComboBoxModel<String> getSearchByOptionsModel() {
        return searchByOptions.get();
    }
}
