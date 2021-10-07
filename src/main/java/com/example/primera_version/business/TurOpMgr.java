package com.example.primera_version.business;

import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.persistence.TurOpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.primera_version.business.exceptions.InvalidTOInformation;
import com.example.primera_version.business.exceptions.TOAlreadyExists;


@Service
public class TurOpMgr {

    @Autowired
    private TurOpRepository turOpRepository;

    private boolean chequearString(String stringAChequear) {
        boolean devolucion = false;

        if (stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ")) {
            devolucion = true;
        }

        return devolucion;

    }

    public void addTO(String razon_social, String name, String contact_name, String contact_surname, String contact_phone, Integer contact_age) throws InvalidTOInformation, TOAlreadyExists {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if (chequearString(razon_social) || chequearString(name) || chequearString(contact_name) || chequearString(contact_surname) || chequearString(contact_phone) || contact_age == null || contact_age < 0) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidTOInformation(
                    "ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el turista no existe, si existe voy a tirar una excepcion

        if (turOpRepository.findOneByRazonSocial(razon_social) != null) {

            throw new TOAlreadyExists("Ya existe un operador turistico registrado con ese mail");
        }

        // Me creo el oprador turistico que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        OperadorTuristico operadorTuristico = new OperadorTuristico();


        operadorTuristico.setRazonSocial(razon_social);
        operadorTuristico.setNameTO(name);
        operadorTuristico.setContact_name(contact_name);
        operadorTuristico.setContact_surname(contact_surname);
        operadorTuristico.setContact_phone(contact_phone);
        operadorTuristico.setContact_age(contact_age);




        // Guardo el operador turistico que me cree anteriormente en mi base de datos

        turOpRepository.save(operadorTuristico);

    }

}
