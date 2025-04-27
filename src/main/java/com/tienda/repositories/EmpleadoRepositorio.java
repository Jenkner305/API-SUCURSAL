package com.tienda.repositories;

import com.tienda.models.Empleado;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositorio {
    private final List<Empleado> empleados = new ArrayList<>();

    public synchronized void agregar(Empleado empleado) {
        empleados.add(empleado);
    }

    public synchronized List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    public synchronized Empleado obtener(int id) {
        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public synchronized Empleado actualizar(int id, Empleado nuevoEmpleado) {
        Empleado empleado = obtener(id);
        if (empleado != null) {
            empleado.setNombre(nuevoEmpleado.getNombre());
            empleado.setPuesto(nuevoEmpleado.getPuesto());
        }
        return empleado;
    }

    public synchronized boolean eliminar(int id) {
        return empleados.removeIf(e -> e.getId() == id);
    }
}