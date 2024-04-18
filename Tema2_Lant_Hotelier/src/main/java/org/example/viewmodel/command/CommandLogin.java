package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.MeniuAdminView;
import org.example.view.MeniuAngajatView;
import org.example.view.RoomsView;
import org.example.viewmodel.VM.VMLogin;

import java.io.IOException;
import java.util.List;

public class CommandLogin implements ICommand{
    private VMLogin vmLogin;
    public CommandLogin(VMLogin vmLogin){
        this.vmLogin = vmLogin;
    }

    @Override
    public void execute() throws IOException {
        String email = vmLogin.getEmail();
        String password = vmLogin.getPassword();
        List<Utilizator> utilizatori = new UtilizatorRepo().readAll();
        utilizatori.stream().filter(user->user.getEmail().equals(email) && user.getParola().equals(password))
                .findFirst()
                .ifPresent(user->{
                    if(user.getTipUtilizator().equals(TipUtilizator.ADMINISTRATOR)){
                        // AdminView
                        MeniuAdminView meniuAdminView = new MeniuAdminView();
                        GUIFrameSinglePointAccess.changePanel(meniuAdminView.getMainJPanel(), "Meniu");
                        System.out.println("Admin logged");
                    } else if (user.getTipUtilizator().equals(TipUtilizator.ANGAJAT)) {
                        //angajat
                        System.out.println("Angajat logged");
                        MeniuAngajatView meniuAngajatView = new MeniuAngajatView();
                        GUIFrameSinglePointAccess.changePanel(meniuAngajatView.getMainPanel(), "Meniu");
                    } else if (user.getTipUtilizator().equals(TipUtilizator.CLIENT)) {
                        // client
                        System.out.println("Client logged");
                        try {
                            RoomsView roomsView = new RoomsView(TipUtilizator.CLIENT);
                            GUIFrameSinglePointAccess.changePanel(roomsView.getMainJPanel(), "Rooms");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
    }
}
