package com.gamezone.E_commerce.controllers;
import com.gamezone.E_commerce.exceptions.ProductAlreadyExistsException;
import com.gamezone.E_commerce.exceptions.ProductOutOfStockException;
import com.gamezone.E_commerce.exceptions.ProductNotExistsException;
import com.gamezone.E_commerce.exceptions.CategoryNotExistsException;
import com.gamezone.E_commerce.Models.Producto;
import com.gamezone.E_commerce.repositories.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class ProductoController {
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/agregarProducto")
    Producto newProducto(@RequestBody Producto producto) throws ParseException{
        List<Producto> newProducto = productoRepository.findAll();
        for(Producto product:newProducto){
            if(product.getProducto().toLowerCase(Locale.ROOT).equals(producto.getProducto().toLowerCase(Locale.ROOT))){
                throw new ProductAlreadyExistsException("Ya existe un producto con el nombre " + producto.getProducto());
            }
        }
        if(producto.getStock() <= 0) {
            throw new ProductOutOfStockException("La cantidad disponible debe ser mayor a 0");
        }
        producto.setCantidadCarrito(0);
        return productoRepository.save(producto);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/verProductos")
    List <Producto> getProductList(){
        List<Producto> ProductList = productoRepository.findAll();
        return ProductList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("verProducto/{producto}")
    Producto getProducto(@PathVariable String producto) throws ParseException{
        List<Producto> ProductList = productoRepository.findAll();
        Producto getproducto = null;
        for(Producto product:ProductList){
            if(product.getProducto().toLowerCase(Locale.ROOT).equals(producto.toLowerCase(Locale.ROOT))){
                getproducto = product;
                break;
            }
        }
        if (getproducto == null){
            throw new ProductNotExistsException("El producto no existe");
        }
        return getproducto;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("verProductos/{categoria}")
    List<Producto> getProductoByCategoria(@PathVariable String categoria) throws ParseException{
        List<Producto> productoList = productoRepository.findAll();
        List<Producto> productsCategory = new ArrayList();
        for (Producto product:productoList){
            if(product.getCategoria().toLowerCase(Locale.ROOT).equals(categoria.toLowerCase(Locale.ROOT))){
                productsCategory.add(product);
            }
        }
        if(productsCategory.size() == 0){
            throw new CategoryNotExistsException("No existe la categoria");
        }
        return productsCategory;
    }

    @CrossOrigin(origins = "*")
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
        updateProducto.setCantidadCarrito(0);

        return productoRepository.save(updateProducto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("eliminarProducto/{producto}")
    String deleteProduto(@PathVariable String producto) throws ParseException{
        List<Producto> ProductList = productoRepository.findAll();
        Producto productoDelete = null;
        for(Producto product:ProductList){
            if(product.getProducto().toLowerCase(Locale.ROOT).equals(producto.toLowerCase(Locale.ROOT))){
                productoDelete = product;
                break;
            }
        }
        if (productoDelete == null){
            throw new ProductNotExistsException("El producto no existe");
        }
        productoRepository.delete(productoDelete);
        return "Se ha eliminado el producto";
    }
}
