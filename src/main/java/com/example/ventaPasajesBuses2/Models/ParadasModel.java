package com.example.ventaPasajesBuses2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ventaPasajesBuses2.Models.BusesViajesInfoModel;

@Entity
@Table(name = "paradas")
public class ParadasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "identificador_bus")
    private BusesViajesInfoModel identificador_bus_id;

    @Column
    private String parada;

    // Getter para el campo id
    public Long getId() {
        return id;
    }

    // Getter para el campo identificador_bus_id
    public BusesViajesInfoModel getIdentificador_bus_id() {
        return identificador_bus_id;
    }

    // Setter para el campo identificador_bus_id
    public void setIdentificador_bus_id(BusesViajesInfoModel identificador_bus_id) {
        this.identificador_bus_id = identificador_bus_id;
    }

    // Getter para el campo parada
    public String getParada() {
        return parada;
    }

    // Setter para el campo parada
    public void setParada(String parada) {
        this.parada = parada;
    }
}
