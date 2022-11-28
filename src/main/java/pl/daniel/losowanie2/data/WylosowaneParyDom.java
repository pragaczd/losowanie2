package pl.daniel.losowanie2.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wylosowane_pary_dom")
public class WylosowaneParyDom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "osoba_losujaca")
    private String osobaLosujaca;

    @Column(name = "osoba_wylosowana")
    private String osobaWylosowana;

    @Column(name = "data")
    private LocalDateTime data;

    public WylosowaneParyDom(String osobaLosujaca, String osobaWylosowana, LocalDateTime data) {
        this.osobaLosujaca = osobaLosujaca;
        this.osobaWylosowana = osobaWylosowana;
        this.data = data;
    }
}
