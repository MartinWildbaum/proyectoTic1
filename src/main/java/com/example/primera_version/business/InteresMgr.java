package com.example.primera_version.business;


import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.persistence.InterestRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InteresMgr {


    @Autowired
    InterestRepository interestRepository;

    public ObservableList<Interes> getIntereses() {

        ObservableList<Interes> intereses = FXCollections.observableArrayList();
        Iterable<Interes> temp = interestRepository.findAll();

        for(Interes interes : temp){
            intereses.add(interes);
        }

        return intereses;
    }

    public ArrayList<Interes> obtenerTodosIntereses(){
        ArrayList<Interes> intereses = (ArrayList<Interes>) interestRepository.findAll();
        return intereses;
    }


}
