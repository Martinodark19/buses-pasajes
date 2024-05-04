package com.example.ventaPasajesBuses2.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;

public class DataTripsSearchDto 
{
     @NotBlank(message = "no se ha encontrado la ciudad de inicio, reintente nuevamente")
    private String ciudad_inicio;

    @NotBlank(message = "no se ha encontrado la ciudad de destino, reintente nuevamente")
    private String ciudad_de_destino;

    @NotBlank(message = "no se ha encontrado la fecha seleccionada para el trayecto deseado, reintente nuevamente")
    private LocalDate fecha_seleccionada;

    // Getter para ciudad_inicio
    public String getCiudad_inicio() {
        return ciudad_inicio;
    }

    // Setter para ciudad_inicio
    public void setCiudad_inicio(String ciudad_inicio) {
        this.ciudad_inicio = ciudad_inicio;
    }

    // Getter para ciudad_de_destino
    public String getCiudad_de_destino() {
        return ciudad_de_destino;
    }

    // Setter para ciudad_de_destino
    public void setCiudad_de_destino(String ciudad_de_destino) {
        this.ciudad_de_destino = ciudad_de_destino;
    }

    // Getter para fecha_seleccionada
    public LocalDate getFecha_seleccionada() {
        return fecha_seleccionada;
    }

    // Setter para fecha_seleccionada
    public void setFecha_seleccionada(LocalDate fecha_seleccionada) {
        this.fecha_seleccionada = fecha_seleccionada;
    }
}
