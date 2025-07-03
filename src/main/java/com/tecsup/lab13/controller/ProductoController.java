package com.tecsup.lab13.controller;

import com.tecsup.lab13.model.Producto;
import com.tecsup.lab13.service.ProductoService;
import com.tecsup.lab13.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("productos", productos);
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "productos/formulario";
    }

    @PostMapping
    public String crearProducto(@Valid @ModelAttribute Producto producto, 
                               BindingResult result, 
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.getAllCategorias());
            return "productos/formulario";
        }
        
        productoService.createProducto(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto creado exitosamente");
        return "redirect:/productos";
    }

    @GetMapping("/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        return "productos/detalle";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "productos/formulario";
    }

    @PostMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, 
                                   @Valid @ModelAttribute Producto producto, 
                                   BindingResult result, 
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.getAllCategorias());
            return "productos/formulario";
        }
        
        productoService.updateProducto(id, producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado exitosamente");
        return "redirect:/productos";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productoService.deleteProducto(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado exitosamente");
        return "redirect:/productos";
    }
}