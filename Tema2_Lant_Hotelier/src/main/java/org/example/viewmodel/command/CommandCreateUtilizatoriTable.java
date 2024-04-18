package org.example.viewmodel.command;

import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAdministrator;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class CommandCreateUtilizatoriTable extends CommandPopulateUtilizatoriTable implements ICommand{

   private VMAdministrator vmAdministrator;
   public CommandCreateUtilizatoriTable(VMAdministrator vmAdministrator){
       this.vmAdministrator = vmAdministrator;
   }
    @Override
    public void execute() throws IOException {
        List<Utilizator> utilizatorList = new UtilizatorRepo().readAll();
        DefaultTableModel model = this.populateTable(utilizatorList);
        vmAdministrator.setModel(model);
    }
}
