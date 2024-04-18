package org.example.viewmodel.command;

import org.example.model.entities.Rezervare;
import org.example.model.repository.RezervareRepository;
import org.example.viewmodel.VM.VMAngajat;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class CommandCreateRezervariTable extends CommandPopulateRezervariTable implements ICommand{
   private VMAngajat vmAngajat;
   public CommandCreateRezervariTable(VMAngajat vmAngajat){this.vmAngajat = vmAngajat;}
    @Override
    public void execute() throws IOException {
        RezervareRepository rezervareRepository = new RezervareRepository();
        List<Rezervare> rezervareList = rezervareRepository.readAll();
        DefaultTableModel model = this.populateTable(rezervareList);
        vmAngajat.setTableModel(model);
    }
}
