package docuJSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coche {
    private String marca;
    private String modelo;
    private String tipo;
    private double precio;

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + tipo + ") - " + precio + "€";
    }
}
