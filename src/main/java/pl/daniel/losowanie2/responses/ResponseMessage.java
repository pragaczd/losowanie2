package pl.daniel.losowanie2.responses;

import lombok.Getter;
import lombok.Setter;
import pl.daniel.losowanie2.data.Uzytkownicy;

@Getter
@Setter
public class ResponseMessage {
    private String message;
    private String wylosowanaOsoba;

    public ResponseMessage(String message,String wylosowanaOsoba) {
        this.message = message;
    }

    public ResponseMessage(String wylosowanaOsoba) {
        this.wylosowanaOsoba = wylosowanaOsoba;
    }

}
