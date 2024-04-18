package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.FacilitatiCamera;
import org.example.model.repository.CameraRepository;
import org.example.viewmodel.VM.VMRoom;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CommandSearchRooms extends CommandPopulateRoomsTable implements ICommand{
    private VMRoom vmRoom;
    public CommandSearchRooms(VMRoom vmRoom){
        this.vmRoom = vmRoom;
    }

    @Override
    public void execute() throws IOException {
        DefaultTableModel model = setTableColumns(vmRoom.getTipUtilizator());
        CameraRepository cameraRepository = new CameraRepository();

        List<Camera> cameraList = cameraRepository.readAll();
        List<FacilitatiCamera> facilitatiCameraList = List.of(FacilitatiCamera.values());
        String selectedSearch = vmRoom.getSelectedSearch();
        if(selectedSearch.equals("Numar")){
            cameraList.sort(Comparator.comparing(Camera::getNumarCamera));
        } else if (selectedSearch.equals("Locatie")) {
            cameraList.sort(Comparator.comparing(Camera::getLocatie));
        }
        setTableRows(model,cameraList);
        vmRoom.setModel(model);
    }
}
