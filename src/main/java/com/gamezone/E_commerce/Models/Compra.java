package com.gamezone.E_commerce.Models;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import java.util.Date;

@ApiModel("Modelo Compra")
public class Compra {
    @Id
    private String id;
    private String idCliente;
    private Date fecha;

    public Compra(String id, String idCliente, Date fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
