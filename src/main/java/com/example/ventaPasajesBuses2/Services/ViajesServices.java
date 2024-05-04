package com.example.ventaPasajesBuses2.Services;

import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.example.ventaPasajesBuses2.Models.BusesViajesInfoModel;
import com.example.ventaPasajesBuses2.dto.DataTripsSearchDto;

public class ViajesServices 
{
    
    @PersistenceContext
    EntityManager entityManager;

    // esta variable estatica tiene la funcion de crear una instancia de logger
    // private static final Logger logger =
    // LoggerFactory.getLogger(ViajesServices.class);

    public BusesViajesInfoModel searchTripsServices(DataTripsSearchDto dataTripsSearchDto) {

        // enviaremos a los setters de BusesViajesInfoModel los datos del dto
        BusesViajesInfoModel busesViajesInfoModel = new BusesViajesInfoModel();
        busesViajesInfoModel.setCiudad_inicio(dataTripsSearchDto.getCiudad_inicio());
        busesViajesInfoModel.setCiudad_destino(dataTripsSearchDto.getCiudad_de_destino());
        busesViajesInfoModel.setFecha_viaje(dataTripsSearchDto.getFecha_seleccionada());

        // La funcion de esta variable es convertir la fecha LocalDate a un formato Date
        // para la BD
        String fechaFinal = busesViajesInfoModel.getFecha_viaje().format(DateTimeFormatter.ISO_DATE);
        TypedQuery<BusesViajesInfoModel> query = null;

        try 
        {
            String jpqlQuery = "SELECT b FROM BusesViajesInfoModel WHERE b.ciudad_inicio = :ciudadInicioParameter and b.ciudad_destino = :ciudadDestinoParameter and b.fecha_viaje = :fechaViajeParameter";
            query = entityManager.createQuery(jpqlQuery, BusesViajesInfoModel.class);
            query.setParameter("ciudadInicioParameter", busesViajesInfoModel.getCiudad_inicio());
            query.setParameter("ciudadDestinoParameter", busesViajesInfoModel.getCiudad_destino());
            query.setParameter("fechaViajeParameter", fechaFinal);

            // logger.debug("Se realizo la consulta");
        } catch (PersistenceException e) {
            System.err.println("Ha ocurrido un error " + e.getMessage());
            // logger.error("Ha ocurrido un error con la base de datos", e);
            throw e;
        }

        return query.getSingleResult();

    }
}
