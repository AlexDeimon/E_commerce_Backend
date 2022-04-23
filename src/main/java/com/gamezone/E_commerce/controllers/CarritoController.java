package com.gamezone.E_commerce.controllers;

import com.gamezone.E_commerce.Models.Carrito;
import com.gamezone.E_commerce.Models.Producto;
import com.gamezone.E_commerce.exceptions.CarritoNotExistsException;
import com.gamezone.E_commerce.exceptions.ProductNotExistsException;
import com.gamezone.E_commerce.exceptions.ProductOutOfStockException;
import com.gamezone.E_commerce.repositories.CarritoRepository;
import com.gamezone.E_commerce.repositories.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

@RestController
public class CarritoController {
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private List<Producto> productos = new ArrayList();

    public CarritoController(CarritoRepository carritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    @PostMapping("/crearCarrito/{id}")
    Carrito newCarrito(@PathVariable String id) throws ParseException{
        if(!productos.isEmpty()){
            productos.clear();
        }
        Carrito carrito = new Carrito(id, 0, productos, 0);
        return carritoRepository.save(carrito);
    }

    @PutMapping("/añadirProducto/{id}/{producto}/{cantidad}")
    Carrito newProducto(@PathVariable String id, @PathVariable String producto, @PathVariable int cantidad) throws ParseException{
        if(!productos.isEmpty()){
            productos.clear();
        }
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        if(carrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        List<Producto> ProductList = productoRepository.findAll();
        Producto addProducto = null;
        for(Producto product:ProductList){
            if(product.getProducto().toLowerCase(Locale.ROOT).equals(producto.toLowerCase(Locale.ROOT))){
                addProducto = product;
                product.setCantidadCarrito(0);
                productoRepository.save(product);
                break;
            }
        }
        if (addProducto == null){
            throw new ProductNotExistsException("El producto no existe");
        }
        if (cantidad <= 0){
            throw new ProductOutOfStockException("La cantidad del producto debe ser mayor a 0");
        }
        if(addProducto.getStock() < cantidad){
            throw new ProductOutOfStockException("No hay Stock suficiente del producto que deseas comprar");
        }
        int cantidadProductos = 0;
        int precioCarrito = 0;
        addProducto.setCantidadCarrito(addProducto.getCantidadCarrito()+cantidad);
        addProducto.setStock(addProducto.getStock()-cantidad);
        productoRepository.save(addProducto);
        productos.add(addProducto);
        for(int i = 0; i < cantidad; i++) {
            cantidadProductos++;
            precioCarrito += addProducto.getPrecio();
        }
        List newcarrito = new ArrayList(carrito.getProductos());
        newcarrito.addAll(productos);
        carrito.setProductos(newcarrito);
        carrito.setCantidad_productos(carrito.getCantidad_productos()+cantidadProductos);
        carrito.setPrecioTotal(carrito.getPrecioTotal()+precioCarrito);
        return carritoRepository.save(carrito);
    }

    @PutMapping("/borrarProducto/{id}/{producto}")
    Carrito deleteProducto(@PathVariable String id, @PathVariable String producto) throws ParseException{
        if(!productos.isEmpty()){
            productos.clear();
        }
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        List<Producto> ProductList = productoRepository.findAll();
        Producto removeProducto = null;
        for(Producto product:ProductList){
            if(product.getProducto().toLowerCase(Locale.ROOT).equals(producto.toLowerCase(Locale.ROOT))){
                removeProducto = product;
                break;
            }
        }
        if(carrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        if(removeProducto == null){
            throw new ProductNotExistsException("El producto no existe");
        }
        for (Producto p:carrito.getProductos()){
            if(p.getProducto().equals(removeProducto.getProducto())){
                removeProducto.setStock(removeProducto.getStock()+p.getCantidadCarrito());
                carrito.setPrecioTotal(carrito.getPrecioTotal()-(removeProducto.getPrecio()*p.getCantidadCarrito()));
                carrito.setCantidad_productos(carrito.getCantidad_productos()-p.getCantidadCarrito());
            }else{
                productos.add(p);
            }
        }
        carrito.setProductos(productos);
        productoRepository.save(removeProducto);
        return carritoRepository.save(carrito);
    }

    @GetMapping("/verCarrito/{id}")
    Carrito getCarrito(@PathVariable String id) throws ParseException{
        Carrito getcarrito = carritoRepository.findById(id).orElse(null);
        if(getcarrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        return getcarrito;
    }

    @GetMapping("/verCarritos")
    List<Carrito> getCarritos() throws ParseException{
        List<Carrito> carritoList = carritoRepository.findAll();
        return carritoList;
    }

    @DeleteMapping("/eliminarCarrito/{id}")
    String deleteCarrito(@PathVariable String id) throws ParseException{
        Carrito deletecarrito = carritoRepository.findById(id).orElse(null);
        if(deletecarrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        carritoRepository.delete(deletecarrito);
        return "Se ha eliminado el carrito";
    }
}
