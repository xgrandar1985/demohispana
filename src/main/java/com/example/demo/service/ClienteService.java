package com.example.demo.service;

import com.example.demo.dto.ClienteConVehiculosYModeloYMarcaDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Marca;
import com.example.demo.entity.Modelo;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.MarcaRepository;
import com.example.demo.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public Cliente saveOrUpdateCliente(Cliente cliente) {

        for (Vehiculo vehiculo : cliente.getVehiculos()) {

            vehiculo.setCliente(cliente);

            Marca marca = vehiculo.getMarca();

            if ((marca != null) && (marca.getId() != null)) {

                marca = marcaRepository.findById(marca.getId()).orElseThrow(() -> new RuntimeException("No se ha encontrado la marca"));

            } else {

                marca = marcaRepository.save(marca);
                
            }

            vehiculo.setMarca(marca);

            Modelo modelo = vehiculo.getModelo();
            if ((modelo != null) && (modelo.getId() != null)) {

                modelo = modeloRepository.findById(modelo.getId()).orElseThrow(() -> new RuntimeException("No se ha encontrado el modelo"));

            } else {

                modelo.setMarca(marca);
                modelo = modeloRepository.save(modelo);

            }

            vehiculo.setModelo(modelo);
        }

        return clienteRepository.save(cliente);

    }


    public Optional<ClienteConVehiculosYModeloYMarcaDTO> findByIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion)
                .map(cliente -> {
                    List<ClienteConVehiculosYModeloYMarcaDTO.VehiculoConModeloConMarcaDTO> vehiculos = cliente.getVehiculos().stream()
                            .map(vehiculo -> new ClienteConVehiculosYModeloYMarcaDTO.VehiculoConModeloConMarcaDTO(
                                    vehiculo.getPlaca(),
                                    vehiculo.getMarca().getDescripcion(),
                                    vehiculo.getModelo().getDescripcion(),
                                    vehiculo.getChasis()
                            ))
                            .collect(Collectors.toList());
                    return new ClienteConVehiculosYModeloYMarcaDTO(
                            cliente.getId(),
                            cliente.getIdentificacion(),
                            cliente.getNombres(),
                            cliente.getApellidos(),
                            cliente.getDireccion(),
                            cliente.getCorreo(),
                            cliente.getTelefono(),
                            vehiculos
                    );
                });
    }
}