package com.hibernate;

import com.hibernate.controller.AppController;
import jakarta.transaction.SystemException;

public class App {
    public static void main(String[] args) throws SystemException {
        AppController app = new AppController();
        app.mostrarMenu();
    }
}