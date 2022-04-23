package com.gamezone.E_commerce.Models;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;

@ApiModel("Modelo Producto")
public class Producto {
    @Id
    private String id;
    private String producto;
    private String descripcion;
    private int precio;
    private int stock;
    private String categoria;
    private int cantidadCarrito;

    public Producto(String id, String producto, String descripcion, int precio, int stock, String categoria, int cantidadCarrito) {
        this.id = id;
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.cantidadCarrito = cantidadCarrito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadCarrito() {
        return cantidadCarrito;
    }

    public void setCantidadCarrito(int cantidadCarrito) {
        this.cantidadCarrito = cantidadCarrito;
    }
}
