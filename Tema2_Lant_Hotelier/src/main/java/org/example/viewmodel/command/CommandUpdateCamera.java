package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.FacilitatiCamera;
import org.example.model.entities.Hotel;
import org.example.model.entities.PozitieCameraMapper;
import org.example.model.repository.CameraRepository;
import org.example.model.repository.HotelRepository;
import org.example.viewmodel.VM.VMAngajat;
import org.example.viewmodel.VM.VMRoom;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class CommandUpdateCamera implements ICommand {
    private VMAngajat vmAngajat;
    public CommandUpdateCamera(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    private Camera cameraFromStrings(String numarCamera, String pret, String disonibilitate, String pozitie, Hotel hotel) {
        Camera camera = new Camera();
        camera.setNumarCamera(numarCamera);
        camera.setPret(Double.valueOf(pret));
        camera.setEsteDisponibila(disonibilitate.equals("Disponibila") ? true : false);
        camera.setFacilitati(List.of(FacilitatiCamera.values()));
        camera.setLocatie(hotel);
        camera.setPozitie(PozitieCameraMapper.mapToPozitieCamera(pozitie));
        return camera;
    }

    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAngajat.getRow();
        DefaultTableModel tableModel = vmAngajat.getModel();
        if (selectedRowIndex != -1) {
            Integer id = (Integer) tableModel.getValueAt(selectedRowIndex, 0);
            String numarCamera = (String) tableModel.getValueAt(selectedRowIndex, 1);
            String pret = (String) tableModel.getValueAt(selectedRowIndex, 2);
            String disponibilitate = (String) tableModel.getValueAt(selectedRowIndex, 3);
            String pozitie = (String) tableModel.getValueAt(selectedRowIndex, 4);
            String locatie = (String) tableModel.getValueAt(selectedRowIndex, 5);
            Hotel hotel = new HotelRepository().findByNume(locatie);
            if (hotel != null) {
                Camera camera = cameraFromStrings(numarCamera, pret, disponibilitate, pozitie, hotel);
                camera.setId(id);
                new CameraRepository().update(camera);
            }
        }
    }
}
