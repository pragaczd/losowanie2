package pl.daniel.losowanie2.repository;

import org.springframework.data.repository.CrudRepository;
import pl.daniel.losowanie2.data.Uzytkownicy;

import java.util.List;

public interface UzytkownicyRepository extends CrudRepository<Uzytkownicy, Long> {

    Uzytkownicy findByImie(String imie);
    Iterable<Uzytkownicy> findAllByCzyLosujeIsFalseAndAndCzyWylosowanyIsFalse();
    List<Uzytkownicy> findAllByCzyLosowanoIsFalse();
}
