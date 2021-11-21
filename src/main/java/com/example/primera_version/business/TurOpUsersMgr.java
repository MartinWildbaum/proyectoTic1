package com.example.primera_version.business;

import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.business.entities.UsuarioOpTur;
import com.example.primera_version.business.exceptions.*;
import com.example.primera_version.persistence.OpTurUsersRepository;
import com.example.primera_version.persistence.TurOpRepository;
import com.example.primera_version.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class TurOpUsersMgr {

    @Autowired
    private OpTurUsersRepository opTurUsersRepository;

    @Autowired
    private TurOpRepository turOpRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }

    //En verdad esta funcion en principio no se utiliza
    public void addUsuarioOpTuristico(String mail, String password, String passwordConfirmar, String razonSocial) throws InvalidUserInformation, UserAlreadyExists, PasswordNoCoinciden, InvalidTOInformation {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if ( chequearString(razonSocial) || chequearString(password) || chequearString(mail) || chequearString(passwordConfirmar)) {
            throw new InvalidUserInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el usuario del operador turistico no existe, si existe voy a tirar una excepcion

        if (opTurUsersRepository.findOneByMail(mail) != null) {

            throw new UserAlreadyExists("Ya existe un usuario registrado con ese mail");
        }

        if(!passwordConfirmar.equals(password)){
            throw new PasswordNoCoinciden("Las contrseñas no coinciden");
        }
        OperadorTuristico operadorTuristico = turOpRepository.findOneByRazonSocial(razonSocial);
        if(operadorTuristico == null){
            throw new InvalidTOInformation("La razon social ingresada no se encuentra en nustros registros");
        }

        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        UsuarioOpTur usuarioOpTur = new UsuarioOpTur();

        usuarioOpTur.setMail(mail);
        usuarioOpTur.setPassword(password);
        usuarioOpTur.setOperadorTuristico(operadorTuristico);

        // Guardo el turista que me cree anteriormente en mi base de datos

        opTurUsersRepository.save(usuarioOpTur);

    }

    public boolean ingresar(String mail, String password) throws InvalidUserInformation, UserNotExists {

        if(mail == null || mail.isBlank() || password == null || password.isBlank()){

            //throw new InvalidUserInformation("Los datos ingresador para iniciar sesion no son correctos");
            return false;

        }else if (!opTurUsersRepository.existsByMail(mail)){//Si no encuentro el mail en mi base de datos es que no hay ningun usuario con ese mail registrado en mi base de datos y tengo que tirar un excepcion

            //throw new UserNotExists("El mail no coincide con ningun usuario creado hasta la fecha");
            return false;

        }else if(opTurUsersRepository.existsByMail(mail) || userRepository.findOneByMail(mail).getPassword().equals(password)){// Habia un usuario creado con ese mail y ademas con esa misma contraseña

            return true;

        }else{

            //throw new InvalidUserInformation("Los datos ingresador para iniciar sesion no son correctos");
            return false;
//            return false; //esto antes retornaba false pero yo no quiero que retorne false, porque si pongo usuario
            //no tiene sentido que retorne false porque si pongo un usario cualuiqer

        }

    }

    public UsuarioOpTur encontrarUnUsuariosOperadorTuristico(String mail){
        UsuarioOpTur uot = opTurUsersRepository.findOneByMail(mail);
        return uot;
    }

    public OperadorTuristico encontrarOperadorTuristicoParaElQueTrabaja(String mailEmpleado){
        UsuarioOpTur usuarioOpTur = opTurUsersRepository.findOneByMail(mailEmpleado);
        OperadorTuristico operadorTuristico = usuarioOpTur.getOperadorTuristico();
        return operadorTuristico;
    }

}
