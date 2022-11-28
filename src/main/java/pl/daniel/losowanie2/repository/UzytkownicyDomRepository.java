package pl.daniel.losowanie2.repository;

import org.springframework.data.repository.CrudRepository;
import pl.daniel.losowanie2.data.Uzytkownicy;
import pl.daniel.losowanie2.data.UzytkownicyDom;

import java.util.List;

public interface UzytkownicyDomRepository extends CrudRepository<UzytkownicyDom, Long> {

    UzytkownicyDom findByImie(String imie);
    Iterable<UzytkownicyDom> findAllByCzyLosujeIsFalseAndAndCzyWylosowanyIsFalse();
    List<UzytkownicyDom> findAllByCzyLosowanoIsFalse();
}
