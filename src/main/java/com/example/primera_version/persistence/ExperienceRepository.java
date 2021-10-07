package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Experiencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experiencia, Long> {

    /**
     * Retorna una experiencia por id si encuentra mas de una lanza una excepcion
     * @param idExperiencia
     * @return
     */
    Experiencia findOneByIdExperiencia(Long idExperiencia);


}
