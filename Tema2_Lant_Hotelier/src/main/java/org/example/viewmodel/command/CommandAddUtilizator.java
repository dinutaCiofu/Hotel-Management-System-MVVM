package org.example.viewmodel.command;

import org.example.model.entities.TipUtilizatorMapper;
import org.example.model.entities.Utilizator;
import org.example.model.repository.UtilizatorRepo;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.viewmodel.VM.VMAdministrator;

import java.io.IOException;

public class CommandAddUtilizator implements ICommand{
    private VMAdministrator vmAdministrator;
    public CommandAddUtilizator(VMAdministrator vmAdministrator){
        this.vmAdministrator = vmAdministrator;
    }
    @Override
    public void execute() throws IOException {
        String nume = vmAdministrator.getNume();
        String email = vmAdministrator.getEmail();
        String parola = vmAdministrator.getParola();
        String tipUtilizator = vmAdministrator.getTipUtilizatorr();
        Utilizator utilizator = new UtilizatorRepo().findByEmail(email);
        if(utilizator == null){
            utilizator = new Utilizator();
            utilizator.setNume(nume);
            utilizator.setEmail(email);
            utilizator.setParola(parola);
            utilizator.setTipUtilizator(TipUtilizatorMapper.mapToTipUtilizator(tipUtilizator));
            new UtilizatorRepo().save(utilizator);
            vmAdministrator.setNume("");
            vmAdministrator.setEmail("");
            vmAdministrator.setParola("");
            vmAdministrator.setTipUtilizatorr("");
        }else{
            GUIFrameSinglePointAccess.showDialogMessage("Utilizatorul exista deja");
        }
    }
}
