# Sistema de Gestión de Biblioteca Universitaria

## Descripción
Sistema desarrollado en Java para administrar los materiales disponibles para préstamo y consulta en una biblioteca universitaria.

## Características Principales

✅ **Orientado a Objetos** - Implementación completa de OOP sin frameworks  
✅ **Tres Tipos de Materiales** - Libros, Revistas y Tesis  
✅ **Gestión de Préstamos** - Sistema de préstamos y devoluciones  
✅ **Búsqueda Avanzada** - Búsqueda por código o título  
✅ **Validación Robusta** - Jakarta Validation y Hibernate Validator  
✅ **Anotación Personalizada** - Validación de años de publicación  

## Conceptos de OOP Implementados

### 1. Encapsulamiento
- Todos los atributos son privados
- Acceso mediante getters y setters

### 2. Herencia
```
Material (clase abstracta)
├── Libro
├── Revista
└── Tesis
```

### 3. Clase Abstracta
- `Material` es la clase padre abstracta
- Implementa el método abstracto `mostrarInformacion()`

### 4. Sobreescritura (Override)
Cada clase hija implementa su propia versión de `mostrarInformacion()`

### 5. Interfaz
- `Prestable` - Define operaciones de préstamo y devolución
- Métodos: `prestar()`, `devolver()`, `estaPrestado()`

### 6. Sobrecarga (Overload)
```java
// Búsqueda por código
public Material buscarMaterial(int codigo)

// Búsqueda por título
public Material buscarMaterial(String titulo)
```

### 7. Polimorfismo
Utiliza `List<Material>` para manejar objetos de diferentes tipos

### 8. Validaciones con Anotaciones
- `@NotBlank` - Campos obligatorios
- `@Positive` - Valores positivos
- `@AñoValido` - Validación personalizada

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior
- Jakarta Validation
- Hibernate Validator

## Instalación

1. **Clona el repositorio**
   ```bash
   git clone https://github.com/RMSE233/Practica.git
   cd Practica
   ```

2. **Compila el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecuta el programa**
   ```bash
   mvn exec:java -Dexec.mainClass="com.biblioteca.ui.SistemaGestionBiblioteca"
   ```

## Uso del Sistema

El sistema presenta un menú interactivo con las siguientes opciones:

```
1. Registrar nuevo material
2. Ver todos los materiales
3. Ver materiales disponibles
4. Ver materiales prestados
5. Buscar material por código
6. Buscar material por título
7. Prestar material
8. Devolver material
9. Salir
```

### Ejemplo de Uso

```
1. Registrar un libro:
   - Tipo: Libro
   - Código: 1
   - Título: Don Quijote
   - Año: 1605
   - Autor: Miguel de Cervantes

2. Prestar el libro:
   - Opción: 7
   - Código: 1

3. Devolver el libro:
   - Opción: 8
   - Código: 1
```

## Estructura del Proyecto

```
Practica/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── biblioteca/
                    ├── ui/
                    │   └── SistemaGestionBiblioteca.java
                    ├── models/
                    │   ├── Material.java (abstracta)
                    │   ├── Libro.java
                    │   ├── Revista.java
                    │   ├── Tesis.java
                    │   └── Prestable.java (interfaz)
                    ├── validations/
                    │   ├── AñoValido.java
                    │   └── AñoValidoValidator.java
                    └── services/
                        └── BibliotecaService.java
```

## Validaciones Implementadas

### Campos Obligatorios
- Título: No puede estar vacío
- Autor (Libro): No puede estar vacío
- Universidad (Tesis): No puede estar vacía

### Valores Positivos
- Código: Debe ser mayor a 0
- Número de Edición (Revista): Debe ser mayor a 0

### Año de Publicación (Validación Personalizada)
- Mínimo: 1900
- Máximo: Año actual (2026)

## Ejemplo de Validación

```java
// Esta validación fallará
Libro libro = new Libro(1, "Título", 1800, "Autor");
biblioteca.registrarMaterial(libro);
// ✗ Errores de validación: El año de publicación debe estar entre 1900 y el año actual
```

## Autor
Proyecto desarrollado como práctica de Programación Orientada a Objetos en Java.

## Licencia
MIT

---

**Nota:** Este proyecto fue desarrollado sin utilizar frameworks como Spring Boot, enfocándose en los principios fundamentales de OOP.
