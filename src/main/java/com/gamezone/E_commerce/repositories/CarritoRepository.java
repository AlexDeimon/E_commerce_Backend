package com.gamezone.E_commerce.repositories;
import com.gamezone.E_commerce.Models.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CarritoRepository extends MongoRepository<Carrito, String>{

}
