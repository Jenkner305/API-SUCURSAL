package com.tienda.repositories;

import com.tienda.models.Sucursal;
import java.util.HashMap;
import java.util.Map;

public class SucursalRepositorio {
    private Map<Integer, Sucursal> categorias = new HashMap<>();

    public void agregar(Sucursal categoria) {
        categorias.put(categoria.getId(), categoria);
    }

    public Map<Integer, Sucursal> obtenerTodos() {
        return categorias;
    }

    public Sucursal obtener(int id) {
        return categorias.get(id);
    }

    public Sucursal actualizar(int id, Sucursal nuevaCategoria) {
        if (categorias.containsKey(id)) {
            categorias.put(id, nuevaCategoria);
            return nuevaCategoria;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return categorias.remove(id) != null;
    }
}