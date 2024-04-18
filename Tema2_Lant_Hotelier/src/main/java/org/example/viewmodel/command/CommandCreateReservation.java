package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.Rezervare;
import org.example.model.entities.Utilizator;
import org.example.model.repository.CameraRepository;
import org.example.model.repository.RezervareRepository;
import org.example.model.repository.UtilizatorRepo;
import org.example.viewmodel.VM.VMAngajat;

import java.io.IOException;

public class CommandCreateReservation implements ICommand {
    private VMAngajat vmAngajat;

    public CommandCreateReservation(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }


    @Override
    public void execute() throws IOException {
        String selectedCamera = vmAngajat.getSelectedCamera();
        String selectedClient = vmAngajat.getSelectedClient();
        Camera camera = new CameraRepository().findByNumarCamera(selectedCamera);
        Utilizator client = new UtilizatorRepo().findByEmail(selectedClient);
        Rezervare rezervare = new Rezervare();
        rezervare.setCamera(camera);
        rezervare.setClient(client);
        camera.getRezervari().add(rezervare);
        camera.setEsteDisponibila(false);
        client.getRezervari().add(rezervare);
        new RezervareRepository().save(rezervare);
        new UtilizatorRepo().update(client);
        new CameraRepository().update(camera);
    }
}
