package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Denuncia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenounceRepository extends CrudRepository<Denuncia, Long> {

    /**
     * Retorna una denuncia por idDenuncia si encuentra mas de una lanza una excepcion
     * @param idDenuncia
     * @return
     */
    Denuncia findOneByIdDenuncia(Long idDenuncia);


}
