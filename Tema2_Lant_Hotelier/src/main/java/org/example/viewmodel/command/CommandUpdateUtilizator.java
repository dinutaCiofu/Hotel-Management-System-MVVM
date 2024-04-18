package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.model.entities.TipUtilizatorMapper;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAdministrator;
import scala.util.parsing.combinator.testing.Str;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CommandUpdateUtilizator implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandUpdateUtilizator(VMAdministrator vmAdministrator){
        this.vmAdministrator = vmAdministrator;
    }
    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAdministrator.getRow();
        DefaultTableModel tableModel = vmAdministrator.getModel();
        if(selectedRowIndex != -1){
            Integer id = (Integer)tableModel.getValueAt(selectedRowIndex, 0);
            String nume = (String) tableModel.getValueAt(selectedRowIndex, 1);
            String email = (String) tableModel.getValueAt(selectedRowIndex, 2);
            String parola = (String) tableModel.getValueAt(selectedRowIndex, 3);
            TipUtilizator tipUtilizator = (TipUtilizator) tableModel.getValueAt(selectedRowIndex,4);
            Utilizator client = new Utilizator();
            client.setId(id);
            client.setNume(nume);
            client.setEmail(email);
            client.setParola(parola);
            client.setTipUtilizator(tipUtilizator);
            new UtilizatorRepo().update(client);
        }
    }
}
