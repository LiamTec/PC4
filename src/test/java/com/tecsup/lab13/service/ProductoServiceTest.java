package com.tecsup.lab13.service;

import com.tecsup.lab13.exception.ResourceNotFoundException;
import com.tecsup.lab13.model.Categoria;
import com.tecsup.lab13.model.Producto;
import com.tecsup.lab13.repository.CategoriaRepository;
import com.tecsup.lab13.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Electrónicos");

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(999.99);
        producto.setCategoria(categoria);
    }

    @Test
    void getAllProductos_ShouldReturnAllProductos() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> result = productoService.getAllProductos();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
        verify(productoRepository).findAll();
    }

    @Test
    void getProductoById_WhenProductoExists_ShouldReturnProducto() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        Producto result = productoService.getProductoById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getNombre());
        verify(productoRepository).findById(1L);
    }

    @Test
    void getProductoById_WhenProductoNotExists_ShouldThrowException() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.getProductoById(1L);
        });
        verify(productoRepository).findById(1L);
    }

    @Test
    void createProducto_WithValidData_ShouldReturnSavedProducto() {
        // Arrange
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // Act
        Producto result = productoService.createProducto(producto);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getNombre());
        verify(categoriaRepository).findById(1L);
        verify(productoRepository).save(producto);
    }

    @Test
    void createProducto_WithInvalidCategoria_ShouldThrowException() {
        // Arrange
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.createProducto(producto);
        });
        verify(categoriaRepository).findById(1L);
        verify(productoRepository, never()).save(any());
    }

    @Test
    void deleteProducto_WhenProductoExists_ShouldDeleteProducto() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        doNothing().when(productoRepository).delete(producto);

        // Act
        productoService.deleteProducto(1L);

        // Assert
        verify(productoRepository).findById(1L);
        verify(productoRepository).delete(producto);
    }
} 