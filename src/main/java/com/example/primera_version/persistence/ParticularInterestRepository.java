package com.example.primera_version.persistence;


import com.example.primera_version.business.entities.InteresParticular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticularInterestRepository extends CrudRepository<InteresParticular,Long> {

    /**
     * Retorna un interes particular por idInteres si encuentra mas de una lanza una excepcion
     * @param idInteres
     * @return
     */
    InteresParticular findOneByIdInteres(Long idInteres);


}
