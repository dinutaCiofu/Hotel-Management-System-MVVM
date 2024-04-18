package org.example.viewmodel.command;

import org.example.model.entities.Rezervare;
import org.example.model.repository.RezervareRepository;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class CommandPopulateRezervariTable {
    private RezervareRepository rezervareRepository = new RezervareRepository();

    public DefaultTableModel setTableColumns() {
        DefaultTableModel model = new DefaultTableModel();
        List<String> columns = List.of("ID", "numeClient", "numarCamera", "pret", "email", "locatie");
        model.setRowCount(0);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (String column : columns) {
            model.addColumn(column);
        }
        return model;
    }

    public void setTableRows(DefaultTableModel model, List<Rezervare> rezervari) {
        for (Rezervare rezervare : rezervari) {
            Object[] rowData = {
                    rezervare.getId(),
                    rezervare.getClient().getNume(),
                    rezervare.getCamera().getNumarCamera(),
                    rezervare.getCamera().getPret(),
                    rezervare.getClient().getEmail(),
                    rezervare.getCamera().getLocatie().getNume()
            };
            model.addRow(rowData);
        }
    }
    public DefaultTableModel populateTable(List<Rezervare> rezervari){
        DefaultTableModel model = setTableColumns();
        setTableRows(model,rezervari);
        return model;
    }
}
