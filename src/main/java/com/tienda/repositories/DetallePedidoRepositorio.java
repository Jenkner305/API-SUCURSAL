package com.tienda.repositories;

import com.tienda.models.DetallePedido;
import java.util.HashMap;
import java.util.Map;

public class DetallePedidoRepositorio {
    private Map<Integer, DetallePedido> detalles = new HashMap<>();

    public DetallePedido agregar(DetallePedido detalle) {
        detalles.put(detalle.getId(), detalle);
        return detalle;
    }

    public DetallePedido obtener(int id) {
        return detalles.get(id);
    }

    public Map<Integer, DetallePedido> obtenerTodos() {
        return detalles;
    }

    public DetallePedido actualizar(int id, DetallePedido detalle) {
        if (detalles.containsKey(id)) {
            detalles.put(id, detalle);
            return detalle;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return detalles.remove(id) != null;
    }
}