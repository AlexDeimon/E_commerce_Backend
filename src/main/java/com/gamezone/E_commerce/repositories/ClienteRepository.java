package com.gamezone.E_commerce.repositories;
import com.gamezone.E_commerce.Models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
}
