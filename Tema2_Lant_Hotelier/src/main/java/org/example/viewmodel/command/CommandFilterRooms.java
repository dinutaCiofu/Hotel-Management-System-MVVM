package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.FacilitatiCamera;
import org.example.model.entities.PozitieCameraMapper;
import org.example.model.repository.CameraRepository;
import org.example.viewmodel.VM.VMRoom;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CommandFilterRooms extends CommandPopulateRoomsTable implements ICommand {
    private VMRoom vmRoom;
    public CommandFilterRooms(VMRoom vmRoom){
        this.vmRoom = vmRoom;
    }

    @Override
    public void execute() throws IOException {
        DefaultTableModel model = setTableColumns(vmRoom.getTipUtilizator());
        CameraRepository cameraRepository = new CameraRepository();

       List<Camera> cameraList = cameraRepository.readAll();
       List<FacilitatiCamera> facilitatiCameraList = List.of(FacilitatiCamera.values());
       String selectedFilter = vmRoom.getSelectedFilter();
       if(!facilitatiCameraList.isEmpty() && selectedFilter.equals("Facilitati")){
           System.out.println("Facilitati selected");
         cameraList=  cameraList.stream()
                   .filter(camera -> camera.getFacilitati().containsAll(facilitatiCameraList))
                   .collect(Collectors.toList());
       } else if (selectedFilter.equals("Disponibilitate")) {
           System.out.println("Disponibilitate selected");
           cameraList = cameraList.stream()
                   .filter(camera -> camera.getEsteDisponibila().equals(true))
                   .collect(Collectors.toList());
       } else if (selectedFilter.equals("Pret")) {
           System.out.println("Pret selected");
           cameraList.sort(Comparator.comparing((Camera::getPret)));
       }else{
           cameraList = cameraList.stream()
                   .filter(camera -> PozitieCameraMapper.mapToPozitieString(camera.getPozitie()).equalsIgnoreCase(selectedFilter))
                   .collect(Collectors.toList());
       }
       setTableRows(model,cameraList);
       vmRoom.setModel(model);
    }
}
