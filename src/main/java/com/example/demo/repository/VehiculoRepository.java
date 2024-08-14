package com.example.demo.repository;
import com.example.demo.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByChasis(String chasis);
    Optional<Vehiculo> findByPlaca(String placa);
}
