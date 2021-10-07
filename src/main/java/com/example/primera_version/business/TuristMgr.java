package com.example.primera_version.business;

import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.UserAlreadyExists;
import com.example.primera_version.business.exceptions.UserNotExists;
import com.example.primera_version.persistence.TuristRepository;
import com.example.primera_version.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.business.exceptions.PasswordNoCoinciden;

import java.time.LocalDate;

@Service
public class TuristMgr {

    @Autowired
    private TuristRepository turistRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addTurist(String mail, String nationality, LocalDate birthdate, String password,String passwordConfirmar) throws InvalidUserInformation, UserAlreadyExists,PasswordNoCoinciden {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if ( chequearString(password) || chequearString(mail) || nationality == null || birthdate.isAfter(LocalDate.now()) || chequearString(passwordConfirmar)) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidUserInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el turista no existe, si existe voy a tirar una excepcion

        if (turistRepository.findOneByMail(mail) != null) { //como la primary key cambio esto tiene que cambiar?

            throw new UserAlreadyExists("Ya existe un usuario registrado con ese mail");
        }

        if(!passwordConfirmar.equals(password)){
            throw new PasswordNoCoinciden("Las contrseñas no coinciden");
        }

        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Turist turistaAAgregar = new Turist();

        turistaAAgregar.setMail(mail);
        turistaAAgregar.setNacionalidad(nationality);
        turistaAAgregar.setBirthdate(birthdate);
        turistaAAgregar.setPassword(password);

        // Guardo el turista que me cree anteriormente en mi base de datos

        turistRepository.save(turistaAAgregar);

    }

    public boolean ingresar(String mail, String password)throws InvalidUserInformation, UserNotExists {

        if(mail == null || mail.isBlank() || password == null || password.isBlank()){

            //throw new InvalidUserInformation("Los datos ingresador para iniciar sesion no son correctos");
            return false;

        }else if (!turistRepository.existsByMail(mail)){//Si no encuentro el mail en mi base de datos es que no hay ningun usuario con ese mail registrado en mi base de datos y tengo que tirar un excepcion

            //throw new UserNotExists("El mail no coincide con ningun usuario creado hasta la fecha");
            return false;

        }else if(userRepository.findOneByMail(mail).getPassword().equals(password)){// Habia un usuario creado con ese mail y ademas con esa misma contraseña

            return true;

        }else{

            //throw new InvalidUserInformation("Los datos ingresador para iniciar sesion no son correctos");
            return false;
//            return false; //esto antes retornaba false pero yo no quiero que retorne false, porque si pongo usuario
            //no tiene sentido que retorne false porque si pongo un usario cualuiqer

        }

    }


}
