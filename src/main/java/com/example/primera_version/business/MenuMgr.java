package com.example.primera_version.business;


import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.persistence.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MenuMgr {

    @Autowired
    ExperienceRepository experienceRepository;

    @Transactional
    public ArrayList<Experiencia> asociadorExperiencias(Turist turista) {

        Collection<Interes> intereses = turista.getIntereses();
        Iterable<Experiencia> allExperiencias = experienceRepository.findAll();
        ArrayList<Experiencia> experienciasRecomendadas = new ArrayList<>(); // Experiencias que voy a mostrar en el menu principal

        for (Experiencia experiencia : allExperiencias) {

            for (Interes interes : intereses) {
                if (experiencia.getIntereses().contains(interes)) {
                    experienciasRecomendadas.add(experiencia);
                    break; // Para que no me agregue las experiencias mas de una vez
                }
            }
        }
        return experienciasRecomendadas;
    }

}
