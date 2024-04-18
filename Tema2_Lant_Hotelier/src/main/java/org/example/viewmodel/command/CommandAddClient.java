package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.viewmodel.VM.VMAngajat;

import java.io.IOException;

public class CommandAddClient implements ICommand{
    private VMAngajat vmAngajat;
    public CommandAddClient(VMAngajat vmAngajat){this.vmAngajat=vmAngajat;}

    @Override
    public void execute() throws IOException {
        String nume = vmAngajat.getNume();
        String email = vmAngajat.getEmail();
        String parola = vmAngajat.getParola();
        Utilizator client = new UtilizatorRepo().findByEmail(email);
        if(client == null){
            client = new Utilizator();
            client.setTipUtilizator(TipUtilizator.CLIENT);
            client.setNume(nume);
            client.setEmail(email);
            client.setParola(parola);
            new UtilizatorRepo().save(client);
            vmAngajat.setEmail("");
            vmAngajat.setNume("");
            vmAngajat.setParola("");
        }else{
            GUIFrameSinglePointAccess.showDialogMessage("Clientul exista deja");
        }
    }
}
