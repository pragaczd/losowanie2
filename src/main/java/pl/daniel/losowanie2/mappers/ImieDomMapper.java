package pl.daniel.losowanie2.mappers;

public class ImieDomMapper {

    public static String sprawdzImie(String imie) {
        imie = przygotujImie(imie);
        String zweryfikowaneImie = "";
        switch (imie) {
            case "Henio":
                zweryfikowaneImie = "Henio";
                break;
            case "Czesia":
                zweryfikowaneImie = "Czesia";
                break;
            case "Paweł":
            case "Pawel":
                zweryfikowaneImie = "Paweł";
                break;
            case "Wiola":
                zweryfikowaneImie = "Wiola";
                break;
            case "Martynka":
                zweryfikowaneImie = "Martynka";
                break;
            case "Zosia":
                zweryfikowaneImie = "Zosia";
                break;
            case "Daniel":
                zweryfikowaneImie = "Daniel";
                break;
            case "Ania":
                zweryfikowaneImie = "Ania";
                break;
            case "Milena":
                zweryfikowaneImie = "Milena";
                break;
            case "Karol":
                zweryfikowaneImie = "Karol";
                break;
            default:
                break;
        }
        return zweryfikowaneImie;
    }

    private static String przygotujImie(String imie) {
        imie = imie.replaceAll(" ", "");
        imie = imie.replaceAll("\n", "");
        imie = imie.replaceAll("\t", "");
        return imie;
    }
}
