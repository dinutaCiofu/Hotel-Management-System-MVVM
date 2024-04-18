package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.FacilitatiCamera;
import org.example.model.entities.Hotel;
import org.example.model.entities.PozitieCameraMapper;
import org.example.model.repository.CameraRepository;
import org.example.model.repository.HotelRepository;
import org.example.viewmodel.VM.VMAngajat;
import org.example.viewmodel.VM.VMRoom;

import java.io.IOException;
import java.util.List;

public class CommandAddRoom implements ICommand {
    private VMAngajat vmAngajat;

public CommandAddRoom(VMAngajat vmAngajat) {
    this.vmAngajat=vmAngajat;
}
    private Camera cameraFromStrings(String numarCamera, String pret, String disonibilitate, String pozitie, Hotel hotel){
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
        String numarCamera = vmAngajat.getNumarCamera();
        String pret = vmAngajat.getPret();
        String esteDisponibila = vmAngajat.getEsteDisponibila();
        String pozitie = vmAngajat.getPozitie();
        String locatie = vmAngajat.getLocatie();
        CameraRepository cameraRepository = new CameraRepository();
        Camera newCamera = new Camera();
        newCamera.setNumarCamera(numarCamera);
        HotelRepository hotelRepository = new HotelRepository();
        Hotel hotel = hotelRepository.findByNume(locatie);
        if(hotel != null){
            newCamera = cameraFromStrings(numarCamera,pret,esteDisponibila,pozitie,hotel);
            hotel.getCamere().add(newCamera);
            cameraRepository.save(newCamera);
            System.out.println("CommandAddRoom camera salvata");
            hotelRepository.update(hotel);
        }
    }
}
