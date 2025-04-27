package com.tienda.controllers;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tienda.models.Sucursal;
import com.tienda.repositories.SucursalRepositorio;

public class SucursalController {
    static SucursalRepositorio repositorio = new SucursalRepositorio();
    static Gson gson = new Gson();

    public static void init() {
        post("/sucursal", (req, res) -> {
            try {
                Sucursal categoria = gson.fromJson(req.body(), Sucursal.class);
                if (categoria == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                repositorio.agregar(categoria);
                res.status(201);
                return gson.toJson(categoria);
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        get("/sucursal", (req, res) -> {
            res.type("application/json");
            return gson.toJson(repositorio.obtenerTodos());
        });

        get("/sucursal/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Sucursal categoria = repositorio.obtener(id);
                if (categoria != null) {
                    res.type("application/json");
                    return gson.toJson(categoria);
                } else {
                    res.status(404);
                    return gson.toJson("Sucursal no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });

        put("/sucursal/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                Sucursal categoria = gson.fromJson(req.body(), Sucursal.class);
                if (categoria == null) {
                    res.status(400);
                    return gson.toJson("Error: Datos inválidos");
                }
                Sucursal categoriaActualizada = repositorio.actualizar(id, categoria);
                if (categoriaActualizada != null) {
                    res.type("application/json");
                    return gson.toJson(categoriaActualizada);
                } else {
                    res.status(404);
                    return gson.toJson("Categoría no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            } catch (Exception e) {
                res.status(500);
                return gson.toJson("Error interno del servidor");
            }
        });

        delete("/sucursal/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = repositorio.eliminar(id);
                if (eliminado) {
                    res.status(200);
                    return gson.toJson("Categoría eliminada");
                } else {
                    res.status(404);
                    return gson.toJson("Categoría no encontrada");
                }
            } catch (NumberFormatException e) {
                res.status(400);
                return gson.toJson("Error: ID inválido");
            }
        });
    }
}