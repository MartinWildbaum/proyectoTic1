package com.example.primera_version.business;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Imagen;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.business.exceptions.*;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Service
public class ExperienceMgr {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ImageRepository imageRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addExperience(String tituloExperiencia, String descripcion, String linkVideos, String ubicacion, ArrayList<byte[]> imagenes, Set<Interes> intereses, String aforoDisponible, OperadorTuristico operadorTuristico, byte[] imagenPortada) throws InvalidExperienceInformation, ExperienceAlreadyExists{

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if ( chequearString(tituloExperiencia) || chequearString(descripcion) || chequearString(linkVideos) || chequearString(ubicacion) || chequearString(aforoDisponible) || intereses == null || operadorTuristico == null || imagenPortada == null) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidExperienceInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Suponemos que el cliente pretende este control en principio
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
        experienciaAgregar.setOperadorTuristico(operadorTuristico);
        experienciaAgregar.setFotoPortada(imagenPortada);
        experienciaAgregar.setMomentoRegistro(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Guardo el turista que me cree anteriormente en mi base de datos

        experienceRepository.save(experienciaAgregar);

        //FIXME
        for (byte[] imagen: imagenes) {
            Imagen imagenAgregar = new Imagen();
            imagenAgregar.setImagen(imagen);
            imagenAgregar.setExperiencia(experienciaAgregar);
            imageRepository.save(imagenAgregar);
        }

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

    public void actualizarExperiencia(Experiencia experiencia){
        experienceRepository.save(experiencia);
    }

    public ArrayList<Experiencia> encontrarTodasPorTituloYOperador(OperadorTuristico operadorTuristico, String texto){
        ArrayList<Experiencia> devolucion = new ArrayList<>(10);
        ArrayList<Experiencia> listaExperiencias = (ArrayList<Experiencia>) experienceRepository.findAllByTituloExperienciaContaining(texto);
        for (Experiencia experiencia: listaExperiencias) {
            if(experiencia.getOperadorTuristico().equals(operadorTuristico)){
                devolucion.add(experiencia);
            }
        }

        return devolucion;
    }

}
