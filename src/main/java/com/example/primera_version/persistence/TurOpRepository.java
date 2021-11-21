package com.example.primera_version.persistence;



import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.OperadorTuristico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurOpRepository extends CrudRepository<OperadorTuristico, Long> { // Repositorio de los operadores turisticos


    /**
     * Retorna un operador turistico por id si encuentra mas de una lanza una excepcion
     * @param idOpTur
     * @return
     */
     OperadorTuristico findOneByIdOpTur(Long idOpTur);

     OperadorTuristico findOneByRazonSocial(String razonSocial);

    List<OperadorTuristico> findAllByNameTOContaining(String texto);

}
