package com.gamezone.E_commerce.Models;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.ArrayList;

@ApiModel("Modelo Carrito")
public class Carrito {
    @Id
    private String id;
    private int cantidad_productos;
    private List<Producto> productos = new ArrayList();
    private int precioTotal;

    public Carrito(String id, int cantidad_productos, List productos, int precioTotal) {
        this.id = id;
        this.cantidad_productos = cantidad_productos;
        this.productos = productos;
        this.precioTotal = precioTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
}
