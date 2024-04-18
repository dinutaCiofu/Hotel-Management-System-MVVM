package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.RoomsView;
import org.example.viewmodel.VM.VMRoom;

import java.io.IOException;

public class CommandCRUDCamereButton implements ICommand{
    private VMRoom vmRoom;
    public CommandCRUDCamereButton(VMRoom vmRoom){this.vmRoom=vmRoom;}
    @Override
    public void execute() throws IOException {
        RoomsView roomsView = new RoomsView(TipUtilizator.ANGAJAT);
        GUIFrameSinglePointAccess.changePanel(roomsView.getMainJPanel(), "CRUD camere");
    }
}
