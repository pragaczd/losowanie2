package pl.daniel.losowanie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.daniel.losowanie2.data.Uzytkownicy;
import pl.daniel.losowanie2.data.WylosowanePary;
import pl.daniel.losowanie2.repository.UzytkownicyRepository;
import pl.daniel.losowanie2.repository.WylosowaneParyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LosowanieService {

    @Autowired
    UzytkownicyRepository uzytkownicyRepository;

    @Autowired
    WylosowaneParyRepository wylosowaneParyRepository;

    public Uzytkownicy znajdzUzytkownikaLosujacegoIUstawCzyLosuje(String imie) {
        try {
            Uzytkownicy uzytkownikLosujacy = uzytkownicyRepository.findByImie(imie);
            uzytkownikLosujacy.setCzyLosuje(true);
            uzytkownicyRepository.save(uzytkownikLosujacy);
            return uzytkownikLosujacy;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Uzytkownicy> pobierzUzytkownikowKtorzyNieLosujaINieSaWylosowani() {
        List<Uzytkownicy> uzytkownicy = new ArrayList<>();
        uzytkownicyRepository.findAllByCzyLosujeIsFalseAndAndCzyWylosowanyIsFalse()
                .iterator().forEachRemaining(uzytkownicy::add);
        return uzytkownicy;
    }

    public List<Uzytkownicy> pobierzUzytkownikow() {
        List<Uzytkownicy> uzytkownicy = new ArrayList<>();
        uzytkownicyRepository.findAll()
                .iterator().forEachRemaining(uzytkownicy::add);
        return uzytkownicy;
    }

    public Uzytkownicy losuj(String uzytkownikLosujacy, List<Uzytkownicy> uzytkownicyNiewylosowani) {
        Random random = new Random();
        int randomNumber = 0;
        for (int i = 0; i <= uzytkownicyNiewylosowani.size(); i++) {
            randomNumber = random.nextInt(uzytkownicyNiewylosowani.size());
        }
        Uzytkownicy uzytkownikWylosowany = uzytkownicyNiewylosowani.get(randomNumber);
        zapiszWylosowanaPare(uzytkownikLosujacy, uzytkownikWylosowany.getImie());

        uzytkownikWylosowany.setCzyWylosowany(true);
        uzytkownicyRepository.save(uzytkownikWylosowany);

        return uzytkownikWylosowany;
    }

    private void zapiszWylosowanaPare(String uzytkownikLosujacy, String uzytkownikWylosowany) {
        wylosowaneParyRepository.save(new WylosowanePary(uzytkownikLosujacy, uzytkownikWylosowany, LocalDateTime.now()));
    }

    public List<WylosowanePary> pobierzWylosowanePary() {
        return (List<WylosowanePary>) wylosowaneParyRepository.findAll();
    }

    public void zakonczLosowanie(String imie) {
        Uzytkownicy uzytkownikLosujacy = uzytkownicyRepository.findByImie(imie);
        uzytkownikLosujacy.setCzyLosuje(false);
        uzytkownikLosujacy.setCzyLosowano(true);
        uzytkownicyRepository.save(uzytkownikLosujacy);
    }

    public boolean czyJuzLosowalem(String imie) {
        return wylosowaneParyRepository.findAllByOsobaLosujaca(imie) == null;
    }

    public List<Uzytkownicy> pobierzUzytkownikowKtorzyNieLosowali() {
        return uzytkownicyRepository.findAllByCzyLosowanoIsFalse();
    }

    public void wyzerujLosowanie() {
        Iterable<Uzytkownicy> uzytkownicyList = uzytkownicyRepository.findAll();
        for (Uzytkownicy uzytkownicy : uzytkownicyList) {
            uzytkownicy.setCzyLosowano(false);
            uzytkownicy.setCzyWylosowany(false);
            uzytkownicyRepository.save(uzytkownicy);
        }
        wylosowaneParyRepository.deleteAll();
    }
}
