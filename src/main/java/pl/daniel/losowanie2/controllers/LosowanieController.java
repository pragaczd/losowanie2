package pl.daniel.losowanie2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.daniel.losowanie2.data.Uzytkownicy;
import pl.daniel.losowanie2.mappers.ImieMapper;
import pl.daniel.losowanie2.responses.ResponseMessage;
import pl.daniel.losowanie2.services.LosowanieService;

import java.util.List;

@RestController
@RequestMapping("losowanie")
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class LosowanieController {

    @Autowired
    LosowanieService losowanieService;

    @PostMapping("/losowanko")
    public ResponseEntity<ResponseMessage> losuj(@RequestBody String imie) {
        imie = ImieMapper.sprawdzImie(imie);
        if (losowanieService.czyJuzLosowalem(imie)) {
            System.out.println("asdf");
            Uzytkownicy uzykownikLosujacy = losowanieService.znajdzUzytkownikaLosujacegoIUstawCzyLosuje(imie);
            if (uzykownikLosujacy == null) {
                return ResponseEntity.ok(new ResponseMessage("Nie ma takiego losujacego", null));
            }
            List<Uzytkownicy> uzytkownicyNiewylosowaniINielosujacy = losowanieService.pobierzUzytkownikowKtorzyNieLosujaINieSaWylosowani();
            Uzytkownicy wylosowanaOsoba = losowanieService.losuj(imie, uzytkownicyNiewylosowaniINielosujacy);
            losowanieService.zakonczLosowanie(imie);
            return ResponseEntity.ok(new ResponseMessage(wylosowanaOsoba.getImie()));
        } else {
            return ResponseEntity.ok(new ResponseMessage("Juz losowałeś cwaniaczku!", null));
        }
    }

    @GetMapping("/uzytkownicy")
    public ResponseEntity<List<Uzytkownicy>> losuj() {
       return ResponseEntity.ok(losowanieService.pobierzUzytkownikowKtorzyNieLosowali());
    }
}
