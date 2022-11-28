package pl.daniel.losowanie2.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.daniel.losowanie2.data.Uzytkownicy;
import pl.daniel.losowanie2.data.WylosowanePary;
import pl.daniel.losowanie2.mappers.ImieMapper;
import pl.daniel.losowanie2.responses.ResponseMessage;
import pl.daniel.losowanie2.services.LosowanieDomService;
import pl.daniel.losowanie2.services.LosowanieService;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("wyczysc")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class WyczyscDaneController {

    @Autowired
    LosowanieService losowanieService;

    private static final String CSV_SEPARATOR = ",";

    @SneakyThrows
    @DeleteMapping("/losowanko")
    public void wyczysc() throws IOException {

        List<Uzytkownicy> uzytkownicy = losowanieService.pobierzUzytkownikow();
        BufferedWriter csv = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("uzytkownicy.csv"), "UTF-8"));
        for (Uzytkownicy uzytkownicy1 : uzytkownicy) {
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

        List<WylosowanePary> pary = losowanieService.pobierzWylosowanePary();
        BufferedWriter csvPary = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pary.csv"), "UTF-8"));
        for (WylosowanePary wylosowanePary : pary) {
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
