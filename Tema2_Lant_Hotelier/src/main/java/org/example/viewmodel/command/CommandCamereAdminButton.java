package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.RoomsView;
import org.example.viewmodel.VM.VMAdministrator;

import java.io.IOException;

public class CommandCamereAdminButton implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandCamereAdminButton(VMAdministrator vmAdministrator){this.vmAdministrator = vmAdministrator;}
    @Override
    public void execute() throws IOException {

        RoomsView roomsView = new RoomsView(TipUtilizator.ADMINISTRATOR);
        GUIFrameSinglePointAccess.changePanel(roomsView.getMainJPanel(), "Rooms");
    }
}
