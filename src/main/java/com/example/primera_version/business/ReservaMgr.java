package com.example.primera_version.business;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.ReservaNoDisponible;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.ReservationRepository;
import com.example.primera_version.persistence.TuristRepository;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ReservaMgr {

    @Autowired
    private ExperienceRepository  experienceRepository;

    @Autowired
    private TuristMgr turistMgr;

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

    private boolean puedoHacerReserva(Experiencia experiencia,Long cantidadDePersonas, LocalDate fechaReserva){

        // Ni me fijo que haya lugar
        if(cantidadDePersonas > experiencia.getCantidad() && experiencia.getCantidad() != 0){
            return false;
        }
        // Aca controlo que no se haya pasado el aforo
        if(reservationRepository.countByExperienciaAndFecha(experiencia, fechaReserva) == null){
            return true;
        }else if ((reservationRepository.countByExperienciaAndFecha(experiencia, fechaReserva) + cantidadDePersonas > experiencia.getCantidad()) && experiencia.getCantidad() != 0){
            return false;
        }
        return true;
    }

    public void agregarReserva(String mail, String nombreExperiencia, String tipoDeDocumento, String numeroDeDocumento, Long numeroPersonas, LocalDate fechaReserva) throws InvalidUserInformation, ReservaNoDisponible {


        if ( chequearString(mail) || chequearString(nombreExperiencia) || chequearString(tipoDeDocumento) || chequearString(numeroDeDocumento) || numeroPersonas==null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidUserInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        Experiencia experiencia = experienceRepository.findOneByTituloExperiencia(nombreExperiencia);


        if(!puedoHacerReserva(experiencia, numeroPersonas, fechaReserva)){
            throw new ReservaNoDisponible("ERROR!,No nos queda espacio, intente realizar una reserva para otro dia. ");
        }

        Turist turist = turistRepository.findOneByMail(mail);
        turist.setTipoDocumento(tipoDeDocumento);
        turist.setValorDocumento(numeroDeDocumento);
        turistMgr.actualizarTurista(turist);

        Reserva reserva = new Reserva(); //Hay algunas cosas que no tienen sentido con la base de datos
        reserva.setTurista(turist);
        reserva.setExperiencia(experiencia);
        reserva.setNumeroPersonas(numeroPersonas);
        reserva.setFecha(fechaReserva);
        reservationRepository.save(reserva);
    }

    public ArrayList<Reserva> encontrarTodas(){
        return (ArrayList<Reserva>) reservationRepository.findAll();
    }

    public Reserva encontrarNumeroReserva(Long numero){
        Reserva listaReservas = reservationRepository.findOneByNumeroReserva(numero);
        return listaReservas;
    }

    public void actualizarReserva(Reserva reserva){
        reservationRepository.save(reserva);
    }
/*
    public ArrayList<Reserva> encontrarPorNumeroYExperiencia(Experiencia experiencia, Long numeroReserva){

        ArrayList<Reserva> devolucion = new ArrayList<>(10);
        List<Reserva> reservaPorNumero = reservationRepository.findAllByNumeroReservaContaining(numeroReserva);
        for (Reserva reserva: reservaPorNumero) {
            if(reserva.getExperiencia().equals(experiencia)){
                devolucion.add(reserva);
            }
        }
        return devolucion;
    }*/


    public ArrayList<Reserva> encontrarReservasPorFechaYExperiencia(Experiencia xp, LocalDate fechaReserva){
        ArrayList<Reserva> devolucion = new ArrayList<>(10);
        ArrayList<Reserva> reservasExperiencia = (ArrayList<Reserva>) reservationRepository.findByExperiencia(xp);
        for (Reserva reserva: reservasExperiencia) {
            if(reserva.getFecha().equals(fechaReserva)){
                devolucion.add(reserva);
            }
        }

        return devolucion;

    }

    public ArrayList<Reserva> enconotrarPorNumeroYTurista(Turist turist, Long numeroReserva){

        ArrayList<Reserva> devolucion = new ArrayList<>(10);


        List<Reserva> reservaPorNumero = reservationRepository.findAllByNumeroReservaContaining(numeroReserva);

        for (Reserva reserva: reservaPorNumero) {
            if (reserva.getTurista().equals(turist)){
                devolucion.add(reserva);
            }
        }
        return devolucion;
    }

    public ArrayList<Reserva> encontrarPorTituloExpYTurista(Turist turist, String texto){
        ArrayList<Reserva> devolucion = new ArrayList<>(10);

        Collection<Reserva> reservasTurista = turist.getReservas();
        for (Reserva reserva: reservasTurista) {
            if(reserva.getExperiencia().getTituloExperiencia().contains(texto)){
                devolucion.add(reserva);
            }
        }


        return devolucion;

    }

    public ObservableList<Reserva> encontrarPorExperiencia(Experiencia experiencia){
        ObservableList<Reserva> devolucion = (ObservableList<Reserva>) reservationRepository.findByExperiencia(experiencia);
        return devolucion;
    }
}
