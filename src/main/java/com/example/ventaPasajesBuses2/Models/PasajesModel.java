package com.example.ventaPasajesBuses2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.example.ventaPasajesBuses2.Models.BusesViajesInfoModel;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Size;
import org.springframework.cglib.core.Local;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pasajes")
public class PasajesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "identificador_bus")
    private BusesViajesInfoModel identificador_bus_id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    @Size(min = 10, max = 10, message = "el rut debe tener 10 caracteres como minimo y maximo")
    private String rut;

    @Column
    private String email;

    @Column
    private String ciudad_inicio;

    @Column
    private String ciudad_destino;

    @Column
    private LocalDate fecha_inicio_viaje;

    @Column
    private LocalDate fecha_vuelta_viaje;

    @Column
    private String hora;

    @Column
    private int asiento;

    @Column
    private String tipo_de_asiento;

    @Column
    private String terminal_subida;

    @Column
    private String terminal_bajada;

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    // Getter y Setter para identificador_bus_id
    public BusesViajesInfoModel getIdentificador_bus_id() {
        return identificador_bus_id;
    }

    public void setIdentificador_bus_id(BusesViajesInfoModel identificador_bus_id) {
        this.identificador_bus_id = identificador_bus_id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para rut
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter y Setter para ciudad_inicio
    public String getCiudad_inicio() {
        return ciudad_inicio;
    }

    public void setCiudad_inicio(String ciudad_inicio) {
        this.ciudad_inicio = ciudad_inicio;
    }

    // Getter y Setter para ciudad_destino
    public String getCiudad_destino() {
        return ciudad_destino;
    }

    public void setCiudad_destino(String ciudad_destino) {
        this.ciudad_destino = ciudad_destino;
    }

    // Getter y Setter para fecha_inicio_viaje
    public LocalDate getFecha_inicio_viaje() {
        return fecha_inicio_viaje;
    }

    public void setFecha_inicio_viaje(LocalDate fecha_inicio_viaje) {
        this.fecha_inicio_viaje = fecha_inicio_viaje;
    }

    // Getter y Setter para fecha_vuelta_viaje
    public LocalDate getFecha_vuelta_viaje() {
        return fecha_vuelta_viaje;
    }

    public void setFecha_vuelta_viaje(LocalDate fecha_vuelta_viaje) {
        this.fecha_vuelta_viaje = fecha_vuelta_viaje;
    }

    // Getter y Setter para hora
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    // Getter y Setter para asiento
    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    // Getter y Setter para tipo_de_asiento
    public String getTipo_de_asiento() {
        return tipo_de_asiento;
    }

    public void setTipo_de_asiento(String tipo_de_asiento) {
        this.tipo_de_asiento = tipo_de_asiento;
    }

    // Getter y Setter para terminal_subida
    public String getTerminal_subida() {
        return terminal_subida;
    }

    public void setTerminal_subida(String terminal_subida) {
        this.terminal_subida = terminal_subida;
    }

    // Getter y Setter para terminal_bajada
    public String getTerminal_bajada() {
        return terminal_bajada;
    }

    public void setTerminal_bajada(String terminal_bajada) {
        this.terminal_bajada = terminal_bajada;
    }

}
