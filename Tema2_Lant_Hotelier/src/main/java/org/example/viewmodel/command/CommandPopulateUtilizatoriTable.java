package org.example.viewmodel.command;

import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class CommandPopulateUtilizatoriTable {
    public DefaultTableModel setTableColumns(){
        DefaultTableModel model = new DefaultTableModel();
        List<String> columns = List.of("ID", "nume", "email","parola", "tipUtilizator");
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
    public void setTableRows(DefaultTableModel model, List<Utilizator> utilizatorList){
        for(Utilizator utilizator:utilizatorList){
            Object[] rowData = {
                    utilizator.getId(),
                    utilizator.getNume(),
                    utilizator.getEmail(),
                    utilizator.getParola(),
                    utilizator.getTipUtilizator()
            };
            model.addRow(rowData);
        }
    }
    public DefaultTableModel populateTable(List<Utilizator> utilizatorList){
        DefaultTableModel model = setTableColumns();
        setTableRows(model,utilizatorList);
        return model;
    }
}
