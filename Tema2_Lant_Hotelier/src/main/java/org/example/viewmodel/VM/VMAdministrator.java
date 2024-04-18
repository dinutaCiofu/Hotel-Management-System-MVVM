package org.example.viewmodel.VM;

import org.example.model.entities.TipUtilizator;
import org.example.viewmodel.command.*;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;


public class VMAdministrator extends VMRoom {
    private ICommand commandCamereAdminButton;
    private ICommand commandCRUDUtilizatori;
    private ICommand commandBackButtonAdmin;
    private ICommand commandBackButtonCRUDUtilizatori;
    private ICommand commandAddUtilizator;
    private ICommand commandCreateUtilizatoriTable;
    private ICommand commandDeleteUtilizator;
    private ICommand commandUpdateUtilizator;
    private Property<String> email;
    private Property<String> parola;
    private Property<String> nume;
    private Property<String> tipUtilizatorr;

    public VMAdministrator() {
        super(TipUtilizator.ADMINISTRATOR);
        commandCamereAdminButton = new CommandCamereAdminButton(this);
        commandCRUDUtilizatori = new CommandCRUDUtilizatoriButton(this);
        commandBackButtonAdmin = new CommandBackButtonAdmin(this);
        commandBackButtonCRUDUtilizatori = new CommandBackButtonCRUDUtilizatori(this);
        commandAddUtilizator = new CommandAddUtilizator(this);
        commandCreateUtilizatoriTable = new CommandCreateUtilizatoriTable(this);
        commandDeleteUtilizator = new CommandDeleteUtilizator(this);
        commandUpdateUtilizator = new CommandUpdateUtilizator(this);
        nume = PropertyFactory.createProperty("nume", this, String.class);
        email = PropertyFactory.createProperty("email", this, String.class);
        parola = PropertyFactory.createProperty("parola", this, String.class);
        tipUtilizatorr = PropertyFactory.createProperty("tipUtilizator", this, String.class);

    }

    public ICommand getCommandUpdateUtilizator() {
        return commandUpdateUtilizator;
    }

    public ICommand getCommandDeleteUtilizator() {
        return commandDeleteUtilizator;
    }

    public ICommand getCommandCreateUtilizatoriTable() {
        return commandCreateUtilizatoriTable;
    }

    public String getEmail() {
        return email.get();
    }

    public ICommand getCommandBackButtonCRUDUtilizatori() {
        return commandBackButtonCRUDUtilizatori;
    }

    public ICommand getCommandAddUtilizator() {
        return commandAddUtilizator;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getParola() {
        return parola.get();
    }

    public void setParola(String parola) {
        this.parola.set(parola);
    }

    public String getNume() {
        return nume.get();
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getTipUtilizatorr() {
        return tipUtilizatorr.get();
    }

    public void setTipUtilizatorr(String tipUtilizator) {
        this.tipUtilizatorr.set(tipUtilizator);
    }

    public ICommand getCommandBackButtonAdmin() {
        return commandBackButtonAdmin;
    }

    public ICommand getCommandCamereAdminButton() {
        return commandCamereAdminButton;
    }

    public ICommand getCommandCRUDUtilizatori() {
        return commandCRUDUtilizatori;
    }
}
