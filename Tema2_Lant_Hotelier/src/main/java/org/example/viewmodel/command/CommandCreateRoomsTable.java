package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.TipUtilizator;
import org.example.model.entities.Utilizator;
import org.example.model.repository.CameraRepository;
import org.example.viewmodel.VM.VMRoom;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CommandCreateRoomsTable extends CommandPopulateRoomsTable implements ICommand {
    private VMRoom vmRoom;

    public CommandCreateRoomsTable(VMRoom vmRoom) {
        this.vmRoom = vmRoom;
    }

    @Override
    public void execute() throws IOException {
        TipUtilizator tipUtilizator = vmRoom.getTipUtilizator();

        CameraRepository cameraRepository = new CameraRepository();
        List<Camera> camere = cameraRepository.readAll();
        Collections.sort(camere);
        DefaultTableModel model = this.populateTable(tipUtilizator, camere);
        vmRoom.setModel(model);
//        System.out.println("CommandCraeteRooms: model: "+ vmRoom.getModel().getValueAt(0, 2));
    }
}
