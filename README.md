# Sistema de Productos - Lab13

Sistema de gestión de productos y categorías desarrollado con Spring Boot y Thymeleaf.

## Características

- ✅ Gestión de productos (CRUD completo)
- ✅ Gestión de categorías (CRUD completo)
- ✅ Interfaz web con Bootstrap y Thymeleaf
- ✅ Base de datos H2 (en memoria)
- ✅ Validación de datos
- ✅ Manejo de errores
- ✅ Datos de ejemplo precargados
- ✅ Diseño responsive

## Tecnologías Utilizadas

- **Backend**: Spring Boot 3.5.0
- **Base de Datos**: H2 (en memoria)
- **Frontend**: Thymeleaf + Bootstrap 5
- **Validación**: Bean Validation
- **Build Tool**: Maven

## Estructura del Proyecto

```
src/main/java/com/tecsup/lab13/
├── controller/
│   ├── HomeController.java
│   ├── ProductoController.java
│   └── CategoriaController.java
├── model/
│   ├── Producto.java
│   └── Categoria.java
├── repository/
│   ├── ProductoRepository.java
│   └── CategoriaRepository.java
├── service/
│   ├── ProductoService.java
│   └── CategoriaService.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
└── Lab13Application.java
```

## Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd Lab13
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - URL principal: http://localhost:8080
   - Consola H2: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (dejar vacío)

## Funcionalidades

### Gestión de Productos

- **Listar productos**: Ver todos los productos con información detallada
- **Crear producto**: Agregar nuevos productos con validación
- **Editar producto**: Modificar información de productos existentes
- **Eliminar producto**: Remover productos del sistema
- **Ver detalles**: Información completa de cada producto

### Gestión de Categorías

- **Listar categorías**: Ver todas las categorías disponibles
- **Crear categoría**: Agregar nuevas categorías
- **Editar categoría**: Modificar nombres de categorías
- **Eliminar categoría**: Remover categorías del sistema

### Datos de Ejemplo

Al iniciar la aplicación, se cargan automáticamente:

**Categorías:**
- Electrónicos
- Ropa
- Hogar
- Deportes

**Productos:**
- Laptop HP Pavilion ($899.99)
- Camiseta Nike ($29.99)
- Sofá 3 Plazas ($599.99)
- Balón de Fútbol ($49.99)
- Smartphone Samsung ($699.99)

## Configuración

### Base de Datos

La aplicación utiliza H2 en memoria con la siguiente configuración:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
```

### Puertos

- **Aplicación**: 8080
- **Consola H2**: 8080/h2-console

## Despliegue

### Render

Para desplegar en Render:

1. Crear una nueva aplicación web en Render
2. Conectar el repositorio de GitHub
3. Configurar:
   - **Build Command**: `mvn clean package`
   - **Start Command**: `java -jar target/Lab13-0.0.1-SNAPSHOT.jar`
   - **Environment**: Java 17

### Variables de Entorno

No se requieren variables de entorno adicionales ya que la aplicación utiliza H2 en memoria.

## API Endpoints

Aunque la aplicación está diseñada para usar templates, también expone endpoints REST:

### Productos
- `GET /productos` - Lista de productos
- `GET /productos/nuevo` - Formulario nuevo producto
- `POST /productos` - Crear producto
- `GET /productos/{id}` - Ver producto
- `GET /productos/{id}/editar` - Formulario editar
- `POST /productos/{id}` - Actualizar producto
- `GET /productos/{id}/eliminar` - Eliminar producto

### Categorías
- `GET /categorias` - Lista de categorías
- `GET /categorias/nuevo` - Formulario nueva categoría
- `POST /categorias` - Crear categoría
- `GET /categorias/{id}` - Ver categoría
- `GET /categorias/{id}/editar` - Formulario editar
- `POST /categorias/{id}` - Actualizar categoría
- `GET /categorias/{id}/eliminar` - Eliminar categoría

## Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto

- **Desarrollador**: [Tu Nombre]
- **Email**: [tu-email@ejemplo.com]
- **Proyecto**: [https://github.com/tu-usuario/Lab13](https://github.com/tu-usuario/Lab13) 