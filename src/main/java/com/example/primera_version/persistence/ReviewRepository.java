package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Critica;
import com.example.primera_version.business.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends CrudRepository<Critica, Long> {

    /**
     * Retorna una critica por idClasificacion si encuentra mas de una lanza una excepcion
     * @param idCalificacion
     * @return
     */
    Critica findOneByIdCalificacion(Long idCalificacion);

    Boolean existsByIdCalificacion(Long idCalificacion);


}