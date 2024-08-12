package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveOrUpdateCliente(Cliente cliente) {
        Optional<Cliente> existingCliente = clienteRepository.findByIdentificacion(cliente.getIdentificacion());
        return existingCliente.orElseGet(() -> clienteRepository.save(cliente));
    }

    public Optional<Cliente> findByIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }
}