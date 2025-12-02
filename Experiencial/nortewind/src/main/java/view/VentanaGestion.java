package view;

import dao.EmpleadoDAO;
import dao.ProductoDAO;
import dao.ProductoFavDAO;
import dao.PedidoDAO;
import model.Empleado;
import model.Pedido;
import model.Producto;
import model.ProductoFav;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaGestion extends JFrame {

    private JTable tabla;
    private ProductoDAO productoDAO = new ProductoDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private ProductoFavDAO productoFavDAO = new ProductoFavDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public VentanaGestion(JFrame ventanaAnterior) {
        setTitle("Gestión Northwind");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelSup = new JPanel();

        JButton btnEmpleados = new JButton("Mostrar empleados");
        JButton btnProductos = new JButton("Mostrar productos");
        JButton btnPedidos = new JButton("Mostrar pedidos");
        JButton btnPrecio600 = new JButton("Productos precio < 600");
        JButton btnProductosFav = new JButton("Insertar productos > 1000 en favoritos");
        JButton btnVolver = new JButton("Volver");

        panelSup.add(btnEmpleados);
        panelSup.add(btnProductos);
        panelSup.add(btnPedidos);
        panelSup.add(btnPrecio600);
        panelSup.add(btnProductosFav);
        panelSup.add(btnVolver);

        add(panelSup, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        btnEmpleados.addActionListener(e -> cargarTablaEmpleados(empleadoDAO.listarTodos()));
        btnProductos.addActionListener(e -> cargarTablaProductos(productoDAO.listarTodos()));
        btnPedidos.addActionListener(e ->  cargarTablaPedidos(pedidoDAO.listarTodos()));                        

        btnPrecio600.addActionListener(e -> cargarTablaProductos(productoDAO.filtrarPorPrecio(600)));
        btnProductosFav.addActionListener(e -> {
            List<Producto> lista = productoDAO.filtrarPorPrecioMayor(1000);
            for (Producto p : lista) {
                productoFavDAO.insertar(new ProductoFav(0, p.getId()));
            }
            JOptionPane.showMessageDialog(this, "Productos favoritos insertados!");
        });

        btnVolver.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }

    private void cargarTablaEmpleados(List<Empleado> lista) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Apellidos", "Correo"}, 0);
        for (Empleado e : lista) {
            model.addRow(new Object[]{e.getId(), e.getNombre(), e.getApellidos(), e.getCorreo()});
        }
        tabla.setModel(model);
    }

    private void cargarTablaProductos(List<Producto> lista) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Descripción", "Cantidad", "Precio"}, 0);
        for (Producto p : lista) {
            model.addRow(new Object[]{p.getId(), p.getNombre(), p.getDescripcion(), p.getCantidad(), p.getPrecio()});
        }
        tabla.setModel(model);
    }

    private void cargarTablaPedidos(List<Pedido> lista) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "ID Producto", "Descripción", "Precio Total"}, 0);
        for (Pedido p : lista) {
            model.addRow(new Object[]{p.getId(), p.getIdProducto(), p.getDescripcion(), p.getPrecioTotal()});
        }
        tabla.setModel(model);
    }
}
