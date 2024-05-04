package com.example.ventaPasajesBuses2.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buses_viajes_info")
// Esta entidad contendrá la información de los viajes
public class BusesViajesInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String identificador_bus;

    @Column
    private String marca;

    @Column
    private int asientos_totales;

    @Column
    private LocalDate fecha_viaje;

    @Column
    private String ciudad_inicio;

    @Column
    private String ciudad_destino;

    @Column
    private String hora;

    @Column
    private String terminal_inicio;

    @Column
    private String terminales_destino;

    // Getter para el campo id
    public Long getId() {
        return id;
    }

    // Getter para el campo identificador_bus
    public String getIdentificador_bus() {
        return identificador_bus;
    }

    // Setter para el campo identificador_bus
    public void setIdentificador_bus(String identificador_bus) {
        this.identificador_bus = identificador_bus;
    }

    // Getter para el campo marca
    public String getMarca() {
        return marca;
    }

    // Setter para el campo marca
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Getter para el campo asientos_totales
    public int getAsientos_totales() {
        return asientos_totales;
    }

    // Setter para el campo asientos_totales
    public void setAsientos_totales(int asientos_totales) {
        this.asientos_totales = asientos_totales;
    }

    // Getter para el campo fecha_viaje
    public LocalDate getFecha_viaje() {
        return fecha_viaje;
    }

    // Setter para el campo fecha_viaje
    public void setFecha_viaje(LocalDate fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    // Getter para el campo ciudad_inicio
    public String getCiudad_inicio() {
        return ciudad_inicio;
    }

    // Setter para el campo ciudad_inicio
    public void setCiudad_inicio(String ciudad_inicio) {
        this.ciudad_inicio = ciudad_inicio;
    }

    // Getter para el campo ciudad_destino
    public String getCiudad_destino() {
        return ciudad_destino;
    }

    // Setter para el campo ciudad_destino
    public void setCiudad_destino(String ciudad_destino) {
        this.ciudad_destino = ciudad_destino;
    }

    // Getter para el campo hora
    public String getHora() {
        return hora;
    }

    // Setter para el campo hora
    public void setHora(String hora) {
        this.hora = hora;
    }

    // Getter para el campo terminal_inicio
    public String getTerminal_inicio() {
        return terminal_inicio;
    }

    // Setter para el campo terminal_inicio
    public void setTerminal_inicio(String terminal_inicio) {
        this.terminal_inicio = terminal_inicio;
    }

    // Getter para el campo terminales_destino
    public String getTerminales_destino() {
        return terminales_destino;
    }

    // Setter para el campo terminales_destino
    public void setTerminales_destino(String terminales_destino) {
        this.terminales_destino = terminales_destino;
    }
}
