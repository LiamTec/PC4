package com.tecsup.lab13;

import com.tecsup.lab13.model.Categoria;
import com.tecsup.lab13.model.Producto;
import com.tecsup.lab13.repository.CategoriaRepository;
import com.tecsup.lab13.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab13Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab13Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            @Autowired CategoriaRepository categoriaRepository,
            @Autowired ProductoRepository productoRepository) {
        return args -> {
            // Crear categorías por defecto si no existen
            if (categoriaRepository.count() == 0) {
                Categoria electronicos = new Categoria();
                electronicos.setNombre("Electrónicos");
                categoriaRepository.save(electronicos);

                Categoria ropa = new Categoria();
                ropa.setNombre("Ropa");
                categoriaRepository.save(ropa);

                Categoria hogar = new Categoria();
                hogar.setNombre("Hogar");
                categoriaRepository.save(hogar);

                Categoria deportes = new Categoria();
                deportes.setNombre("Deportes");
                categoriaRepository.save(deportes);

                System.out.println("Categorías por defecto creadas");

                // Crear productos de ejemplo
                Producto producto1 = new Producto();
                producto1.setNombre("Laptop HP Pavilion");
                producto1.setDescripcion("Laptop de 15 pulgadas con procesador Intel i5");
                producto1.setPrecio(899.99);
                producto1.setStock(10);
                producto1.setCategoria(electronicos);
                productoRepository.save(producto1);

                Producto producto2 = new Producto();
                producto2.setNombre("Camiseta Nike");
                producto2.setDescripcion("Camiseta deportiva de algodón");
                producto2.setPrecio(29.99);
                producto2.setStock(50);
                producto2.setCategoria(ropa);
                productoRepository.save(producto2);

                Producto producto3 = new Producto();
                producto3.setNombre("Sofá 3 Plazas");
                producto3.setDescripcion("Sofá moderno para sala de estar");
                producto3.setPrecio(599.99);
                producto3.setStock(5);
                producto3.setCategoria(hogar);
                productoRepository.save(producto3);

                Producto producto4 = new Producto();
                producto4.setNombre("Balón de Fútbol");
                producto4.setDescripcion("Balón oficial tamaño 5");
                producto4.setPrecio(49.99);
                producto4.setStock(20);
                producto4.setCategoria(deportes);
                productoRepository.save(producto4);

                Producto producto5 = new Producto();
                producto5.setNombre("Smartphone Samsung");
                producto5.setDescripcion("Teléfono inteligente con cámara de 48MP");
                producto5.setPrecio(699.99);
                producto5.setStock(15);
                producto5.setCategoria(electronicos);
                productoRepository.save(producto5);

                System.out.println("Productos de ejemplo creados");
            }
        };
    }
}
