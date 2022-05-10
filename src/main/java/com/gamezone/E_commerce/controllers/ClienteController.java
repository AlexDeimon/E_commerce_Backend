package com.gamezone.E_commerce.controllers;
import com.gamezone.E_commerce.Models.Cliente;
import com.gamezone.E_commerce.Models.Carrito;
import com.gamezone.E_commerce.exceptions.ClientAlreadyExistsException;
import com.gamezone.E_commerce.exceptions.CarritoNotExistsException;
import com.gamezone.E_commerce.exceptions.ClientNotExistsException;
import com.gamezone.E_commerce.repositories.ClienteRepository;
import com.gamezone.E_commerce.repositories.CarritoRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class ClienteController {
    private final ClienteRepository clienteRepository;
    private final CarritoRepository carritoRepository;

    public ClienteController(ClienteRepository clienteRepository, CarritoRepository carritoRepository) {
        this.clienteRepository = clienteRepository;
        this.carritoRepository = carritoRepository;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/nuevoCliente/{idCliente}/{idCarrito}")
    Cliente newCliente(@PathVariable String idCliente, @PathVariable String idCarrito, @RequestBody Cliente cliente) throws ParseException{
        Cliente newclient = clienteRepository.findById(idCliente).orElse(null);
        if(newclient != null){
            throw new ClientAlreadyExistsException("El cliente ya existe");
        }
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        if(carrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        cliente.setIdCliente(idCliente);
        cliente.setIdCarrito(idCarrito);

        return clienteRepository.save(cliente);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/verClientes")
    List<Cliente> getClientes() throws ParseException{
        List<Cliente> ClientList = clienteRepository.findAll();
        return ClientList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("verCliente/{idcliente}")
    Cliente getCliente(@PathVariable String idcliente) throws ParseException {
        Cliente getclient = clienteRepository.findById(idcliente).orElse(null);
        if(getclient == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        return getclient;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("verClienteXCarrito/{idcarrito}")
    Cliente getClienteXCarrito(@PathVariable String idcarrito) throws ParseException{
        Carrito carrito = carritoRepository.findById(idcarrito).orElse(null);
        if(carrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        List<Cliente> ClientList = clienteRepository.findAll();
        Cliente getcliente = null;
        for (Cliente client : ClientList) {
            if (client.getIdCarrito().equals(idcarrito)) {
                getcliente = client;
                break;
            }
        }
        if (getcliente == null){
            throw new CarritoNotExistsException("No hay ningun cliente con el carrito " + idcarrito);
        }
        return getcliente;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("verCarritoXCliente/{id}")
    Carrito getCarritoXCliente(@PathVariable String id) throws ParseException{
        Cliente getclient = clienteRepository.findById(id).orElse(null);
        if(getclient == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        Carrito getcarrito = carritoRepository.findById(getclient.getIdCarrito()).orElse(null);
        if(getcarrito == null){
            throw new CarritoNotExistsException("El carrito no existe");
        }
        return getcarrito;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/actualizarCliente/{id}")
    Cliente putCliente(@PathVariable String id, @RequestBody Cliente cliente) throws ParseException{
        Cliente putclient = clienteRepository.findById(id).orElse(null);
        if(putclient == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        putclient.setCorreo(cliente.getCorreo());
        putclient.setNombre(cliente.getNombre());
        putclient.setApellidos(cliente.getApellidos());
        putclient.setDireccion(cliente.getDireccion());
        putclient.setTelefono(cliente.getTelefono());

        return clienteRepository.save(putclient);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/eliminarCliente/{id}")
    String deleteCliente(@PathVariable String id) throws ParseException{
        Cliente deletecliente = clienteRepository.findById(id).orElse(null);
        if(deletecliente == null){
            throw new ClientNotExistsException("El cliente no existe");
        }
        clienteRepository.delete(deletecliente);
        return "Se ha eliminado el cliente";
    }

}
