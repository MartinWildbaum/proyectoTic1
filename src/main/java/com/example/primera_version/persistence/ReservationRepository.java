package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.entities.Turist;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reserva, Long> {

    /**
     * Retorna una reserva por numero de reserva si encuentra mas de una lanza una excepcion
     * @param numeroReserva
     * @return
     */
    Reserva findOneByNumeroReserva(Long numeroReserva);



    @Query("select sum(r.numeroPersonas) from Reserva r where r.experiencia = ?1 and r.fecha = ?2")
    Long countByExperienciaAndFecha(Experiencia experiencia, LocalDate fecha);


    // Si la cantidad es cero me devuelve null porque no hay nadie anotado


    // esto me cuenta cuantas reservas tengo para un determinado dia

    List<Reserva> findAllByNumeroReservaContaining(Long numero);

    @Query("select r from Reserva r where r.experiencia = ?1")
    List<Reserva> findByExperiencia(Experiencia experiencia);




}
