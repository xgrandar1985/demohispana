package com.example.demo.service;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.MarcaRepository;
import com.example.demo.repository.ModeloRepository;
import com.example.demo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {

        Optional<Vehiculo> existingVehiculoByChasis = vehiculoRepository.findByChasis(vehiculo.getChasis());
        if (existingVehiculoByChasis.isPresent()) {
            throw new RuntimeException("El chasis ya se encuentra registrado.");
        }

        Optional<Vehiculo> existingVehiculoByPlaca = vehiculoRepository.findByPlaca(vehiculo.getPlaca());
        if (existingVehiculoByPlaca.isPresent()) {
            throw new RuntimeException("La placa ya se encuentra registrada.");
        }

        if (!marcaRepository.existsById(vehiculo.getMarca().getId())) {
            throw new RuntimeException("La marca no existe.");
        }

        if (!modeloRepository.existsById(vehiculo.getModelo().getId())) {
            throw new RuntimeException("El modelo no existe.");
        }

        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> findByChasis(String chasis) {
        return vehiculoRepository.findByChasis(chasis);
    }
}
