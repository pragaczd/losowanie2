package pl.daniel.losowanie2.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "uzytkownicy")
public class Uzytkownicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "czy_losuje")
    private boolean czyLosuje;

    @Column(name = "czy_wylosowany")
    private boolean czyWylosowany;

    @Column(name = "czy_losowano")
    private boolean czyLosowano;

    @Column(name = "prezent")
    private String prezent;

}
