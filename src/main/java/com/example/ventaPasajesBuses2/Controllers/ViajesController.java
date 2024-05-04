package com.example.ventaPasajesBuses2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ventaPasajesBuses2.Models.BusesViajesInfoModel;
import com.example.ventaPasajesBuses2.Services.ViajesServices;
import com.example.ventaPasajesBuses2.dto.DataTripsSearchDto;

public class ViajesController {
    private final ViajesServices viajesServices;

    @Autowired
    public ViajesController(ViajesServices viajesServices) {
        this.viajesServices = viajesServices;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/pasajesDisponibles")
    public ResponseEntity<BusesViajesInfoModel> searchTrips(@Valid @RequestBody DataTripsSearchDto dataTripsSearchDto) {
        System.out.println("llego el objeto al controlador");
        BusesViajesInfoModel sendToServices = viajesServices.searchTripsServices(dataTripsSearchDto);
        // inicializamos una instancia de BusesViajesInfoModel para acceder a sus
        // getters
        BusesViajesInfoModel busesViajesInfoModel = new BusesViajesInfoModel();

        if (busesViajesInfoModel.getCiudad_inicio() != null && busesViajesInfoModel.getCiudad_destino() != null
                && busesViajesInfoModel.getFecha_viaje() != null) {
            return ResponseEntity.ok().body(sendToServices); // retornamos respuesta exitosa (200)

        } else {
            return ResponseEntity.noContent().build(); // retornamos respuesta exitosa pero vacia, (204)
        }
    }
}
