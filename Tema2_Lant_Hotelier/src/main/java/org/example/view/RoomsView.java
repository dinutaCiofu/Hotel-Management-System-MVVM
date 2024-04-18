package org.example.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.BindValues;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import org.example.model.entities.TipUtilizator;
import org.example.view.observer.TableModelObserver;
import org.example.viewmodel.VM.VMAdministrator;
import org.example.viewmodel.VM.VMAngajat;
import org.example.viewmodel.VM.VMRoom;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Locale;
@Component
@AllArgsConstructor
public class RoomsView implements TableModelObserver {
    @Getter
    private JPanel mainJPanel;
    private JLabel titluLabel;
    private JButton adaugaCameraButton;
    private JButton actualizeazaButton;
    @Bind(value = "model", target = "filterByOptions.value", type = BindingType.TARGET_TO_SOURCE)
    @Bind(value = "selectedItem", target = "selectedFilter.value", type = BindingType.BI_DIRECTIONAL)
    private JComboBox<String> filterByComboBox;
    private JButton searchButton;
    @BindValues({@Bind(value = "model", target = "model.value", type = BindingType.BI_DIRECTIONAL),
            @Bind(value = "selectedRow", target = "row.value", type = BindingType.BI_DIRECTIONAL)})
    private JTable table;
    private JLabel searchLabel;
    private JButton stergeButton;
    private JLabel filterByLabel;
    @Bind(value = "model", target = "searchByOptions.value", type = BindingType.TARGET_TO_SOURCE)
    @Bind(value = "selectedItem", target = "selectedSearch.value", type = BindingType.BI_DIRECTIONAL)
    private JComboBox<String> searchByComboBox;

    private JButton filterBtn;
    private JPanel crudPanel;
    @Bind(value = "text", target = "numarCamera.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField numarCameraTextField;
    @Bind(value = "text", target = "pret.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField pretTextField;
    @Bind(value = "text", target = "esteDisponibila.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField disponibilitateTextField;
    @Bind(value = "text", target = "pozitie.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField pozitieTextField;
    @Bind(value = "text", target = "locatie.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField locatieTextField;
    private TipUtilizator userLogged;
    protected VMRoom vmRoom;
    protected VMAngajat vmAngajat;

    public RoomsView(TipUtilizator userLogged) throws IOException {
        this.userLogged = userLogged;
        this.vmRoom = new VMRoom(userLogged);
        this.vmRoom.addObserver(this);
        this.vmAngajat = new VMAngajat();

        searchByComboBox.setModel(vmRoom.getSearchByOptionsModel());
        filterByComboBox.setModel(vmRoom.getFilterByOptionsModel());

        if (userLogged == TipUtilizator.ANGAJAT) {
            adaugaCameraButton.setVisible(true);
            actualizeazaButton.setVisible(true);
            stergeButton.setVisible(true);
            crudPanel.setVisible(true);
        } else {
            adaugaCameraButton.setVisible(false);
            actualizeazaButton.setVisible(false);
            stergeButton.setVisible(false);
            crudPanel.setVisible(false);
        }
        try {
            Binder.bind(this, vmRoom);
            Binder.bind(this, vmAngajat);

        } catch (Exception e) {
            e.printStackTrace();
        }
        vmRoom.getCommandCreateRoomsTable().execute();
        searchByComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) e.getItem();
                    vmRoom.setSelectedSearch(selected);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                vmRoom.getCommandSearchRooms().execute();
            }
        });
        filterByComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) e.getItem();
                    vmRoom.setSelectedFilter(selected);
                }
            }
        });
        filterBtn.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                vmRoom.getCommandFilterRooms().execute();
            }
        });
        adaugaCameraButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandAddRoom().execute();
                vmRoom.getCommandCreateRoomsTable().execute();
            }
        });
        actualizeazaButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandUpdateRoom().execute();
                vmRoom.getCommandCreateRoomsTable().execute();
            }
        });
        stergeButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandDeleteRoom().execute();
                vmRoom.getCommandCreateRoomsTable().execute();
            }
        });
    }


    /**
     * @noinspection ALL
     */
    private Font getFont(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }


    @Override
    public void onTableModelChanged(DefaultTableModel model) {
        SwingUtilities.invokeLater(() -> table.setModel(model));
    }
}
