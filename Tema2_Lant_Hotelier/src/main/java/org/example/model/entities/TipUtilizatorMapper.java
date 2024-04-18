package org.example.model.entities;

public class TipUtilizatorMapper {
    public static String mapToTipUtilizatorString(TipUtilizator tipUtilizator) {
        switch (tipUtilizator) {
            case CLIENT:
                return "Client";
            case ADMINISTRATOR:
                return "Administrator";
            case ANGAJAT:
                return "Angajat";
            default:
                return "";
        }
    }

    public static TipUtilizator mapToTipUtilizator(String tipUtilizatorString) {
       String tipUtilizatorToLower = tipUtilizatorString.toLowerCase();
        switch (tipUtilizatorToLower) {
            case "client":
                return TipUtilizator.CLIENT;
            case "administrator":
                return TipUtilizator.ADMINISTRATOR;
            case "angajat":
                return TipUtilizator.ANGAJAT;
            default:
                return null;
        }
    }
}