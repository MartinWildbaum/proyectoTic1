package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Pais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.primera_version.business.entities.Pais;


@Repository
public interface CountryRepository extends CrudRepository<Pais, String> {
    /**
     * Retorna un pais por nombre si encuentra mas de una lanza una excepcion
     * @param nombre
     * @return
     */
    Pais findOneByNombre(String nombre);


}
