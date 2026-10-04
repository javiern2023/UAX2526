
import controlador.PersonaController;
import vista.PersonaView;

public class App {

    public static void main(String[] args) {

        PersonaView vista = new PersonaView();

        PersonaController controlador =
                new PersonaController(vista);

        controlador.iniciar();
    }
}