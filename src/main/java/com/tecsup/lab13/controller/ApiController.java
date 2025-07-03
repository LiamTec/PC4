package com.tecsup.lab13.controller;

import com.tecsup.lab13.model.Producto;
import com.tecsup.lab13.model.Categoria;
import com.tecsup.lab13.service.ProductoService;
import com.tecsup.lab13.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "API Pública", description = "Endpoints REST para productos y categorías")
public class ApiController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    @Operation(summary = "Obtener todas las categorías")
    @GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        return categoriaService.getAllCategorias();
    }
} 