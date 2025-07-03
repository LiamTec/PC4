package com.tecsup.lab13.controller;

import com.tecsup.lab13.model.Categoria;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        model.addAttribute("categorias", categorias);
        return "categorias/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @PostMapping
    public String crearCategoria(@Valid @ModelAttribute Categoria categoria, 
                                BindingResult result, 
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "categorias/formulario";
        }
        
        categoriaService.createCategoria(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría creada exitosamente");
        return "redirect:/categorias";
    }

    @GetMapping("/{id}")
    public String mostrarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoria);
        return "categorias/detalle";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @PostMapping("/{id}")
    public String actualizarCategoria(@PathVariable Long id, 
                                    @Valid @ModelAttribute Categoria categoria, 
                                    BindingResult result, 
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "categorias/formulario";
        }
        
        categoriaService.updateCategoria(id, categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría actualizada exitosamente");
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoriaService.deleteCategoria(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada exitosamente");
        return "redirect:/categorias";
    }
}