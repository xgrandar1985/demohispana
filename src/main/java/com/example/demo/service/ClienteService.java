package com.example.demo.service;

import com.example.demo.dto.ClienteConVehiculosYModeloYMarcaDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveOrUpdateCliente(Cliente cliente) {
        Optional<Cliente> existingCliente = clienteRepository.findByIdentificacion(cliente.getIdentificacion());
        return existingCliente.orElseGet(() -> clienteRepository.save(cliente));
    }

    public Optional<ClienteConVehiculosYModeloYMarcaDTO> findClienteConVehiculosYModeloYMarcaDTO(String identificacion) {
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