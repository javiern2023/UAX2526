package com.hibernate;

import com.hibernate.controller.AppController;
import com.hibernate.dao.HibernateUtil;
import java.util.logging.LogManager;



public class App {
    public static void main(String[] args) {
        LogManager.getLogManager().reset(); //No necesario, para limpiar los logs de java.
        AppController app = new AppController();
        app.mostrarMenu();
        HibernateUtil.shutdown(); // cerrar SessionFactory al final
    }
}
