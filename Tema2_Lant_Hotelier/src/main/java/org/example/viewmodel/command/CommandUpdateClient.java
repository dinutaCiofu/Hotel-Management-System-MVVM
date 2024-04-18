package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAngajat;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CommandUpdateClient implements ICommand {
    private VMAngajat vmAngajat;

    public CommandUpdateClient(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAngajat.getRow();
        DefaultTableModel tableModel = vmAngajat.getModel();
        if(selectedRowIndex != -1){
            Integer id = (Integer)tableModel.getValueAt(selectedRowIndex, 0);
            String nume = (String) tableModel.getValueAt(selectedRowIndex, 1);
            String email = (String) tableModel.getValueAt(selectedRowIndex, 2);
            String parola = (String) tableModel.getValueAt(selectedRowIndex, 3);
            Utilizator client = new Utilizator();
            client.setId(id);
            client.setNume(nume);
            client.setEmail(email);
            client.setParola(parola);
            client.setTipUtilizator(TipUtilizator.CLIENT);
            new UtilizatorRepo().update(client);
        }
    }
}
