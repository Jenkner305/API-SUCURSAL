package com.tienda.repositories;

import com.tienda.models.Pedido;
import java.util.HashMap;
import java.util.Map;

public class PedidoRepositorio {
    private Map<Integer, Pedido> pedidos = new HashMap<>();

    public Pedido agregar(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    public Pedido obtener(int id) {
        return pedidos.get(id);
    }

    public Map<Integer, Pedido> obtenerTodos() {
        return pedidos;
    }

    public Pedido actualizar(int id, Pedido pedido) {
        if (pedidos.containsKey(id)) {
            pedidos.put(id, pedido);
            return pedido;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return pedidos.remove(id) != null;
    }
}