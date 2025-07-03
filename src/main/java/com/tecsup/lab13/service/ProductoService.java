package com.tecsup.lab13.service;

import com.tecsup.lab13.exception.ResourceNotFoundException;
import com.tecsup.lab13.model.Categoria;
import com.tecsup.lab13.model.Producto;
import com.tecsup.lab13.repository.CategoriaRepository;
import com.tecsup.lab13.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
    }

    public Producto createProducto(Producto producto) {
        // Validar que la categoría existe
        if (producto.getCategoria() == null || producto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", producto.getCategoria().getId()));
        
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = getProductoById(id);
        
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        
        if (productoDetails.getCategoria() != null && productoDetails.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDetails.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", productoDetails.getCategoria().getId()));
            producto.setCategoria(categoria);
        }
        
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        Producto producto = getProductoById(id);
        productoRepository.delete(producto);
    }

    public List<Producto> getProductosByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
} 