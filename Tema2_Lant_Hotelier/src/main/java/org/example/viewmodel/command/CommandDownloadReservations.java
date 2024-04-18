package org.example.viewmodel.command;

import org.example.viewmodel.VM.VMAngajat;
import org.json.JSONArray;
import org.json.JSONObject;


import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandDownloadReservations implements ICommand {
    private VMAngajat vmAngajat;

    public CommandDownloadReservations(VMAngajat vmAngajat) {
        this.vmAngajat = vmAngajat;
    }

    private void writeCsv(List<String[]> data) throws IOException {
        try (PrintWriter csvOutput = new PrintWriter(Files.newBufferedWriter(Paths.get("reservations.csv")))) {
            for (String[] row : data) {
                csvOutput.println(String.join(",", row));
            }
        }
    }

    private void writeTxt(List<String[]> data) throws IOException {
        try (PrintWriter txtOutput = new PrintWriter(Files.newBufferedWriter(Paths.get("reservations.txt")))) {
            for (String[] row : data) {
                txtOutput.println(String.join(" | ", row));
            }
        }
    }

    private void writeJson(List<String[]> data) throws IOException {
        JSONArray jsonArray = new JSONArray();
        String[] columnNames = new String[]{"ID", "Client Name", "Room Number", "Price", "Email", "Location"};

        for (String[] rowData : data) {
            JSONObject obj = new JSONObject();
            for (int i = 0; i < rowData.length; i++) {
                obj.put(columnNames[i], rowData[i]);
            }
            jsonArray.put(obj);
        }

        try (FileWriter file = new FileWriter("reservations.json")) {
            file.write(jsonArray.toString(4));
        }
    }


    private void writeXml(List<String[]> data) throws IOException {
        try (PrintWriter xmlOutput = new PrintWriter(Files.newBufferedWriter(Paths.get("reservations.xml")))) {
            xmlOutput.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlOutput.println("<reservations>");
            String[] columnNames = {"id", "numeClient", "numarCamera", "pret", "email", "locatie"};
            for (String[] row : data) {
                xmlOutput.println("  <reservation>");
                for (int i = 0; i < row.length; i++) {
                    xmlOutput.printf("    <%s>%s</%s>%n", columnNames[i], row[i], columnNames[i]);
                }
                xmlOutput.println("  </reservation>");
            }
            xmlOutput.println("</reservations>");
        }
    }

    @Override
    public void execute() throws IOException {
        DefaultTableModel tableModel = vmAngajat.getTableModel();
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int colCount = tableModel.getColumnCount();
            String[] row = new String[colCount];
            for (int j = 0; j < colCount; j++) {
                row[j] = tableModel.getValueAt(i, j).toString();
            }
            list.add(row);
        }
        writeCsv(list);
        writeTxt(list);
        writeXml(list);
        writeJson(list);
    }
}
