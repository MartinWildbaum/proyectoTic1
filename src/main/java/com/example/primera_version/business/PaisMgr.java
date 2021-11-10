package com.example.primera_version.business;
import com.example.primera_version.business.entities.Pais;
import com.example.primera_version.persistence.CountryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PaisMgr {

    @Autowired
    CountryRepository countryRepository;

    public ObservableList<String> getPaises() {
        ObservableList<String> paises = FXCollections.observableArrayList();
        ArrayList<Pais> temp = (ArrayList<Pais>) countryRepository.findAll();
        for(Pais pais : temp){
            paises.add(pais.getNombre());
        }
        return paises;
    }
}
