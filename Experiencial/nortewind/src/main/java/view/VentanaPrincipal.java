package view;

import dao.ProductoDAO;
import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.JSONLoader;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private JTextField txtBusqueda;
    private JTable tabla;
    private ProductoDAO dao = new ProductoDAO();

    public VentanaPrincipal() {
        setTitle("Northwind - Productos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con campo de búsqueda y botones
        JPanel panelSup = new JPanel();
        panelSup.add(new JLabel("Buscar por nombre:"));
        txtBusqueda = new JTextField(20);
        panelSup.add(txtBusqueda);

        JButton btnMostrarTodos = new JButton("Mostrar todos");
        JButton btnFiltrar600 = new JButton("Filtrar precio < 600");
        JButton btnGestion = new JButton("Ir a Gestión");

        panelSup.add(btnMostrarTodos);
        panelSup.add(btnFiltrar600);
        panelSup.add(btnGestion);

        add(panelSup, BorderLayout.NORTH);

        // Tabla para mostrar productos
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Cargar todos los productos al iniciar
        cargarTabla(dao.listarTodos());

        // Filtro en tiempo real por nombre
        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarPorNombre(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarPorNombre(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrarPorNombre(); }

            private void filtrarPorNombre() {
                String texto = txtBusqueda.getText().trim();
                List<Producto> lista;

                if (texto.isEmpty()) {
                    lista = dao.listarTodos();
                } else {
                    lista = dao.filtrarPorNombre(texto);
                }
                cargarTabla(lista);
            }
        });

        // Botón mostrar todos
        btnMostrarTodos.addActionListener(e -> cargarTabla(dao.listarTodos()));

        // Botón filtrar precio < 600
        btnFiltrar600.addActionListener(e -> cargarTabla(dao.filtrarPorPrecio(600)));

        // Botón ir a gestión
        btnGestion.addActionListener(e -> {
            new VentanaGestion(this);
            this.setVisible(false);
        });

        setVisible(true);
    }

    // Método para cargar la tabla con lista de productos
    private void cargarTabla(List<Producto> lista) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Descripción", "Cantidad", "Precio"}, 0);
        for (Producto p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getCantidad(),
                    p.getPrecio()
            });
        }
        tabla.setModel(model);
    }

    // Main
    public static void main(String[] args) {
        // Cargar productos desde JSON en un hilo separado
        new Thread(JSONLoader::cargarProductosDesdeJSON).start();

        // Lanzar GUI
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}



