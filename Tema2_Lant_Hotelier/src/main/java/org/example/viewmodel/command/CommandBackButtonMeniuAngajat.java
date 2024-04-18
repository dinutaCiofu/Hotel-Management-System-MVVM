package org.example.viewmodel.command;

import lombok.SneakyThrows;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.LoginView;
import org.example.viewmodel.VM.VMAngajat;

import java.io.IOException;

public class CommandBackButtonMeniuAngajat implements ICommand {
    private VMAngajat vmAngajat;

    public CommandBackButtonMeniuAngajat(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    @Override
    @SneakyThrows
    public void execute() throws IOException {
        LoginView loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getMainPanel(), "Login");
    }
}
