package pl.daniel.losowanie2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Losowanie2Application {

    public static void main(String[] args) {
        SpringApplication.run(Losowanie2Application.class, args);
    }

}
