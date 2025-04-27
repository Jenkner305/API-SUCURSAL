package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.Pedido;
import com.tienda.repositories.PedidoRepositorio;

public class PedidoController {
    static PedidoRepositorio repositorio = new PedidoRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/pedidos", (req, res) -> {
            try {
                Pedido pedido = gson.fromJson(req.body(), Pedido.class);
                if (pedido == null || pedido.getId() == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(pedido);
                res.status(201);
                return gson.toJson(pedido);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/pedidos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/pedidos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Pedido pedido = repositorio.obtener(id);
                if (pedido != null) {
                    res.type("application/json");
                    return gson.toJson(pedido);
                } else {
                    res.status(404);
                    return gson.toJson("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/pedidos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Pedido pedido = gson.fromJson(req.body(), Pedido.class);
                if (pedido == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Pedido pedidoActualizado = repositorio.actualizar(id, pedido);
                if (pedidoActualizado != null) {
                    res.type("application/json");
                    return gson.toJson(pedidoActualizado);
                } else {
                    res.status(404);
                    return gson.toJson("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/pedidos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Pedido eliminado");
                } else {
                    res.status(404);
                    return gson.toJson("Pedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}