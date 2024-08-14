package com.example.demo.service;

import com.example.demo.dto.ClienteConVehiculosYModeloYMarcaDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Marca;
import com.example.demo.entity.Modelo;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdentificacion() {

        String identificacion = "097329745";
        Cliente cliente = new Cliente();
        cliente.setIdentificacion(identificacion);
        cliente.setNombres("Nany");
        cliente.setApellidos("Cardenas");
        cliente.setDireccion("56 y la q");
        cliente.setCorreo("nany@outlook.com");
        cliente.setTelefono("33333");

        Marca marca = new Marca();
        marca.setDescripcion("Toyota");

        Modelo modelo = new Modelo();
        modelo.setDescripcion("Corolla");
        modelo.setMarca(marca);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("XBZ456");
        vehiculo.setChasis("CH432");
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setCliente(cliente);

        cliente.setVehiculos(Arrays.asList(vehiculo));

        when(clienteRepository.findByIdentificacion(identificacion)).thenReturn(Optional.of(cliente));

        Optional<ClienteConVehiculosYModeloYMarcaDTO> clienteDTO = clienteService.findByIdentificacion(identificacion);

        assertTrue(clienteDTO.isPresent());
        assertEquals("Nany", clienteDTO.get().getNombres());
        assertEquals("Cardenas", clienteDTO.get().getApellidos());
        assertEquals("56 y la q", clienteDTO.get().getDireccion());
        assertEquals("nany@outlook.com", clienteDTO.get().getCorreo());
        assertEquals("33333", clienteDTO.get().getTelefono());



        assertEquals(1, clienteDTO.get().getVehiculos().size());
        ClienteConVehiculosYModeloYMarcaDTO.VehiculoConModeloConMarcaDTO vehiculoDTO = clienteDTO.get().getVehiculos().get(0);
        assertEquals("XBZ456", vehiculoDTO.getPlaca());
        assertEquals("CH432", vehiculoDTO.getChasis());
        assertEquals("Corolla", vehiculoDTO.getModelo());
        assertEquals("Toyota", vehiculoDTO.getMarca());

    }
}