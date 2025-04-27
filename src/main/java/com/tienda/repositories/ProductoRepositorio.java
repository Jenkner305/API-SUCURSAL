package com.tienda.repositories;

import com.tienda.models.Producto;
import java.util.HashMap;
import java.util.Map;

public class ProductoRepositorio {
    private Map<Integer, Producto> productos = new HashMap<>();

    public Producto agregar(Producto producto) {
        productos.put(producto.getId(), producto);
        return producto;
    }

    public Producto obtener(int id) {
        return productos.get(id);
    }

    public Map<Integer, Producto> obtenerTodos() {
        return productos;
    }

    public Producto actualizar(int id, Producto producto) {
        if (productos.containsKey(id)) {
            productos.put(id, producto);
            return producto;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return productos.remove(id) != null;
    }
}