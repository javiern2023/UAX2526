package controller;

import model.Producto;
import dao.ProductoDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JSONLoader {

    @SuppressWarnings("deprecation")
	public static void cargarProductosDesdeJSON() {
        ProductoDAO dao = new ProductoDAO();
        //try-with-resources: se cierra automáticamente al finalizar el bloque
        //Abre una conexión HTTP a la URL y obtiene los bytes de la respuesta
        try (InputStream is = new java.net.URL("https://dummyjson.com/products").openStream()) {
            Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
            //Se trata todo el contenido como un solo bloque y el \A es el inicio del input. Devuelve todo el JSON
            String jsonText = sc.useDelimiter("\\A").next();
            //Convierte el String en un objeto 
            JSONObject obj = new JSONObject(jsonText);
            //Del JSON obtiene el array de productos por la clave
            JSONArray products = obj.getJSONArray("products");

            for (int i = 0; i < products.length(); i++) {
                //Cada uno de los objetos
            	JSONObject j = products.getJSONObject(i);
                Producto p = new Producto();
                p.setNombre(j.getString("title"));
                p.setDescripcion(j.getString("description"));
                p.setCantidad(j.getInt("stock"));
                p.setPrecio(j.getDouble("price"));
                dao.insertar(p);
            }

        } catch (Exception e) { e.printStackTrace(); }
    }
}
