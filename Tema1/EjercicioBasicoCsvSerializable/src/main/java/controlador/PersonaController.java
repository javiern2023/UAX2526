package controlador;

import modelo.Persona;
import vista.PersonaView;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {

    private final PersonaView vista;

    private static final String FICHERO =
            "src/main/resources/personas.bin";

    public PersonaController(PersonaView vista) {
        this.vista = vista;
    }

    public void iniciar() {

        crearCarpeta();

        boolean salir = false;

        while (!salir) {

            vista.mostrarMenu();

            int opcion = vista.pedirOpcion();

            switch (opcion) {

                case 1:
                    guardarPersona();
                    break;

                case 2:
                    mostrarPersonas();
                    break;

                case 3:
                    salir = true;
                    vista.mostrarMensaje("Saliendo...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }

        vista.cerrarScanner();
    }

    private void crearCarpeta() {

        File carpeta = new File("src/main/resources");

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    private void guardarPersona() {

        Persona persona = vista.pedirPersona();

        try {

            boolean existe = new File(FICHERO).exists();

            FileOutputStream fos =
                    new FileOutputStream(FICHERO, true);

            ObjectOutputStream oos;

            /*
             * Si el fichero está vacío, escribimos
             * la cabecera normal de ObjectOutputStream.
             *
             * Si ya tiene objetos, no debemos escribir
             * otra cabecera porque corromperíamos el fichero.
             */
            if (!existe || new File(FICHERO).length() == 0) {

                oos = new ObjectOutputStream(fos);

            } else {

                oos = new ObjectOutputStreamSinCabecera(fos);
            }

            oos.writeObject(persona);
            oos.close();

            vista.mostrarMensaje(
                    "Persona guardada correctamente."
            );

        } catch (IOException e) {

            vista.mostrarMensaje(
                    "Error guardando la persona: "
                            + e.getMessage()
            );
        }
    }

    private void mostrarPersonas() {

        List<Persona> personas = leerPersonas();

        vista.mostrarPersonas(personas);
    }

    private List<Persona> leerPersonas() {

        List<Persona> personas = new ArrayList<>();

        File fichero = new File(FICHERO);

        if (!fichero.exists() || fichero.length() == 0) {
            return personas;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(FICHERO))) {

            while (true) {

                Persona persona =
                        (Persona) ois.readObject();

                personas.add(persona);
            }

        } catch (EOFException e) {

            // Fin normal del fichero

        } catch (IOException | ClassNotFoundException e) {

            vista.mostrarMensaje(
                    "Error leyendo el fichero: "
                            + e.getMessage()
            );
        }

        return personas;
    }

    /*
     * ObjectOutputStream personalizado.
     *
     * Sirve para añadir nuevos objetos al fichero
     * sin volver a escribir la cabecera del stream.
     */
    private static class ObjectOutputStreamSinCabecera
            extends ObjectOutputStream {

        public ObjectOutputStreamSinCabecera(
                FileOutputStream fos
        ) throws IOException {

            super(fos);
        }

        @Override
        protected void writeStreamHeader()
                throws IOException {

            reset();
        }
    }
}