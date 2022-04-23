package com.gamezone.E_commerce.controllers;
import com.gamezone.E_commerce.Models.Compra;
import com.gamezone.E_commerce.Models.Cliente;
import com.gamezone.E_commerce.exceptions.CompraAlreadyExistsException;
import com.gamezone.E_commerce.exceptions.ClientNotExistsException;
import com.gamezone.E_commerce.exceptions.CompraNotExistsException;
import com.gamezone.E_commerce.repositories.CompraRepository;
import com.gamezone.E_commerce.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CompraController {
    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;

    public CompraController(CompraRepository compraRepository, ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/agregarCompra/{idCompra}/{idCliente}")
    Compra newCompra(@PathVariable String idCompra, @PathVariable String idCliente) throws ParseException{
        Compra newCompr = compraRepository.findById(idCompra).orElse(null);
        if(newCompr != null){
            throw new CompraAlreadyExistsException("La compra ya existe");
        }
        Cliente client = clienteRepository.findById(idCliente).orElse(null);
        if(client == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(new Date());
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(strDate);

        Compra compra = new Compra(idCompra, idCliente, date1);

        return compraRepository.save(compra);
    }

    @GetMapping("/verCompras")
    List<Compra> getCompras() throws ParseException{
        List<Compra> ListCompras = compraRepository.findAll();
        return ListCompras;
    }

    @GetMapping("/verComprasXFecha/{fecha}")
    List<Compra> getComprasFecha(@PathVariable String fecha) throws ParseException{
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        List<Compra> comprasList = compraRepository.findByFecha(date1);
        if(comprasList.size() == 0){
            throw new CompraNotExistsException("No hay compras en la fecha " + fecha);
        }
        return comprasList;
    }

    @GetMapping("verCompraXCliente/{idCliente}")
    Compra getCompraCliente(@PathVariable String idCliente) throws ParseException{
        Cliente client = clienteRepository.findById(idCliente).orElse(null);
        if(client == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        List<Compra> compras = compraRepository.findAll();
        Compra getCompra = null;
        for (Compra compr : compras) {
            if (compr.getIdCliente().equals(idCliente)) {
                getCompra = compr;
                break;
            }
        }
        if (getCompra == null){
            throw new ClientNotExistsException("No hay ninguna compra hecha por el cliente " + idCliente);
        }
        return getCompra;
    }

    @DeleteMapping("/borrarCompra/{id}")
    String deleteCompra(@PathVariable String id) throws ParseException{
        Compra deleteCompr = compraRepository.findById(id).orElse(null);
        if(deleteCompr == null){
            throw new CompraNotExistsException("La compra no existe");
        }
        compraRepository.delete(deleteCompr);
        return "Se ha eliminado la compra";
    }
}
