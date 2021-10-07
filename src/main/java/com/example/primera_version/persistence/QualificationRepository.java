package com.example.primera_version.persistence;


import com.example.primera_version.business.entities.Calificacion;
import com.example.primera_version.business.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends CrudRepository<Calificacion, Long> {
    /**
     * Retorna una calificacion por id si encuentra mas de una lanza una excepcion
     * @param idCalificacion
     * @return
     */
    Calificacion findOneByIdCalificacion(Long idCalificacion);

    Boolean existsByIdCalificacion(Long idCalificacion);


}