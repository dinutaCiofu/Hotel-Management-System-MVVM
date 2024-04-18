package org.example.viewmodel.command;

import lombok.SneakyThrows;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.LoginView;
import org.example.viewmodel.VM.VMAdministrator;

import java.io.IOException;

public class CommandBackButtonAdmin implements ICommand {
    private VMAdministrator vmAdministrator;

    public CommandBackButtonAdmin(VMAdministrator vmAdministrator) {
        this.vmAdministrator = vmAdministrator;
    }

    @Override
    @SneakyThrows
    public void execute() throws IOException {
        LoginView loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getMainPanel(), "Login");
    }
}
