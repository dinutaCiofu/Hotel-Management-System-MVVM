package org.example.viewmodel.command;

import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class CommandPopulateClientiTable {
    private UtilizatorRepo clientiRepo = new UtilizatorRepo();

    public DefaultTableModel setTableColumns(){
        DefaultTableModel model = new DefaultTableModel();
        List<String> columns = List.of("ID", "nume", "email","parola");
        model.setRowCount(0);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        for (String column : columns) {
            model.addColumn(column);
        }
        return model;
    }
    public void setTableRows(DefaultTableModel model, List<Utilizator> clienti){
        for(Utilizator client:clienti){
            Object[] rowData = {
                    client.getId(),
                    client.getNume(),
                    client.getEmail(),
                    client.getParola()
            };
            model.addRow(rowData);
        }
    }
    public DefaultTableModel populateTable(List<Utilizator> clienti){
        DefaultTableModel model = setTableColumns();
        setTableRows(model,clienti);
        return model;
    }
}
