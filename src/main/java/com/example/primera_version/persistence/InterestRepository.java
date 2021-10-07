package com.example.primera_version.persistence;


import com.example.primera_version.business.entities.Interes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends CrudRepository<Interes,Long> {

    /**
     * Retorna un interes por idInteres si encuentra mas de una lanza una excepcion
     * @param idInteres
     * @return
     */
    Interes findOneByIdInteres(Long idInteres);

}
