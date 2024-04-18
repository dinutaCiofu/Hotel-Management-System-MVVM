package org.example.viewmodel.command;

import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.MeniuAngajatView;
import org.example.viewmodel.VM.VMAngajat;

import java.io.IOException;

public class CommandBackButton implements ICommand {
    private VMAngajat vmAngajat;

    public CommandBackButton(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    @Override
    public void execute() throws IOException {
        MeniuAngajatView meniuAngajatView = new MeniuAngajatView();
        GUIFrameSinglePointAccess.changePanel(meniuAngajatView.getMainPanel(), "Meniu");
    }
}
