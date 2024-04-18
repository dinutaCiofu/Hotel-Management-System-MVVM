package org.example.viewmodel.command;

import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAngajat;
import scala.Int;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CommandDeleteClient implements ICommand{
    private VMAngajat vmAngajat;
    public CommandDeleteClient(VMAngajat vmAngajat){ this.vmAngajat = vmAngajat;}

    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAngajat.getRow();
        DefaultTableModel tableModel = vmAngajat.getModel();
        if(selectedRowIndex != -1){
            Integer id = (Integer) tableModel.getValueAt(selectedRowIndex,0);
            Utilizator deletedClient = new UtilizatorRepo().findById(id);
            new UtilizatorRepo().delete(deletedClient);
            tableModel.removeRow(selectedRowIndex);
        }
    }
}
