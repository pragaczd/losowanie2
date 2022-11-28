package pl.daniel.losowanie2.repository;

import org.springframework.data.repository.CrudRepository;
import pl.daniel.losowanie2.data.WylosowanePary;

public interface WylosowaneParyRepository extends CrudRepository<WylosowanePary, Long> {
    WylosowanePary findAllByOsobaLosujaca(String imie);
}
