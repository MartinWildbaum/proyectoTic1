package com.example.primera_version.business;

import com.example.primera_version.business.entities.*;
import com.example.primera_version.business.exceptions.ExperienceAlreadyExists;
import com.example.primera_version.business.exceptions.InavlidClasificacionInformation;
import com.example.primera_version.business.exceptions.InvalidExperienceInformation;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.ImageRepository;
import com.example.primera_version.persistence.ReservationRepository;
import com.example.primera_version.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;


@Service
public class CriticaMgr {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addCritica(String comentario, Reserva reserva) throws InavlidClasificacionInformation {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if (chequearString(comentario) || reserva ==null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InavlidClasificacionInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Suponemos que el cliente pretende este control en principio
        if (reservationRepository.findOneByNumeroReserva(reserva.getNumeroReserva()) == null) {

            throw new InavlidClasificacionInformation("La reserva con la que se quiere realizar una critica no existe");
        }


        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Critica criticaAgregar = new Critica();

        criticaAgregar.setComentario(comentario);
        criticaAgregar.setReserva(reserva);

        // Guardo el turista que me cree anteriormente en mi base de datos

        reviewRepository.save(criticaAgregar);


    }


}
