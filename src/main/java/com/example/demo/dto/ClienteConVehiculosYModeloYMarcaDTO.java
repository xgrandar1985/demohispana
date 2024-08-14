package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class ClienteConVehiculosYModeloYMarcaDTO {
    //private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private String telefono;
    private List<VehiculoConModeloConMarcaDTO> vehiculos;

    public ClienteConVehiculosYModeloYMarcaDTO(Long id, String identificacion, String nombres, String apellidos, String direccion, String correo, String telefono, List<VehiculoConModeloConMarcaDTO> vehiculos) {

        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.vehiculos = vehiculos;
    }

    @Data
    public static class VehiculoConModeloConMarcaDTO {
        private String placa;
        private String marca;
        private String modelo;
        private String chasis;



        public VehiculoConModeloConMarcaDTO(String placa, String marca, String modelo, String chasis ) {
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.chasis = chasis;


        }
    }
}