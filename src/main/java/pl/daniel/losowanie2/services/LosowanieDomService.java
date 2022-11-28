package pl.daniel.losowanie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.daniel.losowanie2.data.Uzytkownicy;
import pl.daniel.losowanie2.data.UzytkownicyDom;
import pl.daniel.losowanie2.data.WylosowanePary;
import pl.daniel.losowanie2.data.WylosowaneParyDom;
import pl.daniel.losowanie2.repository.UzytkownicyDomRepository;
import pl.daniel.losowanie2.repository.UzytkownicyRepository;
import pl.daniel.losowanie2.repository.WylosowaneParyDomRepository;
import pl.daniel.losowanie2.repository.WylosowaneParyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LosowanieDomService {

    @Autowired
    UzytkownicyDomRepository uzytkownicyRepository;

    @Autowired
    WylosowaneParyDomRepository wylosowaneParyRepository;

    public UzytkownicyDom znajdzUzytkownikaLosujacegoIUstawCzyLosuje(String imie) {
        try {
            UzytkownicyDom uzytkownikLosujacy = uzytkownicyRepository.findByImie(imie);
            uzytkownikLosujacy.setCzyLosuje(true);
            uzytkownicyRepository.save(uzytkownikLosujacy);
            return uzytkownikLosujacy;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UzytkownicyDom> pobierzUzytkownikowKtorzyNieLosujaINieSaWylosowani() {
        List<UzytkownicyDom> uzytkownicy = new ArrayList<>();
        uzytkownicyRepository.findAllByCzyLosujeIsFalseAndAndCzyWylosowanyIsFalse()
                .iterator().forEachRemaining(uzytkownicy::add);
        return uzytkownicy;
    }

    public List<UzytkownicyDom> pobierzUzytkownikow() {
        List<UzytkownicyDom> uzytkownicy = new ArrayList<>();
        uzytkownicyRepository.findAll()
                .iterator().forEachRemaining(uzytkownicy::add);
        return uzytkownicy;
    }

    public UzytkownicyDom losuj(String uzytkownikLosujacy, List<UzytkownicyDom> uzytkownicyNiewylosowani) {
        Random random = new Random();
        int randomNumber = 0;
        for (int i = 0; i <= uzytkownicyNiewylosowani.size(); i++) {
            randomNumber = random.nextInt(uzytkownicyNiewylosowani.size());
        }
        UzytkownicyDom uzytkownikWylosowany = uzytkownicyNiewylosowani.get(randomNumber);
        zapiszWylosowanaPare(uzytkownikLosujacy, uzytkownikWylosowany.getImie());

        uzytkownikWylosowany.setCzyWylosowany(true);
        uzytkownicyRepository.save(uzytkownikWylosowany);

        return uzytkownikWylosowany;
    }

    private void zapiszWylosowanaPare(String uzytkownikLosujacy, String uzytkownikWylosowany) {
        wylosowaneParyRepository.save(new WylosowaneParyDom(uzytkownikLosujacy, uzytkownikWylosowany, LocalDateTime.now()));
    }

    public List<WylosowaneParyDom> pobierzWylosowanePary() {
        return (List<WylosowaneParyDom>) wylosowaneParyRepository.findAll();
    }

    public void zakonczLosowanie(String imie) {
        UzytkownicyDom uzytkownikLosujacy = uzytkownicyRepository.findByImie(imie);
        uzytkownikLosujacy.setCzyLosuje(false);
        uzytkownikLosujacy.setCzyLosowano(true);
        uzytkownicyRepository.save(uzytkownikLosujacy);
    }

    public boolean czyJuzLosowalem(String imie) {
        return wylosowaneParyRepository.findAllByOsobaLosujaca(imie) == null;
    }

    public List<UzytkownicyDom> pobierzUzytkownikowKtorzyNieLosowali() {
        return uzytkownicyRepository.findAllByCzyLosowanoIsFalse();
    }

    public void wyzerujLosowanie() {
        Iterable<UzytkownicyDom> uzytkownicyList = uzytkownicyRepository.findAll();
        for (UzytkownicyDom uzytkownicy : uzytkownicyList) {
            uzytkownicy.setCzyLosowano(false);
            uzytkownicy.setCzyWylosowany(false);
            uzytkownicyRepository.save(uzytkownicy);
        }
        wylosowaneParyRepository.deleteAll();
    }
}
