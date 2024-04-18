package org.example.viewmodel.command;

import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.MeniuAdminView;
import org.example.viewmodel.VM.VMAdministrator;

import java.io.IOException;

public class CommandBackButtonCRUDUtilizatori implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandBackButtonCRUDUtilizatori(VMAdministrator vmAdministrator){
        this.vmAdministrator = vmAdministrator;
    }
    @Override
    public void execute() throws IOException {
        MeniuAdminView meniuAdminView = new MeniuAdminView();
        GUIFrameSinglePointAccess.changePanel(meniuAdminView.getMainJPanel(), "Meniu");
    }
}
