package pl.daniel.losowanie2.mappers;

public class ImieMapper {

    public static String sprawdzImie(String imie) {
        imie = przygotujImie(imie);
        String zweryfikowaneImie = "";
        switch (imie) {
            case "Kasia":
            case "Katarzyna":
            case "Mama":
               zweryfikowaneImie = "Katarzyna";
               break;
            case "Robert":
            case "Tata":
               zweryfikowaneImie = "Robert";
               break;
            case "Paweł":
            case "Pawel":
               zweryfikowaneImie = "Paweł";
               break;
            case "Martynka":
            case "Martyna":
               zweryfikowaneImie = "Martyna";
               break;
            case "Monika":
               zweryfikowaneImie = "Monika";
               break;
            case "Daniel":
               zweryfikowaneImie = "Daniel";
               break;
            case "Milena":
               zweryfikowaneImie = "Milena";
               break;
            case "Szymon":
               zweryfikowaneImie = "Szymon";
               break;
            default:
                break;
        }
        return zweryfikowaneImie;
    }

    private static String przygotujImie(String imie) {
        imie = imie.replaceAll(" ","");
        imie = imie.replaceAll("\n","");
        imie = imie.replaceAll("\t","");
        return imie;
    }
}
