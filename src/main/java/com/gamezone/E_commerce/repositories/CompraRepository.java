package com.gamezone.E_commerce.repositories;
import com.gamezone.E_commerce.Models.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Date;

public interface CompraRepository extends MongoRepository<Compra, String>{
    List<Compra> findByFecha(Date fecha);
}
