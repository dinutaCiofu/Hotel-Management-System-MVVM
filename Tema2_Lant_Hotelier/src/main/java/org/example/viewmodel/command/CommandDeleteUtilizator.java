package org.example.viewmodel.command;

import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAdministrator;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CommandDeleteUtilizator implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandDeleteUtilizator(VMAdministrator vmAdministrator){
        this.vmAdministrator = vmAdministrator;
    }
    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAdministrator.getRow();
        DefaultTableModel tableModel = vmAdministrator.getModel();
        if(selectedRowIndex != -1){
            Integer id = (Integer) tableModel.getValueAt(selectedRowIndex,0);
            Utilizator deletedUser = new UtilizatorRepo().findById(id);
            new UtilizatorRepo().delete(deletedUser);
            tableModel.removeRow(selectedRowIndex);
        }
    }
}
