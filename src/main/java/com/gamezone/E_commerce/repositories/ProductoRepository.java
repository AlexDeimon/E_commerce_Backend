package com.gamezone.E_commerce.repositories;
import com.gamezone.E_commerce.Models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductoRepository extends MongoRepository<Producto, String>{
    List<Producto> findAll();
}
