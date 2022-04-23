package com.gamezone.E_commerce.Models;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;

@ApiModel("Modelo Cliente")
public class Cliente {
    @Id
    private String idCliente;
    private String idCarrito;
    private String correo;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;

    public Cliente(String idCliente, String idCarrito, String correo, String nombre, String apellidos, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.idCarrito = idCarrito;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
