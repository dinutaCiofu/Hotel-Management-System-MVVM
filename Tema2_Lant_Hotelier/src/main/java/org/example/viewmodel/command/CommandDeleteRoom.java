package org.example.viewmodel.command;

import org.example.model.entities.Camera;
import org.example.model.entities.Hotel;
import org.example.model.repository.CameraRepository;
import org.example.model.repository.HotelRepository;
import org.example.viewmodel.VM.VMAngajat;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class CommandDeleteRoom implements ICommand{
    private VMAngajat vmAngajat;
    public CommandDeleteRoom(VMAngajat vmAngajat){
        this.vmAngajat = vmAngajat;
    }
    @Override
    public void execute() throws IOException {
        int selectedRowIndex = vmAngajat.getRow();
        DefaultTableModel tableModel = vmAngajat.getModel();
        if (selectedRowIndex != -1) {
            Integer id = (Integer) tableModel.getValueAt(selectedRowIndex, 0);
            Camera deletedCamera = new CameraRepository().findById(id);
            Hotel hotel = new HotelRepository().findByNume(deletedCamera.getLocatie().getNume());
            if (hotel != null) {
                hotel.getCamere().remove(deletedCamera);
                new HotelRepository().update(hotel);
                new CameraRepository().delete(deletedCamera);
                tableModel.removeRow(selectedRowIndex);
            }
        }
    }
}
