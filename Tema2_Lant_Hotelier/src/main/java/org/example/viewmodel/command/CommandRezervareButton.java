package org.example.viewmodel.command;

import org.example.model.repository.RezervareRepository;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.RezervareView;
import org.example.viewmodel.VM.VMAngajat;

import java.io.IOException;

public class CommandRezervareButton implements ICommand {
    private VMAngajat vmAngajat;

    public CommandRezervareButton(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    @Override
    public void execute() throws IOException {
        RezervareView rezervareView = new RezervareView();
        GUIFrameSinglePointAccess.changePanel(rezervareView.getMainPanel(), "Rezervare noua");
    }
}
