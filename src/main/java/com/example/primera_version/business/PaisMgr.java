package com.example.primera_version.business;
import com.example.primera_version.business.entities.Pais;
import com.example.primera_version.persistence.CountryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisMgr {

    @Autowired
    CountryRepository countryRepository;

//    public void addPais(String nombre, String brev) throws InvalidInformation, ClassAlreadyExists {
//
//        if(nombre == null || nombre.equals("") || brev == null || brev.equals("")){
//            throw new InvalidInformation();
//        }
//
//        if(paisRepository.findPaisByNombre(nombre) != null){
//            throw new ClassAlreadyExists();
//        }
//
//        Pais pais = new Pais(nombre, brev);
//        paisRepository.save(pais);
//    }
//
    public ObservableList<String> getPaises() {

        ObservableList<String> paises = FXCollections.observableArrayList();
        Iterable<Pais> temp = countryRepository.findAll();

        for(Pais pais : temp){
            paises.add(pais.getNombre());
        }

        return paises;
    }

}
