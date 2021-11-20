package com.example.primera_version.persistence;

import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.OperadorTuristico;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends CrudRepository<Experiencia, Long> {

    /**
     * Retorna una experiencia por id si encuentra mas de una lanza una excepcion
     * @param idExperiencia
     * @return
     */
    Experiencia findOneByIdExperiencia(Long idExperiencia);

    Experiencia findOneByTituloExperiencia(String tituloExperiencia);

    List<Experiencia> findAllByTituloExperienciaContaining(String texto);

    List<Experiencia> findAllByOperadorTuristico(OperadorTuristico operadorTuristico);



}
