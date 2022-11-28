package pl.daniel.losowanie2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.daniel.losowanie2.data.UzytkownicyDom;
import pl.daniel.losowanie2.mappers.ImieDomMapper;
import pl.daniel.losowanie2.mappers.ImieMapper;
import pl.daniel.losowanie2.responses.ResponseMessage;
import pl.daniel.losowanie2.services.LosowanieDomService;
import pl.daniel.losowanie2.services.LosowanieService;

import java.util.List;

@RestController
@RequestMapping("losowanieDom")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class LosowanieDomController {

    @Autowired
    LosowanieDomService losowanieService;

    @PostMapping("/losowankoDom")
    public ResponseEntity<ResponseMessage> losuj(@RequestBody String imie) {
        imie = ImieDomMapper.sprawdzImie(imie);
        if (losowanieService.czyJuzLosowalem(imie)) {
            UzytkownicyDom uzykownikLosujacy = losowanieService.znajdzUzytkownikaLosujacegoIUstawCzyLosuje(imie);
            if (uzykownikLosujacy == null) {
                return ResponseEntity.ok(new ResponseMessage("Nie ma takiego losujacego", null));
            }
            List<UzytkownicyDom> uzytkownicyNiewylosowaniINielosujacy = losowanieService.pobierzUzytkownikowKtorzyNieLosujaINieSaWylosowani();
            UzytkownicyDom wylosowanaOsoba = losowanieService.losuj(imie, uzytkownicyNiewylosowaniINielosujacy);
            losowanieService.zakonczLosowanie(imie);
            return ResponseEntity.ok(new ResponseMessage(wylosowanaOsoba.getImie()));
        } else {
            return ResponseEntity.ok(new ResponseMessage("Juz losowałeś cwaniaczku!", null));
        }
    }

    @GetMapping("/uzytkownicyDom")
    public ResponseEntity<List<UzytkownicyDom>> losuj() {
       return ResponseEntity.ok(losowanieService.pobierzUzytkownikowKtorzyNieLosowali());
    }
}
