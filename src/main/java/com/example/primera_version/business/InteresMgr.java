package com.example.primera_version.business;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.business.entities.InteresParticular;
import com.example.primera_version.persistence.GeneralInterestRepository;
import com.example.primera_version.persistence.InterestRepository;
import com.example.primera_version.persistence.ParticularInterestRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class InteresMgr {

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    GeneralInterestRepository generalInterestRepository;

    @Autowired
    ParticularInterestRepository particularInterestRepository;

    public ObservableList<Interes> getIntereses() {

        ObservableList<Interes> intereses = FXCollections.observableArrayList();
        Iterable<Interes> temp = interestRepository.findAll();

        for(Interes interes : temp){
            intereses.add(interes);
        }

        return intereses;
    }

    public ObservableList<InteresGeneral> getIntereseGenerales(){
        ObservableList<InteresGeneral> interesesGenerales = FXCollections.observableArrayList();
        Iterable<InteresGeneral> temp = generalInterestRepository.findAll();

        for(InteresGeneral interes : temp){
            interesesGenerales.add(interes);
        }

        return interesesGenerales;
    }

    public  ObservableList<InteresParticular> getInteresParticular(){
        ObservableList<InteresParticular> interesesParticulares = FXCollections.observableArrayList();
        Iterable<InteresParticular> temp = particularInterestRepository.findAll();

        for(InteresParticular interes : temp){
            interesesParticulares.add(interes);
        }

        return interesesParticulares;
    }

    public ArrayList<Interes> obtenerTodosIntereses(){
        ArrayList<Interes> intereses = (ArrayList<Interes>) interestRepository.findAll();
        return intereses;
    }

    public InteresGeneral encontrarGeneralPorNombre(String nombreInteres){
        return generalInterestRepository.findByNombre(nombreInteres);
    }

}
