package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.DetallePedido;
import com.tienda.repositories.DetallePedidoRepositorio;

public class DetallePedidoController {
    static DetallePedidoRepositorio repositorio = new DetallePedidoRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/detalle-pedido", (req, res) -> {
            try {
                DetallePedido detalle = gson.fromJson(req.body(), DetallePedido.class);
                if (detalle == null || detalle.getId() == 0) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(detalle);
                res.status(201);
                return gson.toJson(detalle);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/detalle-pedido", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/detalle-pedido/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                DetallePedido detalle = repositorio.obtener(id);
                if (detalle != null) {
                    res.type("application/json");
                    return gson.toJson(detalle);
                } else {
                    res.status(404);
                    return gson.toJson("DetallePedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/detalle-pedido/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                DetallePedido detalle = gson.fromJson(req.body(), DetallePedido.class);
                if (detalle == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                DetallePedido detalleActualizado = repositorio.actualizar(id, detalle);
                if (detalleActualizado != null) {
                    res.type("application/json");
                    return gson.toJson(detalleActualizado);
                } else {
                    res.status(404);
                    return gson.toJson("DetallePedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/detalle-pedido/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("DetallePedido eliminado");
                } else {
                    res.status(404);
                    return gson.toJson("DetallePedido no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}