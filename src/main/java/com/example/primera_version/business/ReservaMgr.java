package com.example.primera_version.business;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.ReservaNoDisponible;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.ReservationRepository;
import com.example.primera_version.persistence.TuristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReservaMgr {

    @Autowired
    private ExperienceRepository  experienceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TuristRepository turistRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }

    private boolean puedoHacerReserva(Experiencia experiencia,Long cantidadDePersonas){
        boolean devolucion=true;


        if(cantidadDePersonas >experiencia.getCantidad()){
            devolucion = false;
        }

        return devolucion;
    }

    public void agregarReserva(String mail, String nombreExperiencia, String tipoDeDocumento,String numeroDeDocumento,Long numeroPersonas) throws InvalidUserInformation, ReservaNoDisponible {


        if ( chequearString(mail) || chequearString(nombreExperiencia) || chequearString(tipoDeDocumento) || chequearString(numeroDeDocumento) || numeroPersonas==null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidUserInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        Experiencia experiencia=experienceRepository.findOneByTituloExperiencia(nombreExperiencia);


        if(!puedoHacerReserva(experiencia,numeroPersonas)){
            throw new ReservaNoDisponible("ERROR!,No nos queda espacio, intente más tarde");
        }

        Turist turist= turistRepository.findOneByMail(mail);
//        turist.setTipoDocumento(); como seteo esto
        turist.setValorDocumento(numeroDeDocumento);
        Reserva reserva=new Reserva(); //Hay algunas cosas que no tienen sentido con la base de datos
        reserva.setTurista(turistRepository.findOneByMail(mail));
        reserva.setExperiencia(experiencia);
        reserva.setNumeroPersonas(numeroPersonas);
        reservationRepository.save(reserva);
    }

    public ArrayList<Reserva> encontrarTodas(){
        return (ArrayList<Reserva>) reservationRepository.findAll();
    }

    public ArrayList<Reserva> encontrarTodasContenidoTitulo(Long numero){
        ArrayList<Reserva> listaReservas = (ArrayList<Reserva>) reservationRepository.findAllByNumeroReservaContaining(numero);
        return listaReservas;
    }


}
