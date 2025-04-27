package com.tienda.repositories;

import com.tienda.models.Cliente;
import java.util.HashMap;
import java.util.Map;

public class ClienteRepositorio {
    private Map<Integer, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public Map<Integer, Cliente> obtenerTodos() {
        return clientes;
    }

    public Cliente obtener(int id) {
        return clientes.get(id);
    }

    public Cliente actualizar(int id, Cliente nuevoCliente) {
        if (clientes.containsKey(id)) {
            clientes.put(id, nuevoCliente);
            return nuevoCliente;
        }
        return null;
    }

    public boolean eliminar(int id) {
        return clientes.remove(id) != null;
    }
}