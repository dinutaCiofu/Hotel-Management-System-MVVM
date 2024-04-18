package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAngajat;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandCreateClientiTable extends CommandPopulateClientiTable implements ICommand {
    private VMAngajat vmAngajat;

    public CommandCreateClientiTable(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    @Override
    public void execute() throws IOException {
        UtilizatorRepo utilizatorRepo = new UtilizatorRepo();
        List<Utilizator> clienti = utilizatorRepo.readAll().stream()
                .filter(utilizator -> utilizator.getTipUtilizator() == TipUtilizator.CLIENT)
                .collect(Collectors.toList());
        DefaultTableModel model = this.populateTable(clienti);
        vmAngajat.setModel(model);
    }
}
