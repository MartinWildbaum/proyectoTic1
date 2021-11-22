package com.example.primera_version.business;

import com.example.primera_version.business.entities.Denuncia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.exceptions.InavlidClasificacionInformation;
import com.example.primera_version.persistence.DenounceRepository;
import com.example.primera_version.persistence.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class DenunciaMgr {

    @Autowired
    private DenounceRepository denounceRepository;

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


    public void addDenuncia(String motivoDenuncia, String descripcion, Reserva reserva) throws InavlidClasificacionInformation {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if (chequearString(motivoDenuncia) || chequearString(descripcion) || reserva == null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InavlidClasificacionInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Suponemos que el cliente pretende este control en principio
        if (reservationRepository.findOneByNumeroReserva(reserva.getNumeroReserva()) == null) {

            throw new InavlidClasificacionInformation("La reserva con la que se quiere realizar una denuncia no existe");
        }
        if (reserva.getDenuncia() != null){
            throw new InavlidClasificacionInformation("Ya se realizo una denuncia de esta reserva.");
        }


        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Denuncia denunciaAgregar = new Denuncia();


        denunciaAgregar.setDescripcion(descripcion);
        denunciaAgregar.setFechaHora(LocalDateTime.now());
        denunciaAgregar.setReserva(reserva);
        denunciaAgregar.setMotivoDenuncia(motivoDenuncia);
        reserva.setDenuncia(denunciaAgregar);
        reservaMgr.actualizarReserva(reserva);

        // Guardo el turista que me cree anteriormente en mi base de datos

        denounceRepository.save(denunciaAgregar);


    }
}

