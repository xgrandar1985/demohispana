package com.example.demo.controller;

import com.example.demo.dto.ClienteConVehiculosYModeloYMarcaDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Vehiculo;
import com.example.demo.service.ClienteService;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/save")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {

        for (Vehiculo vehiculo : cliente.getVehiculos()) {
            vehiculo.setCliente(cliente);
            vehiculoService.saveVehiculo(vehiculo);
        }

        Cliente savedCliente = clienteService.saveOrUpdateCliente(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @GetMapping("/find")
    public ResponseEntity<ClienteConVehiculosYModeloYMarcaDTO> findClienteByIdentificacion(@RequestParam String identificacion) {

        Optional<ClienteConVehiculosYModeloYMarcaDTO> cliente = clienteService.findByIdentificacion(identificacion);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


}
