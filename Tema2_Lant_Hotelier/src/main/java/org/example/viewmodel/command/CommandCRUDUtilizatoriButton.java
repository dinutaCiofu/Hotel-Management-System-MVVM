package org.example.viewmodel.command;

import lombok.SneakyThrows;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.CrudUtilizatoriView;
import org.example.viewmodel.VM.VMAdministrator;

import java.io.IOException;

public class CommandCRUDUtilizatoriButton implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandCRUDUtilizatoriButton(VMAdministrator vmAdministrator){
        this.vmAdministrator = vmAdministrator;
    }

    @Override
    @SneakyThrows
    public void execute() throws IOException {
        CrudUtilizatoriView crudUtilizatoriView = new CrudUtilizatoriView();
        GUIFrameSinglePointAccess.changePanel(crudUtilizatoriView.getMainJPanel(), "CRUD utilizatori");
    }
}
