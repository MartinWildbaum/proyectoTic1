package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Calificacion;
import com.example.primera_version.business.entities.Puntuacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PunctuationRepository extends CrudRepository<Puntuacion, Long> {

     /**
     * Retorna una puntuacion por id si encuentra mas de una lanza una excepcion
     * @param idCalificacion
     * @return
     */
    Puntuacion findOneByIdCalificacion(Long idCalificacion);

    Boolean existsByIdCalificacion(Long idCalificacion);


}