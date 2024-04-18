package org.example;

import lombok.SneakyThrows;
import net.sds.mvvm.bindings.BindingException;
import org.example.model.entities.TipUtilizator;
import org.example.single_point_access.GUIFrameSinglePointAccess;
import org.example.view.CrudClientiView;
import org.example.view.LoginView;
import org.example.view.RoomsView;

import java.io.IOException;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        LoginView loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getMainPanel(),"Login");
//        CrudClientiView crudClientiView = new CrudClientiView();
//        GUIFrameSinglePointAccess.changePanel(crudClientiView.getMainJPanel(), "CRUD");

//        RoomsView roomsView = new RoomsView(TipUtilizator.ANGAJAT);
//        GUIFrameSinglePointAccess.changePanel(roomsView.getMainJPanel(),"Login");
//        Camera camera = new Camera();
//        camera.setNumarCamera("101");
//        camera.setPret(100.0);
//        camera.setEsteDisponibila(true);
//        camera.setPozitie(PozitieCamera.PARTER);
//        camera.setFacilitati(Arrays.asList(FacilitatiCamera.WIFI, FacilitatiCamera.TV));
//
//
//        CameraRepository cameraRepository = new CameraRepository();
//        cameraRepository.save(camera);

    }
}