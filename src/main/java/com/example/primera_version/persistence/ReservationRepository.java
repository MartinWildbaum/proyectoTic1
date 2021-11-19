package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reserva, Long> {

    /**
     * Retorna una reserva por numero de reserva si encuentra mas de una lanza una excepcion
     * @param numeroReserva
     * @return
     */
    Reserva findOneByNumeroReserva(Long numeroReserva);

    List<Reserva> findAllByNumeroReservaContaining(Long numero);



}
