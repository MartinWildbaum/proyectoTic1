package com.example.primera_version.business;

import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.business.exceptions.*;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.UserRepository;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;


@Service
public class ExperienceMgr {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserRepository userRepository;


    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addExperience(String tituloExperiencia, String descripcion, String linkVideos, String ubicacion, ArrayList<byte[]> imagenes, Collection<Interes> intereses, String aforoDisponible, OperadorTuristico operadorTuristico) throws InvalidExperienceInformation, ExperienceAlreadyExists{

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if ( chequearString(tituloExperiencia) || chequearString(descripcion) || chequearString(linkVideos) || chequearString(ubicacion) || chequearString(aforoDisponible) || intereses == null || operadorTuristico == null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidExperienceInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        //FIXME el cliente pretende este control?
        if (experienceRepository.findOneByTituloExperiencia(tituloExperiencia) != null) {

            throw new ExperienceAlreadyExists("Ya existe una experiencia registrada con ese titulo");
        }


        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Experiencia experienciaAgregar = new Experiencia();

        experienciaAgregar.setTituloExperiencia(tituloExperiencia);
        experienciaAgregar.setDescripcion(descripcion);
        experienciaAgregar.setIntereses(intereses);
        experienciaAgregar.setUbicacion(ubicacion);
        experienciaAgregar.setCantidad(Integer.valueOf(aforoDisponible));
        experienciaAgregar.setLinkVideos(linkVideos);
        experienciaAgregar.setImagen(imagenes);
        experienciaAgregar.setOperadorTuristico(operadorTuristico);


        // Guardo el turista que me cree anteriormente en mi base de datos

        experienceRepository.save(experienciaAgregar);

    }

    public Experiencia encontrarExperienciaPorTitulo(String tituloExperiencia){

        Experiencia experiencia = experienceRepository.findOneByTituloExperiencia(tituloExperiencia);
        return experiencia;

    }

    public Experiencia encontrarExperienciaPorId(Long id){

        Experiencia experiencia = experienceRepository.findOneByIdExperiencia(id);
        return experiencia;

    }
    public ArrayList<Experiencia> encontrarTodasContenidoTitulo(String texto){
        ArrayList<Experiencia> listaExperiencias = (ArrayList<Experiencia>) experienceRepository.findAllByTituloExperienciaContaining(texto);
        return listaExperiencias;
    }
    public ArrayList<Experiencia> encontrarTodasPorOperadorTuristico(OperadorTuristico operadorTuristico){
        ArrayList<Experiencia> devolucion = (ArrayList<Experiencia>) experienceRepository.findAllByOperadorTuristico(operadorTuristico);
        return devolucion;

    }

    public ArrayList<Experiencia> encontrarTodas(){
        return (ArrayList<Experiencia>) experienceRepository.findAll();
    }
}
