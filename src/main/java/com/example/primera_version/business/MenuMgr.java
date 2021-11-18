package com.example.primera_version.business;
import com.example.primera_version.business.entities.*;
import com.example.primera_version.persistence.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Array;
import java.util.*;

@Service
public class MenuMgr {

    @Autowired
    ExperienceRepository experienceRepository;

    @Transactional
    public Queue<Experiencia> asociadorExperiencias(Turist turista) {

        // Se realiza asi porque estoy recorriendo intereses de turistas y experiencias. Si hay un turista muy interesado, tendra a lo sumo 50 intereses
        // Me genero la cola de prioridad de 100 lugares ya que me parece un numero razonable de experiencias que coinciden con sus intereses
        Queue<Experiencia> colaExperienciasAMostrar = new PriorityQueue<>(100);

        // PREPARO TURISTA
        Collection<Interes> interesesTurista = turista.getIntereses(); // Todos los intereses del turista
        Collection<InteresGeneral> interesesGeneralesTurista = new ArrayList<>(10);// Todos los INTERESES GENERALES del turista
        Collection<InteresParticular> interesParticularesTurista = new ArrayList<>(10); // Todos los INTERESES PARTICULARES del turista

        //Cargo las listas con los intereses generales y particulares del turista
        for (Interes interes : interesesTurista) {
            if(interes instanceof InteresGeneral){
                interesesGeneralesTurista.add((InteresGeneral) interes);
            }else{
                interesParticularesTurista.add((InteresParticular) interes);
            }
        }
        //PREPARO EXPERIENCIAS
        Collection<Experiencia> allExperiencias = (Collection<Experiencia>) experienceRepository.findAll();

        ArrayList<Experiencia> experienciasRecomendadas = new ArrayList<>(15); // Experiencias que voy a mostrar en el menu principal en orden de relevancia

        //Designo mis parametros
        int N = interesesTurista.size();
        int n = interesParticularesTurista.size();
        float M = 0; // Cantidad de intereses generales que tiene la expereincia
        float m = 0f; // Cantidad de intereses particulares que tiene la experiencia

        float puntaje;

        for (Experiencia experiencia: allExperiencias) {

            Collection<Interes> interesesExperiencia = experiencia.getIntereses();
            Collection<InteresGeneral> interesesGeneralesExperiencia = new ArrayList<>(10);
            Collection<InteresParticular> interesesParticularesExperiencia = new ArrayList<>(10);

            for (Interes interes: interesesExperiencia){
                if(interes instanceof InteresGeneral){
                    interesesGeneralesExperiencia.add((InteresGeneral) interes);
                }else {
                    interesesParticularesExperiencia.add((InteresParticular) interes);
                }
            }
            // Actualizo los parametros
            puntaje = 0f;
            m = interesesParticularesExperiencia.size();
            M = interesesGeneralesExperiencia.size();

            for (InteresGeneral interesGeneral:interesesGeneralesExperiencia) {
                if (interesesGeneralesTurista.contains(interesGeneral)){
                    puntaje = puntaje + (100 / ( (2 * N) + (M) ));
                }
            }
            for (InteresParticular interesParticular: interesesParticularesExperiencia) {
                if(interesParticularesTurista.contains(interesParticular)){
                    puntaje = puntaje + (300 / ( (2 * n) + (m) ));
                }
            }
            /*Agrego la experiencia a la priority queue si esta habilitada y si matcheo con al menos un interes. Vease que recien ahora me fijo si esta habilitada
            * ya que se espera que este algoritmo recomendador NO se lleve a cabo en cada entrada del turista, sino guardar a las experiencias recomendadas en una variable de clase y
            * volver a ejecutar el algoritmo solo en el caso de que el turista cambie los intereses o se agregue un determinado numero de experiencias nuevas. Tal vez sea bueno ejecutarlo cada
            * determinado tiempo por ejemplo. */
            experiencia.puntaje = puntaje;
            if(experiencia.getEstaDisponible() && puntaje > 0){
                colaExperienciasAMostrar.add(experiencia);
            }
        }


        return colaExperienciasAMostrar;
    }

}
