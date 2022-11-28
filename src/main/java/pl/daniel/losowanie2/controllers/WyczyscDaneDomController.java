package pl.daniel.losowanie2.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.daniel.losowanie2.data.UzytkownicyDom;
import pl.daniel.losowanie2.data.WylosowaneParyDom;
import pl.daniel.losowanie2.services.LosowanieDomService;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@RestController
@RequestMapping("wyczyscDom")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class WyczyscDaneDomController {

    @Autowired
    LosowanieDomService losowanieService;

    private static final String CSV_SEPARATOR = ",";

    @SneakyThrows
    @DeleteMapping("/losowankoDom")
    public void wyczysc() throws IOException {

        List<UzytkownicyDom> uzytkownicy = losowanieService.pobierzUzytkownikow();
        BufferedWriter csv = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("uzytkownicyDom.csv"), "UTF-8"));
        for (UzytkownicyDom uzytkownicy1 : uzytkownicy) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(uzytkownicy1.getId());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(uzytkownicy1.getImie());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(uzytkownicy1.isCzyLosuje());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(uzytkownicy1.isCzyWylosowany());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(uzytkownicy1.isCzyLosowano());
            csv.write(oneLine.toString());
            csv.newLine();
        }
        csv.flush();
        csv.close();

        List<WylosowaneParyDom> pary = losowanieService.pobierzWylosowanePary();
        BufferedWriter csvPary = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("paryDom.csv"), "UTF-8"));
        for (WylosowaneParyDom wylosowanePary : pary) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(wylosowanePary.getId());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(wylosowanePary.getOsobaLosujaca());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(wylosowanePary.getOsobaWylosowana());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(wylosowanePary.getData());
            csvPary.write(oneLine.toString());
            csvPary.newLine();
        }
        csvPary.flush();
        csvPary.close();

        losowanieService.wyzerujLosowanie();
    }
}
