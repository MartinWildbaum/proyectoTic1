package com.example.primera_version.persistence;


import com.example.primera_version.business.entities.Imagen;
import com.example.primera_version.business.entities.Turist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Imagen, Long> {

    /**
     * Retorna una imagen por mail si encuentra mas de una lanza una excepcion
     * @param idImagen
     * @return
     */

}
