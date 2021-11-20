package com.example.primera_version.business;
import com.example.primera_version.business.entities.Administrador;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.PasswordNoCoinciden;
import com.example.primera_version.business.exceptions.UserAlreadyExists;
import com.example.primera_version.business.exceptions.UserNotExists;
import com.example.primera_version.persistence.AdministratorRepository;
import com.example.primera_version.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;


@Service
public class AdminMgr {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean chequearString(String stringAChequear){
        boolean devolucion=false;

        if(stringAChequear == null || stringAChequear.isBlank() || stringAChequear.equals(" ") ){
            devolucion=true;
        }

        return devolucion;

    }


    public void addAdmin(String mail, String password, String passwordConfirmar) throws InvalidUserInformation, UserAlreadyExists, PasswordNoCoinciden {

        // Verifico que la informacion que me metieron en la interface sea valida, osea que no haya ningun campo vacio o cosas incoherentes

        if ( chequearString(password) || chequearString(mail) || chequearString(passwordConfirmar)) { //agregue que si la fecha es despues de hoy que de un error

            throw new InvalidUserInformation("ERROR!, Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el turista no existe, si existe voy a tirar una excepcion

        if (administratorRepository.findOneByMail(mail) != null) { //como la primary key cambio esto tiene que cambiar?

            throw new UserAlreadyExists("Ya existe un administrador registrado con ese mail");
        }

        if(!passwordConfirmar.equals(password)){
            throw new PasswordNoCoinciden("Las contrseñas no coinciden. Intentelo nuevamente.");
        }

        // Me creo el turista que quiero agregar con los datos que me pasaron que ya se que son todos correctos/validos

        Administrador administradorAAgregar = new Administrador();

        administradorAAgregar.setMail(mail);
        administradorAAgregar.setPassword(password);

        // Guardo el turista que me cree anteriormente en mi base de datos

        administratorRepository.save(administradorAAgregar);

    }

    public boolean ingresar(String mail, String password) throws InvalidUserInformation, UserNotExists {

        if(mail == null || mail.isBlank() || password == null || password.isBlank()){

            //throw new InvalidUserInformation("Los datos ingresador para iniciar sesion no son correctos");
            return false;

        }else if (!administratorRepository.existsByMail(mail)){//Si no encuentro el mail en mi base de datos es que no hay ningun usuario con ese mail registrado en mi base de datos y tengo que tirar un excepcion

            //throw new UserNotExists("El mail no coincide con ningun  creado hasta la fecha");
            return false;

        }else if(userRepository.findOneByMail(mail).getPassword().equals(password)){// Habia un usuario creado con ese mail y ademas con esa misma contraseña

            return true;

        }else{
            return false;
        }
    }

    public Administrador encontrarAdministradorPorMail(String mailAdministrador){
        Administrador administrador = administratorRepository.findOneByMail(mailAdministrador);
        return administrador;
    }
}
