package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.Cliente;
import com.tienda.repositories.ClienteRepositorio;

public class ClienteController {
    static ClienteRepositorio repositorio = new ClienteRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/clientes", (req, res) -> {
            try {
                Cliente cliente = gson.fromJson(req.body(), Cliente.class);
                if (cliente == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(cliente);
                res.status(201);
                return gson.toJson(cliente);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/clientes", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/clientes/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Cliente cliente = repositorio.obtener(id);
                if (cliente != null) {
                    res.type("application/json");
                    return gson.toJson(cliente);
                } else {
                    res.status(404);
                    return gson.toJson("Cliente no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/clientes/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Cliente cliente = gson.fromJson(req.body(), Cliente.class);
                if (cliente == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Cliente clienteActualizado = repositorio.actualizar(id, cliente);
                if (clienteActualizado != null) {
                    res.type("application/json");
                    return gson.toJson(clienteActualizado);
                } else {
                    res.status(404);
                    return gson.toJson("Cliente no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/clientes/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Cliente eliminado");
                } else {
                    res.status(404);
                    return gson.toJson("Cliente no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}