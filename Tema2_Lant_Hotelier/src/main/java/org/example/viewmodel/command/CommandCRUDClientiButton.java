package org.example.viewmodel.command;

import lombok.SneakyThrows;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.CrudClientiView;
import org.example.viewmodel.VM.VMRoom;

import java.io.IOException;

public class CommandCRUDClientiButton implements ICommand{
    private VMRoom vmRoom;
    public CommandCRUDClientiButton(VMRoom vmRoom){ this.vmRoom = vmRoom;}
    @Override
    @SneakyThrows
    public void execute() throws IOException {
        CrudClientiView crudClientiView = new CrudClientiView();
        GUIFrameSinglePointAccess.changePanel(crudClientiView.getMainJPanel(), "CRUD Clienti");
    }
}
