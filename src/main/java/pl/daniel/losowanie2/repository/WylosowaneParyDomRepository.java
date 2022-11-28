package pl.daniel.losowanie2.repository;

import org.springframework.data.repository.CrudRepository;
import pl.daniel.losowanie2.data.WylosowanePary;
import pl.daniel.losowanie2.data.WylosowaneParyDom;

public interface WylosowaneParyDomRepository extends CrudRepository<WylosowaneParyDom, Long> {
    WylosowaneParyDom findAllByOsobaLosujaca(String imie);
}
