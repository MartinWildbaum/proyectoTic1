package com.example.primera_version.business;

import com.example.primera_version.business.entities.Critica;
import com.example.primera_version.business.entities.Puntuacion;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.exceptions.InavlidClasificacionInformation;
import com.example.primera_version.persistence.PunctuationRepository;
import com.example.primera_version.persistence.ReservationRepository;
import com.example.primera_version.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PuntajeMgr {

    @Autowired
    private PunctuationRepository punctuationRepository;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private ReservationRepository reservationRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addPuntaje(Long puntaje, Reserva reserva) throws InavlidClasificacionInformation {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if (puntaje < 0 || puntaje == null || reserva ==null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InavlidClasificacionInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }


        if (reservationRepository.findOneByNumeroReserva(reserva.getNumeroReserva()) == null) {

            throw new InavlidClasificacionInformation("La reserva con la que se quiere realizar una puntuacion no existe");
        }
        if(reserva.getPuntuacion() != null){
            throw new InavlidClasificacionInformation("Ya se ha realizado una puntuacion de la experiencia.");
        }

        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Puntuacion puntuacionAgregar = new Puntuacion();

        puntuacionAgregar.setPuntaje(puntaje);
        puntuacionAgregar.setReserva(reserva);
        puntuacionAgregar.setFechaHora(LocalDateTime.now());
        reserva.setPuntuacion(puntuacionAgregar);
        reservaMgr.actualizarReserva(reserva);

        // Guardo el turista que me cree anteriormente en mi base de datos

        punctuationRepository.save(puntuacionAgregar);


    }
}

