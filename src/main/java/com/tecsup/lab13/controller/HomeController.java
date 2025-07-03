package com.tecsup.lab13.controller;

import com.tecsup.lab13.service.CategoriaService;
import com.tecsup.lab13.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "home";
    }
} 