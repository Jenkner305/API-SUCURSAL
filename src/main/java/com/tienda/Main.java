package com.tienda;

import static spark.Spark.*;
import com.tienda.controllers.SucursalController;
import com.tienda.controllers.DetallePedidoController;
import com.tienda.controllers.ClienteController;
import com.tienda.controllers.ProductoController;
import com.tienda.controllers.PedidoController;
import com.tienda.controllers.EmpleadoController;

public class Main {
    public static void main(String[] args) {
        port(4567);
        SucursalController.init();
        DetallePedidoController.init();
        ClienteController.init();
        ProductoController.init();
        PedidoController.init();
        EmpleadoController.init();
    }
}