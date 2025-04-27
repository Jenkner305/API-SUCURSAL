package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tienda.models.Producto;
import com.tienda.repositories.ProductoRepositorio;

public class ProductoController {
    static ProductoRepositorio repositorio = new ProductoRepositorio();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void init() {
        post("/productos", (req, res) -> {
            try {
                Producto producto = gson.fromJson(req.body(), Producto.class);
                if (producto == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(producto);
                res.status(201);
                return gson.toJson(producto);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/productos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/productos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Producto producto = repositorio.obtener(id);
                if (producto != null) {
                    res.type("application/json");
                    return gson.toJson(producto);
                } else {
                    res.status(404);
                    return gson.toJson("Producto no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/productos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Producto producto = gson.fromJson(req.body(), Producto.class);
                if (producto == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Producto productoActualizado = repositorio.actualizar(id, producto);
                if (productoActualizado != null) {
                    res.type("application/json");
                    return gson.toJson(productoActualizado);
                } else {
                    res.status(404);
                    return gson.toJson("Producto no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/productos/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Producto eliminado");
                } else {
                    res.status(404);
                    return gson.toJson("Producto no encontrado");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}