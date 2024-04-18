package org.example.view;

import lombok.Getter;
import lombok.SneakyThrows;
import org.example.viewmodel.VM.VMAngajat;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MeniuAngajatView {
    private JLabel titleLabel;
    private JButton CRUDCamereButton;
    private JButton CRUDClientiButton;
    private JButton backButton;
    @Getter
    private JPanel mainPanel;
    private JButton rezervareCameraButton;
    protected VMAngajat vmAngajat;

    public MeniuAngajatView() {
        vmAngajat = new VMAngajat();

        CRUDCamereButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandCRUDCamereButton().execute();
            }
        });
        CRUDClientiButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandCrudClientiButton().execute();
            }
        });
        rezervareCameraButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandRezervareButton().execute();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                vmAngajat.getCommandBackButtonMeniuAngajat().execute();
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

}
