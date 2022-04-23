
# E_commerce_Backend

Backend para la creación de una API-REST para un sistema de E_commerce de una tienda de video juegos ([Basado en anterior proyecto GameZone](https://github.com/AlexDeimon/GameZone_MS_Frontend)) desarrollado en Java Spring Boot usando una arquitectura MVC y una Base de Datos noSQL.

El objetivo de este código es exponer una API-REST para la creación y manejo de:

- Productos.
- Carrito de compras.
- Clientes.
- Compras. 

El código está realizado usando una arquitectura MVC, donde el Modelo y el Controlador son definidos de manera explícita, y de la construcción de la Vista se encarga Spring Boot. También cuenta con un manejo de Excepciones simple.
## Arquitectura ⚙️

**Modelos y repositorio**

El componente Modelo de la arquitectura, cuenta con dos elementos; el primero: los Modelos, en los cuales se definen las entidades que se utilizarán, y el segundo elemento: los Repositorios, son una interfaz de Java que le indica a Spring Boot y a MongoDB cómo realizar el mapeo de los modelos en la base de datos. Además, los repositorios permiten realizar consultas a la base de datos. Estos extienden de MongoRepository por lo cual se tienen métodos heredados de su padre.

**Controladores (con vistas implícitas)**

Cada uno de los modelos tambien tiene su respectivo controlador, el cual posee los métodos que cumplen dos funciones, la primera es indicarle a Spring Boot por medio de un decorador (como @GetMapping o @PostMapping) que cree una vista, la cual será un endpoint sobre el cual el usuario puede realizar una petición. La segunda función del método es implementar el controlador para esa vista, es decir, implementar cómo la vista responderá a una petición.

**Diagrama de clases**

![diagrama de clases] (https://github.com/AlexDeimon/E_commerce_Backend/blob/main/diagrama%20clases%20GZ2.png)

Como se puede ver, la única entidad independiente son los productos. 

Para que exista el carrito deben existir los productos; para que se pueda crear un cliente (a la hora de registrar sus datos), debe existir su debido carrito de compras; y a la hora de confirmar (crear) una compra, el cliente debió haber registrado sus datos.
## Entendiendo el código 💻

- **Inyección de dependencias:** dentro de los constructores de los controladores se reciben instancias de los repositorios, es natural pensar que en algún momento se debe instanciar dichos repositorios, pero no es así. Una de las tareas que Spring Boot hace es detectar que se trata de un controlador (gracias al decorador @RestController) y realizar la instanciación de los repositorios.

```bash
import com.gamezone.E_commerce.repositories.ProductoRepository;

@RestController
public class ProductoController {
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
...
}
```

- **Route:** dentro de los decoradores de los métodos (@GetMapping, @PostMapping, etc), se tiene como parámetro una ruta o path; ésta, junto al tipo de decorador, indica cómo el usuario puede acceder a la vista. Por ejemplo, para @PutMapping("actualizarProducto/{id}"), el usuario debe acceder a la URL http://localhost:8080/actualizarProducto/{id}, a través del método PUT.

- **Parámetros:** una vista puede recibir parámetros por parte del usuario final. En este caso, los tipos de parámetros que puede recibir la vista son Parámetros de Ruta y Parámetros del Body, los primeros como su nombre lo indica se envían a través de las rutas, en el decorador estos se definen usando corchetes {}; los segundos se envían a través del cuerpo de la petición.

**Ejemplo:**

En esta petición de tipo PUT se tienen parámetros de los 2 tipos @PathVariable y @RequestBody.

```bash
@PutMapping("actualizarProducto/{id}")
    Producto putProducto(@PathVariable String id, @RequestBody Producto productoupdate) throws ParseException{
        Producto updateProducto = productoRepository.findById(id).orElse(null);
        if(updateProducto == null) {
            throw new ProductNotExistsException("El producto no existe");
        }
        if(productoupdate.getStock() <= 0){
            throw new ProductOutOfStockException("La cantidad disponible debe ser mayor a 0");
        }
        updateProducto.setProducto(productoupdate.getProducto());
        updateProducto.setDescripcion(productoupdate.getDescripcion());
        updateProducto.setPrecio(productoupdate.getPrecio());
        updateProducto.setStock(productoupdate.getStock());
        updateProducto.setCategoria(productoupdate.getCategoria());

        return productoRepository.save(updateProducto);
    }
```

[!prueba en postman] (https://github.com/AlexDeimon/E_commerce_Backend/blob/main/prueba%20postman.png)
## Pruebas ✅

Para ejecutar el proyecto se usa el siguiente comando

```bash
  mvnw spring-boot:run
```

Unos segundos despues, mediante la ruta http://localhost:8080/ se pueden realizar las peticiones agregando el endpoint de la petición que se desee. Se debe tener en cuanta cuales peticiones requieren un Body y cuales no.


## API-REST desplegada 📦

El despliegue de la API se realizó en Heroku y se puede acceder a esta mediante la URL https://gamezone-e-commerce-backend.herokuapp.com/ 

Con esa URL se pueden realizar tambien pruebas a las peticiones de las 4 entidades.

## Herramientas utilizadas 🛠️

- IntelliJ IDEA - IDE utilizado para el desarrollo.
- Spring boot - Framework de Java utilizado.
- Maven - Herramienta para simplificar procesos de compilación y ejecución.
- Postman - Herramienta para realizar las pruebas de la API-REST.
- Docker - Herramienta para crear contenedor del proyecto haciendo más facil su despliegue y ejecución. 
- Heroku - Plataforma como servicio (PaaS) utilizada para desplegar la API-REST en la nube. 
