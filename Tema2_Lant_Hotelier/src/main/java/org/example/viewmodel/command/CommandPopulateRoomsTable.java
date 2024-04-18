package org.example.viewmodel.command;

import org.example.model.entities.*;
import org.example.model.repository.HotelRepository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandPopulateRoomsTable {
    private HotelRepository hotelRepository = new HotelRepository();

    public DefaultTableModel setTableColumns(TipUtilizator tipUtilizator) {
        DefaultTableModel model = new DefaultTableModel();
        List<String> columns = new ArrayList<>();
        model.setRowCount(0);
        columns = List.of("ID", "Numar Camera", "Pret", "Disponibilitate", "Pozitie", "Locatie");
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return tipUtilizator.equals(TipUtilizator.ANGAJAT);
            }
        };

        for (String column : columns) {
            model.addColumn(column);
        }
        return model;
    }

    public void setTableRows(DefaultTableModel model, List<Camera> camere) {
        for (Camera camera : camere) {
            Object[] rowData = {
                    camera.getId(),
                    camera.getNumarCamera(),
                    String.valueOf(camera.getPret()),
                    camera.getEsteDisponibila() ? "Disponibila" : "Indisponibila",
                    PozitieCameraMapper.mapToPozitieString(camera.getPozitie()),
                    getLocatieCamera(camera) != null ? getLocatieCamera(camera) : "Nedefinit"
            };
            model.addRow(rowData);
        }
    }

    public DefaultTableModel populateTable(TipUtilizator tipUtilizator, List<Camera> camere) {
        DefaultTableModel model = setTableColumns(tipUtilizator);
        setTableRows(model, camere);
        return model;
    }

    private String getLocatieCamera(Camera camera) {

        if (camera.getLocatie() != null) {
            Integer hotelId = camera.getLocatie().getId();
            Hotel hotel = hotelRepository.findById(hotelId);
            if (hotel != null) {
                return hotel.getNume();
            }
        } else {
            System.out.println("CommandPopulateRooms: nu exista locatie camera");
            System.out.println(camera.getLocatie());
        }
        return null;
    }


}
