package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.Empleado;
import com.tienda.repositories.EmpleadoRepositorio;

public class EmpleadoController {
    static EmpleadoRepositorio repositorio = new EmpleadoRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/empleados", (req, res) -> {
            try {
                Empleado empleado = gson.fromJson(req.body(), Empleado.class);
                if (empleado == null || empleado.getNombre() == null || empleado.getPuesto() == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(empleado);
                res.status(201);
                return gson.toJson(empleado);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/empleados", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/empleados/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Empleado empleado = repositorio.obtener(id);
                if (empleado != null) {
                    res.type("application/json");
                    return gson.toJson(empleado);
                } else {
                    res.status(404);
                    return gson.toJson("Empleado no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/empleados/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Empleado empleado = gson.fromJson(req.body(), Empleado.class);
                if (empleado == null || empleado.getNombre() == null || empleado.getPuesto() == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Empleado empleadoActualizado = repositorio.actualizar(id, empleado);
                if (empleadoActualizado != null) {
                    res.type("application/json");
                    return gson.toJson(empleadoActualizado);
                } else {
                    res.status(404);
                    return gson.toJson("Empleado no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/empleados/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Empleado eliminado");
                } else {
                    res.status(404);
                    return gson.toJson("Empleado no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}